package com.xingliang.toolforme.ui.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.xingliang.toolforme.R;
import com.xingliang.toolforme.api.util.NetHelper;
import com.xingliang.toolforme.model.account.User;
import com.xingliang.toolforme.ui.navigation.MainNavigationActivity;
import com.xingliang.toolforme.util.AppContextHolder;
import com.xingliang.toolforme.util.DaoHelper;
import com.xingliang.toolforme.util.NetWork;
import com.xingliang.toolforme.util.UIHelper;

import org.apache.http.conn.util.InetAddressUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class LoginActivity extends Activity {

    private EditText usernameTextView;

    Context context;

    private EditText passwordTextView;

    private Button loginButton;

    private CheckBox checkBox;

    private boolean rememberPassword;

    String res="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        usernameTextView = (EditText) findViewById(R.id.username);
        passwordTextView = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login_button);
        checkBox = (CheckBox) findViewById(R.id.remember_password);
        rememberPassword = true;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rememberPassword = isChecked;
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    void login() {
        String username = usernameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        if (username == null || username.length() == 0 || password == null || password.length() == 0) {
            UIHelper.ToastMessage("用户名和密码不能为空");
            return;
        }
        User user = new User();
        user.setPassword(passwordTextView.getText().toString());
        user.setUsername(usernameTextView.getText().toString());
        if (!NetWork.isNetworkAvailable(context)) {
            Toast.makeText(this, "请先连上wifi!", Toast.LENGTH_SHORT).show();
            return;
        }
        new LoginTaskByIAAA().execute(user);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            if (usernameTextView.isFocused()) {
                usernameTextView.clearFocus();
                passwordTextView.requestFocus();
            } else if (passwordTextView.isFocused()) {
                if (!passwordTextView.getText().toString().equals("")) {
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

    public String bytes2HexString(byte[] b)    //byte转换为十六进制
    {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i]& 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

//    private class LoginTask extends AsyncTask<User, Void, User> {
//
//
//        @Override
//        protected User doInBackground(User... users) {
//            if (users.length == 0) {
//                return null;
//            }
//            Properties result = new Properties();
//            result.setProperty("uid", users[0].getUsername());
//            result.setProperty("password", users[0].getPassword());
//            String content = null;
//            result.setProperty("range", "0");
//            Ipgw ipgw = new Ipgw(result);
//            content = ipgw.connect();
//            if (content.contains("网络连接成功") || content.contains("免登录帐号的地址") || content.contains("免费包月时长已经用完") || content.contains("当前连接数超过预定值"))
//                return users[0];
//            return null;
//        }
//
//
//
//
//        @Override
//        protected void onPostExecute(User user) {
//            if (user != null) {
//                UIHelper.ToastMessage("登录成功");
//                AppContextHolder.getAppContext().setCurrentUser(user);
//                Intent intent = new Intent(LoginActivity.this, MainNavigationActivity.class);
//                startActivity(intent);
//                finish();
//            } else {
//                UIHelper.ToastMessage("账户密码不匹配，请重新输入后再登录");
//            }
//        }
//    }


    private class LoginTaskByIAAA extends AsyncTask<User, Void, User> {


        @Override
        protected User doInBackground(User... users) {
            if (users.length == 0) {
                return null;
            }
            User user_test = users[0];
            boolean access = true;

            String url = "https://iaaa.pku.edu.cn/iaaaWS/EuserLogon?WSDL";
            String namespace = "http://pku/iaaa/webservice";
            String methodName = "userLogonCs"; // 函数名
            String soupaction = namespace + "/" + methodName;
            // 指定WebService的命名空间和函数名
            SoapObject soapObject = new SoapObject(namespace, methodName);
            // 设置调用方法参数的值,经测试，此处还变量名好像没关系，貌似和顺序是相关的
            soapObject.addProperty("userID", user_test.getUsername());
            soapObject.addProperty("password", user_test.getPassword());
            soapObject.addProperty("appID", "portalApp");
            String tmp = user_test.getUsername() + "7696baa1fa4ed9679441764a271e556e";
            String md5d = NetHelper.getMd5WithKey(user_test.getUsername(), "7696baa1fa4ed9679441764a271e556e").toLowerCase();
            Log.v("liuyi", user_test.getUsername() +" " + user_test.getPassword() + " " + md5d);
            soapObject.addProperty("messageAbstract", md5d);
            soapObject.addProperty("clientIP", getLocalHostIp());
            HttpTransportSE transport = new HttpTransportSE(url);
            // 版本号向下兼容，SOAP协议版本号，与你要调用的webService中版本号一致
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = soapObject;

            // 经测试，即使访问的是我java发布的webservice，设置为true也可以
            envelope.dotNet = false;
            // 经测试，setoutputsoapobject作用和bodyout一样
            envelope.setOutputSoapObject(soapObject);
            // 使用call方法调用WebService方法

            try {
                transport.call(soupaction, envelope);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            SoapObject sb = (SoapObject) envelope.bodyIn;
            String xmlMessage = sb.getProperty(0).toString(); // 获取从服务器端返回的XML字符串
//            Log.v("liuyi", "前方高能！");
//            Log.v("liuyi", xmlMessage);
            if (xmlMessage.contains("Status=0"))
                return users[0];
            res = xmlMessage;
            return null;
        }

        @Override
        protected void onPostExecute(User user) {
            if (user != null) {
                UIHelper.ToastMessage("登录成功");
                DaoHelper.saveData(DaoHelper.REMBER_PASSWORD_KEY, rememberPassword);
                AppContextHolder.getAppContext().setCurrentUser(user);
                Intent intent = new Intent(LoginActivity.this, MainNavigationActivity.class);
                startActivity(intent);
                finish();
            } else {
                //UIHelper.ToastMessage("账户密码不匹配，请重新输入后再登录");
                UIHelper.ToastMessage(res.substring(res.indexOf("nfo=")+4,res.indexOf("}") - 2));
            }
        }


        // 得到本机ip地址
        public String getLocalHostIp() {
            String ipaddress = "";
            try {
                Enumeration<NetworkInterface> en = NetworkInterface
                        .getNetworkInterfaces();
                // 遍历所用的网络接口
                while (en.hasMoreElements()) {
                    NetworkInterface nif = en.nextElement();// 得到每一个网络接口绑定的所有ip
                    Enumeration<InetAddress> inet = nif.getInetAddresses();
                    // 遍历每一个接口绑定的所有ip
                    while (inet.hasMoreElements()) {
                        InetAddress ip = inet.nextElement();
                        if (!ip.isLoopbackAddress()
                                && InetAddressUtils.isIPv4Address(ip
                                .getHostAddress())) {
                            ipaddress = ip.getHostAddress();
                        }
                    }

                }
            } catch (SocketException e) {
                Log.e("feige", "获取本地ip地址失败");
                e.printStackTrace();
            } finally {
                Log.v("liuyi", "the ip is: " + ipaddress);
                return ipaddress;
            }

        }

    }


}
