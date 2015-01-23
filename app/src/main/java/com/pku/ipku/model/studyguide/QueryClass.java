package com.pku.ipku.model.studyguide;

import android.support.v4.app.Fragment;

import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.ui.studyGuide.queryClass.QueryClassFragment;

/**
 * Created by XingLiang on 2014/12/16.
 */
public class QueryClass implements Fragmentable {
    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new QueryClassFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return null;
    }

    @Override
    public String getType() {
        return "query_class";
    }

    @Override
    public String getChineseName() {
        return "课程查询";
    }
}
