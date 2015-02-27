package com.pku.ipku.ui.account;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pku.ipku.R;
import com.pku.ipku.model.account.User;
import com.pku.ipku.task.LoadDataDefaultTask;

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
                if (usernameTextView.getText() == null || passwordTextView.getText() == null) {
                    return;
                }
                new LoadDataDefaultTask(new LoginTaskConfigure()).execute();
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
            if ()
        }
    }
}
