package com.pku.ipku.model.person;

import android.support.v4.app.Fragment;

import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.model.type.Typable;
import com.pku.ipku.ui.person.LibStateFragment;

/**
 * Created by XingLiang on 2014/12/16.
 */
public class LibState implements Fragmentable {
    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new LibStateFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return null;
    }

    @Override
    public String getType() {
        return "lib_state";
    }

    @Override
    public String getChineseName() {
        return "图书馆";
    }
}
