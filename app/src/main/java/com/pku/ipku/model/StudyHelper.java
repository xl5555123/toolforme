package com.pku.ipku.model;

import android.support.v4.app.Fragment;

import com.pku.ipku.R;
import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.model.type.Typable;
import com.pku.ipku.ui.studyGuide.StudyGuideFragment;

/**
 * Created by XingLiang on 2014/12/8.
 */
public class StudyHelper implements Typable, Fragmentable {
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
        return new StudyGuideFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return R.drawable.side_panel_tasks;
    }
}
