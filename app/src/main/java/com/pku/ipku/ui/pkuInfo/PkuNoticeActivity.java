package com.pku.ipku.ui.pkuInfo;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.pku.ipku.R;
import com.pku.ipku.model.person.navigation.RegisterInPersonPage;
import com.pku.ipku.model.pkuInfo.PkuInfoType;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;

import java.util.ArrayList;
import java.util.List;

public class PkuNoticeActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPersonPage {

    private List<PkuInfoType> pkuInfoTypeList = new ArrayList<PkuInfoType>() {
        {
            add(new PkuInfoType(PkuInfoType.RECENT_SCHOOL_NOTICES));
            add(new PkuInfoType(PkuInfoType.RECENT_DEPT_NOTICES));
            add(new PkuInfoType(PkuInfoType.TOP_NEWS));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pku_news2);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", getPageTitle());
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        ActionBar actionBar = getActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(getPageTitle());
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                PkuInfoType pkuInfoType = (PkuInfoType) tab.getTag();
                if (pkuInfoType != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, PkuPublicInfoWithoutPagingFragment.newInstance(pkuInfoType)).commit();
                }
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };
        for (PkuInfoType pkuInfoType : pkuInfoTypeList) {
            actionBar.addTab(actionBar.newTab().setText(pkuInfoType.getTitle()).setTabListener(tabListener).setTag(pkuInfoType));
        }
    }

    @Override
    public int getPageDrawableId() {
        return R.drawable.new_notice;
    }

    @Override
    public String getPageTitle() {
        return "校内公告";
    }

    @Override
    public int getPageBackgroundId() {
        return R.drawable.navigation_border_yellow;
    }

    @Override
    public Class attachedClassType() {
        return PkuNoticeActivity.class;
    }
}
