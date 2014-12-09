package com.pku.ipku.ui.navigation;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.pku.ipku.R;
import com.pku.ipku.ui.person.PersonFragment;
import com.pku.ipku.util.AppManager;

import cn.onboard.android.slidingmenu.SlidingMenu;
import cn.onboard.android.slidingmenu.app.SlidingFragmentActivity;

public class Home extends SlidingFragmentActivity {
    //当前页的内容
    private Fragment currrentContent;

    private SlidingMenu slidingMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables(savedInstanceState);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }

    private void initVariables(Bundle savedInstanceState) {
        if (savedInstanceState != null)
            currrentContent = getSupportFragmentManager().getFragment(
                    savedInstanceState, "mContent");
        if (currrentContent == null)
            currrentContent = new PersonFragment();
    }


    private void initView() {
        setContentView(R.layout.content_frame);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, currrentContent).commit();

        setBehindContentView(R.layout.menu_frame);
        FragmentTransaction leftFragmentTransaction = getSupportFragmentManager().beginTransaction();
        leftFragmentTransaction.replace(R.id.menu_frame, new HomeMenuFragment()).commit();

        slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
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
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment).commit();
        getSlidingMenu().showContent();
    }
}
