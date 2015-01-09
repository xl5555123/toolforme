package com.pku.ipku.model.studyguide;

import android.graphics.Point;
import android.support.v4.app.Fragment;

import com.pku.ipku.model.studyguide.dto.CurriculumDTO;
import com.pku.ipku.model.studyguide.util.DayClass;
import com.pku.ipku.model.type.Fragmentable;
import com.pku.ipku.ui.studyGuide.CurriculumFragment;

import java.util.List;
import java.util.Map;

/**
 * Created by XingLiang on 2014/12/16.
 */
public class Curriculum implements Fragmentable {

    public Curriculum() {
        curriculumDTO = new CurriculumDTO();
    }

    public void setDayClass(Integer day, Integer classIndex, Lesson lesson) {
        DayClass point = new DayClass(day, classIndex);
        Map<DayClass, Lesson> lessonMap = curriculumDTO.getLessonMap();
        lessonMap.put(point, lesson);
        curriculumDTO.setLessonMap(lessonMap);
    }

    public Lesson getDayClass(Integer day, Integer classIndex) {
        return curriculumDTO.getLessonMap().get(new Point(day, classIndex));
    }

    private CurriculumDTO curriculumDTO;

    public Curriculum(CurriculumDTO curriculumDTO) {
        this.curriculumDTO = curriculumDTO;
    }

    @Override
    public Fragment getAttachedFragment(String[] args) {
        return new CurriculumFragment(curriculumDTO);
    }

    @Override
    public Integer getMenuIcon() {
        return null;
    }

    @Override
    public String getType() {
        return "curriculum";
    }

    @Override
    public String getChineseName() {
        return "课程表";
    }

    public CurriculumDTO getCurriculumDTO() {
        return curriculumDTO;
    }

    public void setCurriculumDTO(CurriculumDTO curriculumDTO) {
        this.curriculumDTO = curriculumDTO;
    }
}
