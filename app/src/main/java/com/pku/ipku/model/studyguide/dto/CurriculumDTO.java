package com.pku.ipku.model.studyguide.dto;

import android.graphics.Point;

import com.google.common.collect.Maps;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.model.studyguide.util.DayClass;

import java.util.List;
import java.util.Map;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class CurriculumDTO {

    public CurriculumDTO() {
        lessonMap = Maps.newHashMap();
    }

    public CurriculumDTO(Map<DayClass, Lesson> lessonMap) {
        this.lessonMap = lessonMap;
    }

    private Map<DayClass, Lesson> lessonMap;

    public Map<DayClass, Lesson> getLessonMap() {
        return lessonMap;
    }

    public void setLessonMap(Map<DayClass, Lesson> lessonMap) {
        this.lessonMap = lessonMap;
    }
}
