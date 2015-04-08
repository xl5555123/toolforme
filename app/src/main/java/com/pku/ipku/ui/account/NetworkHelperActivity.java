package com.pku.ipku.ui.account;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pku.ipku.R;
import com.pku.ipku.model.account.User;
import com.pku.ipku.model.networkHelper.Ipgw;
import com.pku.ipku.util.AppContextHolder;
import com.pku.ipku.util.NetWork;
import com.pku.ipku.util.SwitchButton;

import java.util.Properties;

/**
 * Created by vector liu on 2015/3/29.
 */
public class NetworkHelperActivity extends Activity {
    private Context context = null;
    private EditText uid = null;
    private EditText pwd = null;
    private SwitchButton free = null;
    private Button connect = null;
    private Button disconnect = null;
    private Button all_disconnect = null;
    private TextView edittext_result = null;
    private Ipgw ipgw = null;

    private User user;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    edittext_result.setText(msg.getData().getString("content"));
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_helper);

        context = this;
        user = AppContextHolder.getAppContext().getCurrentUser();
        initView();
    }

    private void initView() {

        getActionBar().setTitle("网关登录");
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        uid = (EditText) findViewById(R.id.network_uid);
        pwd = (EditText) findViewById(R.id.network_pwd);
        free = (SwitchButton) findViewById(R.id.network_free_or_not);
        connect = (Button) findViewById(R.id.network_connect);
        disconnect = (Button) findViewById(R.id.network_disconnect);
        all_disconnect = (Button) findViewById(R.id.network_all_disconnect);
        edittext_result = (TextView) findViewById(R.id.network_edittext_result);
        edittext_result.setMovementMethod(ScrollingMovementMethod.getInstance());
        connect.setOnClickListener(new ConnectListener());
        disconnect.setOnClickListener(new ConnectListener());
        all_disconnect.setOnClickListener(new ConnectListener());

        uid.setText(user.getUsername());
        pwd.setText(user.getPassword());

        //设置光标位置
        CharSequence text = uid.getText();
        if (text instanceof Spannable) {
            Spannable spanText = (Spannable) text;
            Selection.setSelection(spanText, text.length());
        }

        //默认就是保存账户信息的
        /*
        pwd.setText(sharedPre.getString("password", ""));
        if(sharedPre.getString("save", "").equals("yes"))
        	save.setChecked(true);
        	*/
    }


    class ConnectListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            final View tv = v;
            final String uid_str = uid.getText().toString();
            if (uid_str.isEmpty()) {
                Toast.makeText(getApplicationContext(), "账号不能为空", Toast.LENGTH_LONG).show();
                return;
            }
            final String pwd_str = pwd.getText().toString();
            if (pwd_str.isEmpty()) {
                Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_LONG).show();
                return;
            }

            if (!NetWork.isNetworkAvailable(context)) {
                showMsg("请先连上wifi!");
                return;
            }
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();//
                    super.run();

                    Properties result = new Properties();
                    result.setProperty("uid", uid_str);
                    result.setProperty("password", pwd_str);
                    String content = null;
                    if (tv.getId() == connect.getId()) {
                        Toast.makeText(getApplicationContext(), "connect", Toast.LENGTH_LONG).show();
                        if (free.isChecked()) {
                            result.setProperty("range", "1");
                        } else {
                            result.setProperty("range", "2");
                        }
                        ipgw = new Ipgw(result);
                        content = ipgw.connect();
                    } else if (tv.getId() == disconnect.getId()) {
                        Toast.makeText(getApplicationContext(), "disconnect", Toast.LENGTH_LONG).show();
                        ipgw = new Ipgw(result);
                        content = ipgw.disConnect();
                    } else if (tv.getId() == all_disconnect.getId()) {
                        Toast.makeText(getApplicationContext(), "all_disconnect", Toast.LENGTH_LONG).show();
                        ipgw = new Ipgw(result);
                        content = ipgw.disConnectAll();
                    }


                    Message msg = new Message();
                    msg.what = 0;
                    Bundle bd = new Bundle();
                    bd.putString("content", content);
                    msg.setData(bd);
                    handler.sendMessage(msg);
                }
            }.start();
            return;
        }
    }


    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
