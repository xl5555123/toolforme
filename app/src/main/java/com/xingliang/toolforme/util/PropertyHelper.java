package com.xingliang.toolforme.util;

import com.google.common.base.Strings;

import java.util.Properties;

public class PropertyHelper {

    public static String getProperty(String key) {
        return AppConfig.getAppConfig().get(key);
    }

    public static void removeProperty(String... key) {
        AppConfig.getAppConfig().remove(key);
    }

    public static void setProperties(Properties ps) {
        AppConfig.getAppConfig().set(ps);
    }

    public static void setProperty(String key, String value) {
        AppConfig.getAppConfig().set(key, value);
    }

    /**
     * 是否加载显示文章图片
     *
     * @return
     */
    public static boolean isLoadImage() {
        String is_load_image = getProperty(AppConfig.CONF_LOAD_IMAGE);
        // 默认是加载的
        if (Strings.isNullOrEmpty(is_load_image))
            return true;
        else
            return Boolean.parseBoolean(is_load_image);
    }
}
