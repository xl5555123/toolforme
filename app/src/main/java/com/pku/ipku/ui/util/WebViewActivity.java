package com.pku.ipku.ui.util;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.alamkanak.weekview.WeekView;
import com.pku.ipku.R;
import com.pku.ipku.util.UIHelper;

public class WebViewActivity extends ActionBarActivity {

    private WebView webView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        if (title != null) {
            getActionBar().setTitle(title);
        }
        else {
            getActionBar().setTitle("IPKU");
        }
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
