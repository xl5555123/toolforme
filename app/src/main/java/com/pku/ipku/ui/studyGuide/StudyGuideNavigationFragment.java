package com.pku.ipku.ui.studyGuide;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.collect.Lists;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.pku.ipku.R;
import com.pku.ipku.api.factory.IpkuServiceFactory;
import com.pku.ipku.model.studyguide.Curriculum;
import com.pku.ipku.model.studyguide.FreeClassroom;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.model.studyguide.QueryClass;
import com.pku.ipku.model.studyguide.StudyGuide;
import com.pku.ipku.model.studyguide.dto.CurriculumDTO;
import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.task.LoadDataConfigure;
import com.pku.ipku.task.LoadDataDefaultTask;
import com.pku.ipku.util.UIHelper;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudyGuideNavigationFragment extends Fragment {

    private static List<Fragmentable> items = Lists.newArrayList();

    ViewPager viewPager;
    TabPageIndicator indicator;

    SlidingFragmentActivity slidingFragmentActivity;

    public StudyGuideNavigationFragment() {
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
        slidingFragmentActivity = (SlidingFragmentActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_study_guide, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        indicator = (TabPageIndicator) view.findViewById(R.id.titles);
        items = new ArrayList<Fragmentable>() {
            {
                add(new Curriculum());
                add(new FreeClassroom());
                add(new QueryClass());
            }
        };
        UIHelper.setUpTab(slidingFragmentActivity, getChildFragmentManager(), viewPager, indicator, items);
        return view;
    }

    private void initActionBar() {
        ActionBar actionBar = getActivity().getActionBar();
        UIHelper.setUpActionBarWithNoNavigation(actionBar, new StudyGuide().getChineseName());
    }





}
