package com.pku.portal.util;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;


public class AppConfig {

    public final static String CONF_APP_UNIQUEID = "APP_UNIQUEID";
    public final static String CONF_COOKIE = "cookie";
    public final static String CONF_LOAD_IMAGE = "perf_loadimage";
    private final static String APP_CONFIG = "config";
    private static AppConfig appConfig;
    private Context mContext;

    public static AppConfig getAppConfig() {
        if (appConfig == null) {
            appConfig = new AppConfig();
            appConfig.mContext = AppContextHolder.getAppContext();
        }
        return appConfig;
    }


    Properties getProps() {
        FileInputStream fis = null;
        Properties props = new Properties();
        try {
            //读取files目录下的config
            //fis = activity.openFileInput(APP_CONFIG);

            //读取app_config目录下的config
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            fis = new FileInputStream(dirConf.getPath() + File.separator + APP_CONFIG);

            props.load(fis);
        } catch (Exception e) {
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return props;
    }

    private void setProps(Properties p) {
        FileOutputStream fos = null;
        try {
            //把config建在files目录下
            //fos = activity.openFileOutput(APP_CONFIG, Context.MODE_PRIVATE);

            //把config建在(自定义)app_config的目录下
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            File conf = new File(dirConf, APP_CONFIG);
            fos = new FileOutputStream(conf);

            p.store(fos, null);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    public void set(Properties ps) {
        Properties props = getProps();
        props.putAll(ps);
        setProps(props);
    }

    public void set(String key, String value) {
        Properties props = getProps();
        props.setProperty(key, value);
        setProps(props);
    }


    public String get(String key) {
        Properties props = getProps();
        return (props != null) ? props.getProperty(key) : null;
    }

    public void remove(String... key) {
        Properties props = getProps();
        for (String k : key)
            props.remove(k);
        setProps(props);
    }
}
