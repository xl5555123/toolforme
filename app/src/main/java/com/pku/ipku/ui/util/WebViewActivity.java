package com.pku.ipku.ui.util;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.alamkanak.weekview.WeekView;
import com.pku.ipku.R;
import com.pku.ipku.util.UIHelper;

public class WebViewActivity extends BaseActivityIncludingFooterNavigation {

    private WebView webView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_web_view);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        if (title != null) {
            savedInstanceState.putString("title", "成绩查询");
        }
        else {
            savedInstanceState.putString("title", "IPKU");
        }
        super.onCreate(savedInstanceState);
        webView = (WebView) findViewById(R.id.webview);

        UIHelper.setWebViewContent(webView, url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
