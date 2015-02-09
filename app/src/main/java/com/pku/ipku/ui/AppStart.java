package com.pku.ipku.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pku.ipku.R;
import com.pku.ipku.ui.account.LoginActivity;
import com.pku.ipku.util.AppContextHolder;

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
        redirectTo();
    }

    /**
     * TODO redirect to Login page or Navigation Page ?
     */
    private void redirectTo() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}