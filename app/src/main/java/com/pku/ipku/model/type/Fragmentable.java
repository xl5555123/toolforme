package com.pku.ipku.model.type;

import android.support.v4.app.Fragment;

/**
 * Created by XingLiang on 2014/12/8.
 */
public interface Fragmentable {

    public Fragment getAttachedFragment(String[] args);

    public Integer getMenuIcon();

}
