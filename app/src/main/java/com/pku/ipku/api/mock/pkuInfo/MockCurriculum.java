package com.pku.ipku.api.mock.pkuInfo;

import android.graphics.Point;

import com.google.common.collect.Lists;
import com.pku.ipku.model.studyguide.Curriculum;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.model.studyguide.dto.CurriculumDTO;
import com.pku.ipku.model.studyguide.util.ClassIndex;
import com.pku.ipku.model.studyguide.util.WeekIndex;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class MockCurriculum {

    private static Curriculum curriculum;

    public static CurriculumDTO get() {
        if (curriculum == null) {
            init();
        }
        return curriculum.getCurriculumDTO();
    }

    private static void init() {
        curriculum = new Curriculum();

        Lesson lesson = new Lesson();
        lesson.setName("二外日语（一）");
        lesson.setInformation("(备注：三教207 与本科生同上) 每周");
        lesson.setLocation("三教207");
        lesson.setTeacherName("日语老师");
        curriculum.setDayClass(Calendar.MONDAY, ClassIndex.TENTHCLASS, lesson);
        curriculum.setDayClass(Calendar.MONDAY, ClassIndex.ELEVENTHCLASS, lesson);
        curriculum.setDayClass(Calendar.MONDAY, ClassIndex.ELEVENTHCLASS, lesson);
        curriculum.setDayClass(Calendar.THURSDAY, ClassIndex.TENTHCLASS, lesson);
        curriculum.setDayClass(Calendar.THURSDAY, ClassIndex.ELEVENTHCLASS, lesson);
        lesson = new Lesson();
        lesson.setName("美国文化");
        lesson.setInformation("(备注：)  每周");
        lesson.setLocation("电教听力310");
        lesson.setTeacherName("英语老师");
        curriculum.setDayClass(Calendar.THURSDAY, ClassIndex.THIRDCLASS, lesson);
        curriculum.setDayClass(Calendar.THURSDAY, ClassIndex.FOURTHCLASS, lesson);
        lesson = new Lesson();
        lesson.setName("自然辩证法概论");
        lesson.setInformation("(备注：)  每周");
        lesson.setLocation("理教207");
        lesson.setTeacherName("政治老师");
        curriculum.setDayClass(Calendar.THURSDAY, ClassIndex.FIFTHCLASS, lesson);
        curriculum.setDayClass(Calendar.THURSDAY, ClassIndex.SIXTHCLASS, lesson);
        lesson = new Lesson();
        lesson.setName("网络大数据管理理论和应用");
        lesson.setInformation("(备注：)  每周");
        lesson.setLocation("三教406");
        lesson.setTeacherName("计算机老师");
        curriculum.setDayClass(Calendar.THURSDAY, ClassIndex.SEVENTHCLASS, lesson);
        curriculum.setDayClass(Calendar.THURSDAY, ClassIndex.EIGHTHCLASS, lesson);
        curriculum.setDayClass(Calendar.THURSDAY, ClassIndex.NINTHCLASS, lesson);
        lesson = new Lesson();
        lesson.setName("高级系统软件技术");
        lesson.setInformation("(备注：学术型本部上课) 每周");
        lesson.setLocation("二教314");
        lesson.setTeacherName("陈向群");
        curriculum.setDayClass(Calendar.FRIDAY, ClassIndex.TENTHCLASS, lesson);
        curriculum.setDayClass(Calendar.FRIDAY, ClassIndex.ELEVENTHCLASS, lesson);
        curriculum.setDayClass(Calendar.FRIDAY, ClassIndex.TWELVETHCLASS, lesson);
    }
}
