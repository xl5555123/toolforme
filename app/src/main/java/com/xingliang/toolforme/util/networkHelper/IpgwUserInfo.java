package com.xingliang.toolforme.util.networkHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class IpgwUserInfo {

    public static Properties parseUserInfo() {
        String userhome = System.getProperty("user.home");
        File ipgwrc = new File(userhome, ".ipgwrc");

        Properties result = new Properties();
        try {
            FileInputStream ipgwrcinputstream = new FileInputStream(ipgwrc);
            result.load(ipgwrcinputstream);
            ipgwrcinputstream.close();
            return result;
        } catch (IOException e) {
            System.out.println("读取.ipgwrc文件发生错误");
            createIpgwrc();
            return null;
        }
    }

    public static void createIpgwrc() {
        String userhome = System.getProperty("user.home");
        File ipgwrc = new File(userhome, ".ipgwrc");

        Properties result = new Properties();
        if (!ipgwrc.exists()) {
            System.out.println(userhome + ".ipgwrc文件不存在，正在新建");
            try {
                FileOutputStream ipgwrcoutputstream = new FileOutputStream(ipgwrc);
                String comments = "uid:student number\t" + "range:1(international), 2(domestic)";
                result.setProperty("uid", "********");
                result.setProperty("password", "********");
                result.setProperty("range", "2");
                result.store(ipgwrcoutputstream, comments);
                ipgwrcoutputstream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
