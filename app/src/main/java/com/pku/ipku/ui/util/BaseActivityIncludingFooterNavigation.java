package com.pku.ipku.ui.util;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class BaseActivityIncludingFooterNavigation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = savedInstanceState.getString("title");
        if (title != null) {
            getActionBar().setTitle(title);
        }
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
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
