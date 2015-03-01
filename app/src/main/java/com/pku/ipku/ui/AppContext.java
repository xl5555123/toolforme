package com.pku.ipku.ui;

import android.app.Application;

import com.google.common.base.Strings;
import com.pku.ipku.model.account.User;
import com.pku.ipku.util.AppConfig;
import com.pku.ipku.util.AppException;
import com.pku.ipku.util.DaoHelper;
import com.pku.ipku.util.PropertyHelper;

import java.util.UUID;

public class AppContext extends Application {

    public static final int PAGE_SIZE = 20;// 默认分页大小

    private String CURRENT_USER_KEY = "current-user";
    private User currentUser;

    @Override
    public void onCreate() {
        super.onCreate();
        // 注册App异常崩溃处理器
        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
    }

    /**
     * 获取App唯一标识
     *
     * @return
     */
    public String getAppId() {
        String uniqueID = PropertyHelper.getProperty(AppConfig.CONF_APP_UNIQUEID);
        if (Strings.isNullOrEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            PropertyHelper.setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    public User getCurrentUser() {
        if (currentUser != null) {
            return currentUser;
        }
        currentUser = DaoHelper.readData(CURRENT_USER_KEY, User.class);
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
        DaoHelper.saveData(CURRENT_USER_KEY, user);
    }

}