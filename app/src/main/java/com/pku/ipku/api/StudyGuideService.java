package com.pku.ipku.api;

import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.model.studyguide.dto.CurriculumDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by XingLiang on 2015/1/7.
 */
public interface StudyGuideService {

    public CurriculumDTO getCurriculum();

    public Map<String, List<Integer>> getFreeClassrooms(List<String> seletedBuidings, List<String> selectedTime);
}
