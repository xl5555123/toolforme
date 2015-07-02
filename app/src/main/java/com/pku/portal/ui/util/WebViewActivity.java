package com.pku.portal.ui.util;


import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import com.pku.portal.R;
import com.pku.portal.util.UIHelper;

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
            savedInstanceState.putString("title", title);
        } else {
            savedInstanceState.putString("title", "IPKU");
        }
        super.onCreate(savedInstanceState);
        webView = (WebView) findViewById(R.id.webview);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

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
