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
        setUnSelected(settingButton);
    }

    private void setSelected(View view) {
        view.setEnabled(false);
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View thisView = viewGroup.getChildAt(i);
            thisView.setSelected(true);
        }
    }

    private void setUnSelected(View view) {
        view.setEnabled(true);
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

    private void navigationToSetting() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, SettingNavigationFragment.newInstance()).commit();
    }

    private void initView() {
        homeButton = findViewById(R.id.home_button);
        studentButton = findViewById(R.id.student_button);
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
