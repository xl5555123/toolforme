package com.pku.ipku.ui.pkuInfo;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.pku.ipku.R;
import com.pku.ipku.model.pkuInfo.PkuClub;
import com.pku.ipku.model.pkuInfo.PkuInfo;
import com.pku.ipku.model.pkuInfo.PkuJob;
import com.pku.ipku.model.pkuInfo.PkuLecture;
import com.pku.ipku.model.pkuInfo.PkuNews;
import com.pku.ipku.model.pkuInfo.PkuNotice;
import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.util.DataHandleUtil;
import com.pku.ipku.util.UIHelper;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import cn.onboard.android.slidingmenu.SlidingMenu;
import cn.onboard.android.slidingmenu.app.SlidingFragmentActivity;

public class PkuInfoNavigationFragment extends Fragment {

    private SlidingFragmentActivity slidingFragmentActivity;

    private final static List<Fragmentable> items = new ArrayList<Fragmentable>() {
        {
            add(new PkuNotice());
            add(new PkuLecture());
            add(new PkuNews());
            add(new PkuClub());
            add(new PkuJob());
        }
    };

    public PkuInfoNavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pku_info, container, false);
        initActionBar();
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        TabPageIndicator indicator = (TabPageIndicator) view.findViewById(R.id.titles);
        UIHelper.setUpTab(slidingFragmentActivity, viewPager, indicator, items);
        return view;
    }

    private void initActionBar() {
        final ActionBar actionBar = getActivity().getActionBar();
        slidingFragmentActivity = (SlidingFragmentActivity) getActivity();
        slidingFragmentActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        UIHelper.setUpActionBarWithNoNavigation(actionBar, new PkuInfo().getChineseName());
    }



}
