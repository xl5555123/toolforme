package com.pku.ipku.model.studyguide;

import android.support.v4.app.Fragment;

import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.ui.studyGuide.CurriculumFragment;

/**
 * Created by XingLiang on 2014/12/16.
 */
public class Curriculum implements Fragmentable {
    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new CurriculumFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return null;
    }

    @Override
    public String getType() {
        return "curriculum";
    }

    @Override
    public String getChineseName() {
        return "课程表";
    }
}
