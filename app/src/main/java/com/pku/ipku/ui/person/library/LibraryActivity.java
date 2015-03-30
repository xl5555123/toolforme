package com.pku.ipku.ui.person.library;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.pku.ipku.R;
import com.pku.ipku.model.person.dto.LibBorrowDTO;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.ui.AppContext;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;

import java.util.List;

/**
 * Created by XingLiang on 2015/2/5.
 */
public class LibraryActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {
    private Fragment searchResultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_library);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", getPageTitle());
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        searchResultFragment = LendListFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, searchResultFragment).commit();
    }

    @Override
    public int getPageDrawableId() {
        return R.drawable.new_library;
    }

    @Override
    public String getPageTitle() {
        return "借阅图书";
    }

    @Override
    public int getPageBackgroundId() {
        return R.drawable.navigation_border_red;
    }

    @Override
    public Class attachedClassType() {
        return LibraryActivity.class;
    }
}
