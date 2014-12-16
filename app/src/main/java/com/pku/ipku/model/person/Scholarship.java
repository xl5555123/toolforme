package com.pku.ipku.model.person;

import android.support.v4.app.Fragment;

import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.model.type.Typable;
import com.pku.ipku.ui.person.ScholarshipFragment;

/**
 * Created by XingLiang on 2014/12/16.
 */
public class Scholarship implements Fragmentable {
    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new ScholarshipFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return null;
    }

    @Override
    public String getType() {
        return "scholarship";
    }

    @Override
    public String getChineseName() {
        return "奖学金信息";
    }
}
