package com.pku.ipku.model.person;

import android.support.v4.app.Fragment;

import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.model.type.Typable;
import com.pku.ipku.ui.person.ScoreFragment;

/**
 * Created by XingLiang on 2014/12/16.
 */
public class Score implements Fragmentable {
    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new ScoreFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return null;
    }

    @Override
    public String getType() {
        return "score";
    }

    @Override
    public String getChineseName() {
        return "成绩查询";
    }
}
