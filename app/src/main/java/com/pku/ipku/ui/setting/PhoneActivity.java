package com.pku.ipku.ui.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.pku.ipku.R;
import com.pku.ipku.adapter.setting.PhoneAdapter;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;

public class PhoneActivity extends BaseActivityIncludingFooterNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_phone);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", "常用电话");
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        ListView listView = (ListView) findViewById(R.id.phone_list);
        if (listView != null) {
            listView.setAdapter(new PhoneAdapter(this));
        }
    }
}
