package com.pku.ipku.model.pkuInfo;

import android.support.v4.app.Fragment;

import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.ui.pkuInfo.PkuClubFragment;

/**
 * Created by XingLiang on 2014/12/16.
 */
public class PkuClub implements Fragmentable {
    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new PkuClubFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return null;
    }

    @Override
    public String getType() {
        return "pku_club";
    }

    @Override
    public String getChineseName() {
        return "社团通知";
    }
}
