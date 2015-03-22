package com.pku.ipku.ui.navigation;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.pku.ipku.R;
import com.pku.ipku.ui.person.CurriculumListFragment;


public class MainNavigationActivity extends Activity {

    private View homeButton;
    private View studentButton;
    private View schoolButton;
    private View settingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        initView();
    }

    private void initSelectState() {
        setUnSelected(homeButton);
        setUnSelected(studentButton);
        setUnSelected(schoolButton);
        setUnSelected(settingButton);
    }

    private void setSelected(View view) {
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View thisView = viewGroup.getChildAt(i);
            thisView.setSelected(true);
        }
    }

    private void setUnSelected(View view) {
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View thisView = viewGroup.getChildAt(i);
            thisView.setSelected(false);
        }
    }

    private void navigateToHome() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, CurriculumListFragment.newInstance()).commit();
    }

    private void navigateToPerson() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, PersonFragment.newInstance()).commit();
    }

    private void navigateToSchool() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, SchoolFragment.newInstance()).commit();
    }

    private void navigationToSetting() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, SettingNavigationFragment.newInstance()).commit();
    }

    private void initView() {
        homeButton = findViewById(R.id.home_button);
        studentButton = findViewById(R.id.student_button);
        schoolButton = findViewById(R.id.school_button);
        settingButton = findViewById(R.id.setting_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSelectState();
                setSelected(homeButton);
                navigateToHome();
            }
        });
        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSelectState();
                setSelected(studentButton);
                navigateToPerson();
            }
        });
        schoolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSelectState();
                setSelected(schoolButton);
                navigateToSchool();
            }
        });
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSelectState();
                setSelected(settingButton);
                navigationToSetting();
            }
        });

        initSelectState();
        setSelected(homeButton);
        navigateToHome();
    }
}
