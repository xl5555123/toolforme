package com.pku.ipku.ui.pkuInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.pku.ipku.R;

public class PkuClubDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pku_club_detail);
        WebView clubWebView = (WebView) findViewById(R.id.club_webview);
        Intent intent = getIntent();
        String url = intent.getStringExtra(PkuClubFragment.CLUB_ACTIVITY_KEY);
        clubWebView.loadUrl(url);
    }

}
