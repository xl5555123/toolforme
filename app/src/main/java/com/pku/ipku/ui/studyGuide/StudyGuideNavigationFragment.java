package com.pku.ipku.ui.studyGuide;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pku.ipku.R;
import com.pku.ipku.model.studyguide.StudyGuide;
import com.pku.ipku.util.UIHelper;

public class StudyGuideNavigationFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_study_guide, container, false);
    }

    private void initActionBar() {
        ActionBar actionBar = getActivity().getActionBar();
        UIHelper.setUpActionBarWithNoNavigation(actionBar, new StudyGuide().getChineseName());
    }

}
