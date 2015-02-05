package com.pku.ipku.ui.navigation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.pku.ipku.R;
import com.pku.ipku.util.UIHelper;

public class MainNavigationActivity extends Activity {

    private ImageButton homeButton;
    private ImageButton studentButton;
    private ImageButton schoolButton;
    private ImageButton settingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        initView();
    }

    private void initButtonBackground() {
        homeButton.setBackground(getResources().getDrawable(R.drawable.navigation_selector));
        studentButton.setBackground(getResources().getDrawable(R.drawable.navigation_selector));
        schoolButton.setBackground(getResources().getDrawable(R.drawable.navigation_selector));
        settingButton.setBackground(getResources().getDrawable(R.drawable.navigation_selector));
    }

    private void navigateToHome() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, HomeFragment.newInstance()).commit();
    }

    private void navigateToPerson() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, PersonFragment.newInstance()).commit();
    }

    private void navigateToSchool() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, SchoolFragment.newInstance()).commit();
    }

    private void initView() {
        homeButton = (ImageButton) findViewById(R.id.home_button);
        studentButton = (ImageButton) findViewById(R.id.student_button);
        studentButton.setBackgroundColor(getResources().getColor(R.color.darkgreen));
        schoolButton = (ImageButton) findViewById(R.id.school_button);
        settingButton = (ImageButton) findViewById(R.id.setting_button);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage("尽请期待");
            }
        });
        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initButtonBackground();
                studentButton.setBackgroundColor(getResources().getColor(R.color.darkgreen));
                navigateToPerson();
            }
        });
        schoolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initButtonBackground();
                schoolButton.setBackgroundColor(getResources().getColor(R.color.darkgreen));
                navigateToSchool();
            }
        });
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage("尽请期待！");
            }
        });
        navigateToPerson();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
