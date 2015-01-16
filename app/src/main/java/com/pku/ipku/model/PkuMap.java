package com.pku.ipku.model;

import android.support.v4.app.Fragment;

import com.pku.ipku.R;
import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.ui.pkuMap.PkuMapFragment;

/**
 * Created by XingLiang on 2014/12/8.
 */
public class PkuMap implements Fragmentable {
    @Override
    public String getType() {
        return "pku_map";
    }

    @Override
    public String getChineseName() {
        return "校园地图";
    }

    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new PkuMapFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return R.drawable.side_panel_tasks;
    }
}
