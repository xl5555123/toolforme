package com.pku.ipku.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;

import com.pku.ipku.model.type.Fragmentable;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/2.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private final List<Fragmentable> fragmentables;

    private final FragmentManager fragmentManager;

    public PagerAdapter(FragmentManager fm, List<Fragmentable> fragmentableList) {
        super(fm);
        fragmentManager = fm;
        this.fragmentables = fragmentableList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentables.get(position).getAttachedFragment(null);
    }

    @Override
    public int getCount() {
        return fragmentables.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentables.get(position).getChineseName();
    }
}