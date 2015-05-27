package com.pku.portal.ui.util;

import android.os.Bundle;
import android.view.MenuItem;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class BaseActivityIncludingFooterNavigation extends SwipeBackActivity {

    private SwipeBackLayout swipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = savedInstanceState.getString("title");
        if (title != null) {
            getActionBar().setTitle(title);
        }
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        swipeBackLayout = getSwipeBackLayout();
        final int edgeFlag = SwipeBackLayout.FOCUS_LEFT;
        swipeBackLayout.setEdgeTrackingEnabled(edgeFlag);
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
