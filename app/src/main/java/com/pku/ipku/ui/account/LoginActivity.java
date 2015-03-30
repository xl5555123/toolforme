package com.pku.ipku.ui.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.pku.ipku.R;
import com.pku.ipku.model.account.User;
import com.pku.ipku.ui.navigation.MainNavigationActivity;
import com.pku.ipku.util.AppContextHolder;
import com.pku.ipku.util.UIHelper;

public class LoginActivity extends Activity {

    private EditText usernameTextView;

    private EditText passwordTextView;

    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameTextView = (EditText) findViewById(R.id.username);
        passwordTextView = (EditText) findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    void login(){
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

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
            if(usernameTextView.isFocused()){
                usernameTextView.clearFocus();
                passwordTextView.requestFocus();
            }else if(passwordTextView.isFocused()){
                if(!passwordTextView.getText().toString().equals("")) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (inputMethodManager.isActive()) {
                        inputMethodManager.hideSoftInputFromWindow(LoginActivity.this.getCurrentFocus().getWindowToken(), 0);
                    }
                }
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
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
                finish();
            }
        }
    }
}
