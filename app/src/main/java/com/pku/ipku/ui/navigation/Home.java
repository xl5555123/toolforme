package com.pku.ipku.ui.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.pku.ipku.R;
import com.pku.ipku.ui.person.PersonNavigationFragment;
import com.pku.ipku.util.AppManager;

import cn.onboard.android.slidingmenu.SlidingMenu;
import cn.onboard.android.slidingmenu.app.SlidingFragmentActivity;

public class Home extends SlidingFragmentActivity {
    //当前页的内容
    private Fragment currrentContent;

    private SlidingMenu slidingMenu;

    private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        initVariables();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }

    private void initVariables() {
        if (currrentContent == null)
            currrentContent = new PersonNavigationFragment();
    }


    private void initView() {
        setContentView(R.layout.content_frame);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, currrentContent).commit();
        getSupportActionBar().setHomeButtonEnabled(true);

        setBehindContentView(R.layout.menu_frame);
        FragmentTransaction leftFragmentTransaction = getSupportFragmentManager().beginTransaction();
        leftFragmentTransaction.replace(R.id.menu_frame, new HomeMenuFragment()).commit();

        slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.setFadeDegree(0.35f);

        //添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
    }

    public void switchContent(Fragment fragment) {
        currrentContent = fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, currrentContent).commit();
        getSlidingMenu().showContent();
    }

    @Override
    public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getSlidingMenu().showContent();
                toggle();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
