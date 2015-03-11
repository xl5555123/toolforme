package com.pku.ipku.ui.pkuInfo;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pku.ipku.R;
import com.pku.ipku.model.pkuInfo.PkuInfoType;
import com.pku.ipku.model.pkuInfo.RegisterInPkuInfoPage;
import com.pku.ipku.model.pkuInfo.dto.PkuPublicInfo;
import com.pku.ipku.ui.util.BaseActivityIncludingFooterNavigation;

import java.util.ArrayList;
import java.util.List;

public class PkuNewsActivity extends BaseActivityIncludingFooterNavigation implements RegisterInPkuInfoPage {

    private List<PkuInfoType> pkuInfoTypeList = new ArrayList<PkuInfoType>() {
        {
            add(new PkuInfoType(PkuInfoType.RECENT_SCHOOL_NOTICES));
            add(new PkuInfoType(PkuInfoType.TOP_NEWS));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pku_news2);
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        savedInstanceState.putString("title", "学校新闻");
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
                    getFragmentManager().beginTransaction().replace(R.id.content, PkuPublicInfoWithoutPagingFragment.newInstance(pkuInfoType)).commit();
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
        return R.drawable.news;
    }

    @Override
    public String getPageTitle() {
        return "学校新闻";
    }

    @Override
    public int getPageBackgroundId() {
        return R.drawable.navigation_border_pink;
    }

    @Override
    public Class attachedClassType() {
        return PkuNewsActivity.class;
    }

}
