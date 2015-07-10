package com.xingliang.toolforme.model.person.studyguide.dto;

import com.google.common.collect.Maps;
import com.xingliang.toolforme.model.person.studyguide.Lesson;
import com.xingliang.toolforme.model.person.studyguide.util.DayClass;

import java.util.Map;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class CurriculumDTO {

    private Map<DayClass, Lesson> lessonMap;

    public CurriculumDTO() {
        lessonMap = Maps.newHashMap();
    }

    public CurriculumDTO(Map<DayClass, Lesson> lessonMap) {
        this.lessonMap = lessonMap;
    }

    public Map<DayClass, Lesson> getLessonMap() {
        return lessonMap;
    }

    public void setLessonMap(Map<DayClass, Lesson> lessonMap) {
        this.lessonMap = lessonMap;
    }
}
