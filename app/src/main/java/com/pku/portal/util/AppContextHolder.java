package com.pku.portal.util;

import com.pku.portal.ui.AppContext;

public class AppContextHolder {

    private static AppContext appContext;

    public static AppContext getAppContext() {
        //when invoke this function, appContext should not be null
        assert appContext != null;
        return appContext;
    }

    public static void setAppContext(AppContext appContext) {
        AppContextHolder.appContext = appContext;
    }

}
