package com.pku.ipku.model.pkuInfo;

import android.support.v4.app.Fragment;

import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.ui.pkuInfo.PkuLectureFragment;

/**
 * Created by XingLiang on 2014/12/16.
 */
public class PkuLecture implements Fragmentable {
    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new PkuLectureFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return null;
    }

    @Override
    public String getType() {
        return "pku_lecture";
    }

    @Override
    public String getChineseName() {
        return "校内讲座";
    }
}
