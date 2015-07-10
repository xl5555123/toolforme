package com.xingliang.toolforme.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import com.google.common.base.Strings;
import com.xingliang.toolforme.ui.AppContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class SystemHelper {

    public final static String FILE_SAVE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/onboard/";

    public static final int NET_TYPE_WIFI = 0x01;
    private static final int NET_TYPE_WAP = 0x02;
    private static final int NET_TYPE_NET = 0x03;

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */
    @SuppressLint("DefaultLocale")
    public static int getNetworkType() {
        int netType = 0;
        AppContext appContext = AppContextHolder.getAppContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!Strings.isNullOrEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NET_TYPE_NET;
                } else {
                    netType = NET_TYPE_WAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NET_TYPE_WIFI;
        }
        return netType;
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public static PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            AppContext appContext = AppContextHolder.getAppContext();
            info = appContext.getPackageManager().getPackageInfo(appContext.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    /**
     * 判断缓存是否存在
     *
     * @param cacheFile
     * @return
     */
    private static boolean isExistDataCache(String cacheFile) {
        boolean exist = false;
        AppContext appContext = AppContextHolder.getAppContext();
        File data = appContext.getFileStreamPath(cacheFile);
        if (data.exists())
            exist = true;
        return exist;
    }

    /**
     * 读取对象
     *
     * @param file
     * @return
     * @throws java.io.IOException
     */
    public static Serializable readObject(String file) {
        if (!isExistDataCache(file))
            return null;
        AppContext appContext = AppContextHolder.getAppContext();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = appContext.openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
            // 反序列化失败 - 删除缓存文件
            if (e instanceof InvalidClassException) {
                File data = appContext.getFileStreamPath(file);
                data.delete();
            }
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static Boolean haveNetwork() {
        ConnectivityManager connect = (ConnectivityManager) AppContextHolder.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connect.getActiveNetworkInfo();
        if (null == networkInfo) {
            return false;
        }
        return true;
    }


}
