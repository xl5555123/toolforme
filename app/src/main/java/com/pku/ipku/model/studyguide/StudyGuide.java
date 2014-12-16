package com.pku.ipku.model.studyguide;

import android.support.v4.app.Fragment;

import com.pku.ipku.R;
import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.ui.studyGuide.StudyGuideNavigationFragment;

/**
 * Created by XingLiang on 2014/12/8.
 */
public class StudyGuide implements Fragmentable {
    @Override
    public String getType() {
        return "pku_helper";
    }

    @Override
    public String getChineseName() {
        return "学习助手";
    }

    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new StudyGuideNavigationFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return R.drawable.side_panel_tasks;
    }
}
