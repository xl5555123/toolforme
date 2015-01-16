package com.pku.ipku.model.person;

import android.support.v4.app.Fragment;

import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.ui.person.ArrearageStateFragment;

/**
 * Created by XingLiang on 2014/12/16.
 */
public class ArrearageState implements Fragmentable {
    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new ArrearageStateFragment();
    }

    @Override
    public Integer getMenuIcon() {
        return null;
    }

    @Override
    public String getType() {
        return "arrearage_state";
    }

    @Override
    public String getChineseName() {
        return "欠费信息";
    }
}
