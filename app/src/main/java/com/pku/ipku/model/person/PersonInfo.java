package com.pku.ipku.model.person;

import android.support.v4.app.Fragment;

import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.model.type.Typable;
import com.pku.ipku.ui.person.PersonInfoFragment;

/**
 * Created by XingLiang on 2014/12/16.
 */
public class PersonInfo implements Fragmentable {
    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new PersonInfoFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return null;
    }

    @Override
    public String getType() {
        return "person_info";
    }

    @Override
    public String getChineseName() {
        return "个人信息";
    }
}
