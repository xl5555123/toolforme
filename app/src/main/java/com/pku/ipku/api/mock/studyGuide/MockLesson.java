package com.pku.ipku.api.mock.studyGuide;

import com.google.common.collect.Lists;
import com.pku.ipku.model.studyguide.Lesson;

import java.util.List;
import java.util.Random;

/**
 * Created by XingLiang on 2015/1/22.
 */
public class MockLesson {

    private final static Lesson MOCKED_LESSON = new Lesson(12, "高级系统软件技术", "陈向群", "二教314", "哈啊哈哈", "周五晚", "软件与微电子学院");

    public static Lesson get() {
        return MOCKED_LESSON;
    }

    public static List<Lesson> getList() {
        List<Lesson> lessons = Lists.newArrayList();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(10); i++) {
            lessons.add(get());
        }
        return lessons;
    }
}
