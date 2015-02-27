package com.pku.ipku.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.pku.ipku.R;

import java.util.Date;

/**
 * 应用程序UI工具包：封装UI相关的一些操作
 */
public class UIHelper {

    public final static int LISTVIEW_ACTION_REFRESH = 0x02;
    public final static int LISTVIEW_ACTION_SCROLL = 0x03;

    public final static int LISTVIEW_DATA_MORE = 0x01;
    public final static int LISTVIEW_DATA_LOADING = 0x02;
    public final static int LISTVIEW_DATA_FULL = 0x03;
    public final static int LISTVIEW_DATA_EMPTY = 0x04;
    /**
     * 全局web样式
     */
    public final static String WEB_STYLE = "<style>* {font-size:16px;line-height:20px;} p {color:#333;} a {color:#3E62A6;} img {max-width:310px;} " +
            "img.alignleft {float:left;max-width:120px;margin:0 10px 5px 0;border:1px solid #ccc;background:#fff;padding:2px;} " +
            "pre {font-size:9pt;line-height:12pt;font-family:Courier New,Arial;border:1px solid #ddd;border-left:5px solid #6CE26C;background:#f6f6f6;padding:5px;} " +
            "a.tag {font-size:15px;text-decoration:none;background-color:#bbd6f3;border-bottom:2px solid #3E6D8E;border-right:2px solid #7F9FB6;color:#284a7b;margin:2px 2px 2px 0;padding:2px 4px;white-space:nowrap;}</style>";
    private static Date lastNetErrorDate;

    /**
     * 弹出Toast消息
     *
     * @param msg
     */
    public static void ToastMessage(Context cont, String msg) {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(Context cont, int msg) {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(String msg) {
        Toast.makeText(AppContextHolder.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(int msg) {
        Toast.makeText(AppContextHolder.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * 发送App异常崩溃报告
     *
     * @param cont
     * @param crashReport
     */
    public static void sendAppCrashReport(final Context cont, final String crashReport) {
        AlertDialog.Builder builder = new AlertDialog.Builder(cont);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle(R.string.app_error);
        builder.setMessage(R.string.app_error_message);
        builder.setPositiveButton(R.string.submit_report, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //发送异常报告
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822"); //真机
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"xl5555123@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "IPKU客户端 - 错误报告");
                i.putExtra(Intent.EXTRA_TEXT, crashReport);
                cont.startActivity(Intent.createChooser(i, "发送错误报告"));
                //退出
                AppManager.getAppManager().AppExit(cont);
            }
        });
        builder.setNegativeButton(R.string.sure, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //退出
                AppManager.getAppManager().AppExit(cont);
            }
        });
        builder.show();
    }

    /**
     * 显示网络错误，一般情况下3分钟通知一次
     */
    public static void showNetError() {
        Date now = new Date();
        // TODO:10分钟通知一次，暂时先写死
        if (lastNetErrorDate == null || now.getTime() - lastNetErrorDate.getTime() > 1000 * 60 * 3) {
            lastNetErrorDate = now;
            ToastMessage(AppContextHolder.getAppContext(), "网络错误，请检查您的网络连接");
        }
    }

    /**
     * 显示网络错误，总是会展示，一般是更新数据或者创建数据时总需要通知用户
     */
    public static void showNetErrorAlways() {
        ToastMessage(AppContextHolder.getAppContext(), "网络错误，请检查您的网络连接");
    }

    /**
     * @param webView
     * @param url
     */
    public static void setWebViewContent(WebView webView, String url) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDefaultFontSize(15);
        webView.setInitialScale(100);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
