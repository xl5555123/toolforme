package com.pku.ipku.model.studyguide.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class WeekIndex {
    public final static int MONDAY = Calendar.MONDAY;
    public final static int TUESDAY = Calendar.TUESDAY;
    public final static int WEDNESDAY = Calendar.WEDNESDAY;
    public final static int THURSDAY = Calendar.THURSDAY;
    public final static int FRIDAY = Calendar.FRIDAY;
    public final static int SATURDY = Calendar.SATURDAY;
    public final static int SUNDAY = Calendar.SUNDAY;

    private final static Map<Integer, String> map = new HashMap<Integer, String>() {
        {
            put(Calendar.MONDAY, "周一");
            put(Calendar.TUESDAY, "周二");
            put(Calendar.WEDNESDAY, "周三");
            put(Calendar.THURSDAY, "周四");
            put(Calendar.FRIDAY, "周五");
            put(Calendar.SATURDAY, "周六");
            put(Calendar.SUNDAY, "周日");
        }
    };

    public static String getChineseName(Integer weekIndex) {
        return map.get(weekIndex);
    }
}
