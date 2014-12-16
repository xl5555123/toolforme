package com.pku.ipku.model.person;

import android.support.v4.app.Fragment;

import com.pku.ipku.R;
import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.model.type.Typable;
import com.pku.ipku.ui.person.PersonNavigationFragment;

/**
 * Created by XingLiang on 2014/12/8.
 */
public class Person implements Fragmentable {
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
        return new PersonNavigationFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return R.drawable.side_panel_switch_team;
    }
}
