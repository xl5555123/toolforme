package com.pku.portal.ui;

import android.app.Application;

import com.google.common.base.Strings;
import com.pku.portal.model.account.User;
import com.pku.portal.util.AppConfig;
import com.pku.portal.util.DaoHelper;
import com.pku.portal.util.PropertyHelper;

import java.util.UUID;

public class AppContext extends Application {

    public static final int PAGE_SIZE = 20;// 默认分页大小

    public static final String APP_KEY = "?appKey=579d8718c1b911e49c500050568508a5";

    private String CURRENT_USER_KEY = "current-user";
    private User currentUser;

    @Override
    public void onCreate() {
        super.onCreate();
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

    public void deleteCurrentUser() {
        currentUser = null;
        DaoHelper.removeData(CURRENT_USER_KEY);

    }

}