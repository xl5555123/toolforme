package com.pku.ipku.ui.person;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.pku.ipku.R;

public class CurriculumAsWebActivity extends FragmentActivity {


    WebView my_wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_curriculum_as_web);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", "课程表");
        super.onCreate(savedInstanceState);

        initView();
    }

    public void initView() {

        getActionBar().setTitle("课程表");
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        my_wv = (WebView) findViewById(R.id.my_wv);
        my_wv.getSettings().setDefaultTextEncodingName("UTF-8");
        WebSettings settings = my_wv.getSettings();
        settings.setUseWideViewPort(true);//设定支持viewport
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);//设定支持缩放
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        my_wv.loadDataWithBaseURL("www.pku.edu.cn", CurriculumListFragment.courseTable, "text/html", "UTF-8", null);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
