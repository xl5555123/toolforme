package com.pku.ipku.model.freeclass;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by XingLiang on 2015/3/28.
 */
public class Classroom extends ClassroomDTO {

    private List<String> c;

    public Classroom(ClassroomDTO classroomDTO) {
        c = Lists.newArrayList();
        c.add(getC1());
        c.add(getC2());
        c.add(getC3());
        c.add(getC4());
        c.add(getC5());
        c.add(getC6());
        c.add(getC7());
        c.add(getC8());
        c.add(getC9());
        c.add(getC10());
        c.add(getC11());
        c.add(getC12());
    }

    /**
     * 返回此classroom是否可以自习
     *
     * @param times 需要自习的节数
     * @return
     */
    public Boolean isFree(List<Integer> times) {
        for (Integer time : times) {
            String hasClass = c.get(time - 1);
            if (hasClass != null || hasClass.length() != 0) {
                return false;
            }
        }
        return true;
    }
}
