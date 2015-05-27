package com.pku.portal.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.pku.portal.R;
import com.pku.portal.api.util.NetHelper;
import com.pku.portal.model.account.User;
import com.pku.portal.util.networkHelper.Version;
import com.pku.portal.ui.account.LoginActivity;
import com.pku.portal.ui.navigation.MainNavigationActivity;
import com.pku.portal.util.AppContextHolder;
import com.pku.portal.util.SystemHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 程序显示进入页面
 *
 * @author xingliang
 */
public class AppStart extends Activity {

    private AlertDialog updateNotificationDialog;

    private ProgressDialog downloadProgressDialog;

    private long apkDownloadReference;

    private DownloadManager downloadManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppContextHolder.setAppContext((AppContext) getApplicationContext());
        final View view = View.inflate(this, R.layout.start, null);
        setContentView(view);

        downloadManager = (DownloadManager) AppContextHolder.getAppContext().getSystemService(Context.DOWNLOAD_SERVICE);

        updateNotificationDialog = new AlertDialog.Builder(this)
                .setTitle("软件更新")
                .setMessage("有新版本更新")
                .setCancelable(false)
                .setPositiveButton("更新", toUpdateListener).create();

        downloadProgressDialog = new ProgressDialog(this);
        downloadProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        downloadProgressDialog.setTitle("更新中...");
        downloadProgressDialog.setMessage("正在下载,请稍后");
        downloadProgressDialog.setCancelable(false);
        downloadProgressDialog.setMax(100);

        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(2000);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                new GetVersionTask().execute();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

        });
    }

    /**
     * TODO redirect to Login page or Navigation Page ?
     */
    private void redirectTo() {
        User currentUser = AppContextHolder.getAppContext().getCurrentUser();
        Intent intent;
        if (currentUser == null) {
            intent = new Intent(this, LoginActivity.class);
        } else {
            intent = new Intent(this, MainNavigationActivity.class);
        }
        startActivity(intent);
        finish();
    }

    private class GetVersionTask extends AsyncTask<Void, Void, Version> {

        @Override
        protected Version doInBackground(Void... params) {
            return NetHelper.getVersion();
        }

        @Override
        protected void onPostExecute(Version version) {
            if (version != null && version.getVersion().charAt(0) - '0' > SystemHelper.getPackageInfo().versionCode) {
                Log.d("newest version", String.format("%s", version.getVersion()));
                updateNotificationDialog.setMessage("更新日志:\n" + version.getChangelog());
                updateNotificationDialog.show();
            }

            else {
                redirectTo();
            }
        }
    }

    private void downloadNewVersion() {
        String newUrl = "http://fir.im/api/v2/app/install/54a216d223a60da3260071ca?token=0e473730754c11e486625ce46eebe0684d318702";

        Uri uri = Uri.parse(newUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle("信息门户更新");
        request.setDestinationInExternalFilesDir(getApplicationContext(), null, "ipku.apk");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        apkDownloadReference = downloadManager.enqueue(request);

        checkIfdownloaded();
        downloadProgressDialog.show();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                checkDownloadProgress();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 50);
    }


    private void checkDownloadProgress() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(apkDownloadReference);
        Cursor cursor = downloadManager.query(query);
        if (cursor != null && cursor.moveToFirst()) {
            int current_byte = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
            int size = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
            downloadProgressDialog.setProgress(100 * current_byte / size);
            cursor.close();
        }

    }

    private void checkIfdownloaded() {
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

                if (reference == apkDownloadReference) {
                    downloadProgressDialog.hide();
                    installNewVersion(downloadManager);

                }
            }
        };
        registerReceiver(receiver, filter);
    }

    private void installNewVersion(DownloadManager downloadManager) {
        DownloadManager.Query updateDownloadQuery = new DownloadManager.Query();
        updateDownloadQuery.setFilterById(apkDownloadReference);
        Cursor thisDownload = downloadManager.query(updateDownloadQuery);
        String fileName = "ipku.apk";
        if (thisDownload.moveToFirst()) {
            int fileUriIndex = thisDownload.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
            fileName = thisDownload.getString(fileUriIndex);
        }
        Intent install = new Intent();
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.setAction(Intent.ACTION_VIEW);
        install.setDataAndType(Uri.parse(fileName),
                "application/vnd.android.package-archive");
        startActivity(install);
    }

    DialogInterface.OnClickListener toUpdateListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            downloadNewVersion();
        }
    };
}