package com.pku.portal.ui.util;


import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebView;

import com.pku.portal.R;
import com.pku.portal.util.UIHelper;

public class WebViewActivity extends BaseActivityIncludingFooterNavigation {

    Handler mHandler = new Handler();
    Runnable mProgressRunner = new Runnable() {
        @Override
        public void run() {
            mProgress += 2;

            //Normalize our progress along the progress bar's scale
            int progress = (Window.PROGRESS_END - Window.PROGRESS_START) / 100 * mProgress;
            setProgress(progress);

            if (mProgress < 100) {
                mHandler.postDelayed(mProgressRunner, 50);
            }
        }
    };

    private int mProgress = 100;

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