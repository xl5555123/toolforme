package com.xingliang.toolforme.ui.person.library;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.xingliang.toolforme.R;
import com.xingliang.toolforme.model.navigation.RegisterInPersonPage;
import com.xingliang.toolforme.ui.util.BaseActivityIncludingFooterNavigation;

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
