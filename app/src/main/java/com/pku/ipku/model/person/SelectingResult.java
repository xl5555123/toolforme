package com.pku.ipku.model.person;

import android.support.v4.app.Fragment;

import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.ui.person.SelectionResultFragment;

/**
 * Created by XingLiang on 2014/12/16.
 */
public class SelectingResult implements Fragmentable {
    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new SelectionResultFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return null;
    }

    @Override
    public String getType() {
        return "selecting_result";
    }

    @Override
    public String getChineseName() {
        return "选课结果";
    }
}
