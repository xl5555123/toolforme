package com.pku.ipku.ui.account;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.model.account.User;
import com.pku.ipku.ui.navigation.MainNavigationActivity;
import com.pku.ipku.util.AppContextHolder;
import com.pku.ipku.util.UIHelper;

public class LoginActivity extends Activity {

    private TextView usernameTextView;

    private TextView passwordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameTextView = (TextView) findViewById(R.id.username);
        passwordTextView = (TextView) findViewById(R.id.password);
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameTextView.getText().toString();
                String password = passwordTextView.getText().toString();
                if (username == null || username.length() == 0 || password == null || password.length() == 0) {
                    UIHelper.ToastMessage("用户名和密码不能为空");
                    return;
                }
                User user = new User();
                user.setPassword(passwordTextView.getText().toString());
                user.setUsername(usernameTextView.getText().toString());
                new LoginTask().execute(user);
            }
        });
    }

    private class LoginTask extends AsyncTask<User, Void, User> {


        @Override
        protected User doInBackground(User... users) {
            if (users.length == 0) {
                return null;
            }
            return users[0];
        }

        @Override
        protected void onPostExecute(User user) {
            if (user != null) {
                UIHelper.ToastMessage("登录成功");
                AppContextHolder.getAppContext().setCurrentUser(user);
                Intent intent = new Intent(LoginActivity.this, MainNavigationActivity.class);
                startActivity(intent);
            }
        }
    }
}
