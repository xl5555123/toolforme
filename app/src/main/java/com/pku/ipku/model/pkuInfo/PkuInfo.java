package com.pku.ipku.model.pkuInfo;

import android.support.v4.app.Fragment;

import com.pku.ipku.R;
import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.ui.pkuInfo.PkuInfoNavigationFragment;

/**
 * Created by XingLiang on 2014/12/8.
 */
public class PkuInfo implements Fragmentable {
    @Override
    public String getType() {
        return "pku_news";
    }

    @Override
    public String getChineseName() {
        return "校内资讯";
    }

    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new PkuInfoNavigationFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return R.drawable.side_panel_switch_team;
    }
}
