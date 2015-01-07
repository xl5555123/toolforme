package com.pku.ipku.ui.person;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.pku.ipku.R;
import com.pku.ipku.model.person.ArrearageState;
import com.pku.ipku.model.person.LibState;
import com.pku.ipku.model.person.Person;
import com.pku.ipku.model.person.PersonInfo;
import com.pku.ipku.model.person.Scholarship;
import com.pku.ipku.model.person.Score;
import com.pku.ipku.model.person.SelectingResult;
import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.util.UIHelper;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class PersonNavigationFragment extends Fragment {

    private final static List<Fragmentable> items = new ArrayList<Fragmentable>() {
        {
            add(new PersonInfo());
            add(new Scholarship());
            add(new Score());
            add(new SelectingResult());
            add(new LibState());
            add(new ArrearageState());
        }
    };

    private SlidingFragmentActivity activity;

    public PersonNavigationFragment() {
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
        initActionBar();
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        TabPageIndicator indicator = (TabPageIndicator) view.findViewById(R.id.titles);
        UIHelper.setUpTab(activity, getChildFragmentManager(), viewPager, indicator, items);
        return view;
    }

    private void initActionBar() {
        final ActionBar actionBar = getActivity().getActionBar();
        activity = (SlidingFragmentActivity) getActivity();
        UIHelper.setUpActionBarWithNoNavigation(actionBar, new Person().getChineseName());
    }


}
