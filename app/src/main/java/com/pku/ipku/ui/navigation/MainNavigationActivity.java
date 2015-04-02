package com.pku.ipku.ui.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.pku.ipku.R;


public class MainNavigationActivity extends FragmentActivity {

    private View homeButton;
    private View studentButton;
    private View settingButton;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        initView();
        initPager();
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
        viewPager.setCurrentItem(0);
    }

    private void navigateToPerson() {
        viewPager.setCurrentItem(1);
    }

    private void navigationToSetting() {
        viewPager.setCurrentItem(2);
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    initSelectState();
                    setSelected(homeButton);
                }
                else if (position == 1) {
                    initSelectState();
                    setSelected(studentButton);
                }
                else {
                    initSelectState();
                    setSelected(settingButton);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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


    }

    private void initPager() {
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return HomeFragment.newInstance();
                }
                else if (position == 1) {
                    return PersonFragment.newInstance();
                }
                else {
                    return SettingNavigationFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }
}
