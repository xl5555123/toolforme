package com.pku.ipku.ui.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pku.ipku.R;
import com.pku.ipku.ui.navigation.MainNavigationActivity;
import com.pku.ipku.util.UIHelper;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.ToastMessage("登录成功");
                Intent intent = new Intent(LoginActivity.this, MainNavigationActivity.class);
                startActivity(intent);
            }
        });
    }
}
