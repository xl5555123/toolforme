package com.pku.ipku.model.studyguide;

import android.support.v4.app.Fragment;

import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.ui.studyGuide.freeClassRoom.FreeClassroomFragment;

/**
 * Created by XingLiang on 2014/12/16.
 */
public class FreeClassroom implements Fragmentable {
    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new FreeClassroomFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return null;
    }

    @Override
    public String getType() {
        return "free_class";
    }

    @Override
    public String getChineseName() {
        return "空闲教室";
    }
}
