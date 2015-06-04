package com.pku.portal.ui.navigation;


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
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pku.portal.R;
import com.pku.portal.api.util.NetHelper;
import com.pku.portal.ui.account.LoginActivity;
import com.pku.portal.ui.account.NetworkHelperActivity;
import com.pku.portal.ui.person.CurriculumListFragment;
import com.pku.portal.ui.setting.AboutActivity;
import com.pku.portal.ui.setting.BeijingRailway;
import com.pku.portal.ui.setting.PhoneActivity;
import com.pku.portal.ui.setting.PkuMap;
import com.pku.portal.ui.util.WebViewActivity;
import com.pku.portal.util.AppContextHolder;
import com.pku.portal.util.UIHelper;
import com.pku.portal.util.networkHelper.Version;

import org.json.JSONArray;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingNavigationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingNavigationFragment extends Fragment {

    public static SettingNavigationFragment fragment;

    private AlertDialog updateNotificationDialog;

    private ProgressDialog downloadProgressDialog;

    private long apkDownloadReference;

    private DownloadManager downloadManager;

    public SettingNavigationFragment() {
        // Required empty public constructor
    }

    public static SettingNavigationFragment newInstance() {
        return new SettingNavigationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting_navigation, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("注销登录?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AppContextHolder.getAppContext().deleteCurrentUser();
                                //这里不把这个设置成新的JSONArray的话，由于fragment还没有释放，用的还是原来那个，所有会导致没变，其他方法？
                                CurriculumListFragment.courses = new JSONArray();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                if (intent != null) {
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        }).create().show();
            }
        });
        view.findViewById(R.id.network).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NetworkHelperActivity.class);
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
        view.findViewById(R.id.phone_usual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PhoneActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.pku_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PkuMap.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.beijing_railway).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BeijingRailway.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.pku_calendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", "http://www.pku.edu.cn/campuslife/xl/index.htm");
                intent.putExtra("title", "校历");
                startActivity(intent);
            }
        });
        view.findViewById(R.id.suggestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(Intent.ACTION_SENDTO);
                data.setData(Uri.parse("mailto:dudong113@163.com"));
                data.putExtra(Intent.EXTRA_SUBJECT, "掌上信息门户反馈意见");
                data.putExtra(Intent.EXTRA_TEXT, "掌上信息门户反馈意见：");
                startActivity(data);
            }
        });
        view.findViewById(R.id.about_software).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetVersionTask().execute();
            }
        });
    }

    private class GetVersionTask extends AsyncTask<Void, Void, Version> {

        @Override
        protected Version doInBackground(Void... params) {
            return NetHelper.getVersion();
        }

        @Override
        protected void onPostExecute(Version version) {

            UIHelper.ToastMessage("当前为最新版本");
        }
    }

    private void downloadNewVersion() {
        String newUrl = "http://fir.im/api/v2/app/install/54a216d223a60da3260071ca?token=0e473730754c11e486625ce46eebe0684d318702";

        Uri uri = Uri.parse(newUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle("信息门户更新");
        request.setDestinationInExternalFilesDir(getActivity(), null, "ipku.apk");
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
        getActivity().registerReceiver(receiver, filter);
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
