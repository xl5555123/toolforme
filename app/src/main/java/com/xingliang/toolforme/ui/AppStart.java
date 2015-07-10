package com.xingliang.toolforme.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.xingliang.toolforme.R;
import com.xingliang.toolforme.model.account.User;
import com.xingliang.toolforme.ui.account.LoginActivity;
import com.xingliang.toolforme.ui.navigation.MainNavigationActivity;
import com.xingliang.toolforme.util.AppContextHolder;

/**
 * 程序显示进入页面
 *
 * @author xingliang
 */
public class AppStart extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppContextHolder.setAppContext((AppContext) getApplicationContext());
        final View view = View.inflate(this, R.layout.start, null);
        setContentView(view);

        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(500);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

        });
    }

    /**
     * TODO redirect to Login page or Navigation Page ?
     */
    private void redirectTo() {
        User currentUser = AppContextHolder.getAppContext().getCurrentUser();
        Intent intent;
        if (currentUser == null) {
            intent = new Intent(this, LoginActivity.class);
        } else {
            intent = new Intent(this, MainNavigationActivity.class);
        }
        startActivity(intent);
        finish();
    }

}