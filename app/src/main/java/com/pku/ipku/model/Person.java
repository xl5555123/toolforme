package com.pku.ipku.model;

import android.support.v4.app.Fragment;

import com.pku.ipku.R;
import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.model.type.Typable;
import com.pku.ipku.ui.person.PersonFragment;

/**
 * Created by XingLiang on 2014/12/8.
 */
public class Person implements Typable, Fragmentable {
    @Override
    public String getType() {
        return "person";
    }

    @Override
    public String getChineseName() {
        return "个人服务";
    }

    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new PersonFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return R.drawable.side_panel_switch_team;
    }
}
