package com.pku.ipku.model;

import android.support.v4.app.Fragment;

import com.pku.ipku.R;
import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.ui.pkuGuide.PkuGuideFragment;

/**
 * Created by XingLiang on 2014/12/8.
 */
public class PkuNetworkHelper implements Fragmentable {

    @Override
    public String getType() {
        return "pku_guide";
    }

    @Override
    public String getChineseName() {
        return "校内指南";
    }

    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new PkuGuideFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return R.drawable.side_panel_tasks;
    }
}
