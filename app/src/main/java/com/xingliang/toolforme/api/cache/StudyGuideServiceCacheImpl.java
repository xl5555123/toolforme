package com.xingliang.toolforme.api.cache;

import com.xingliang.toolforme.api.StudyGuideService;
import com.xingliang.toolforme.model.person.studyguide.dto.CurriculumDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class StudyGuideServiceCacheImpl implements StudyGuideService {
    @Override
    public CurriculumDTO getCurriculum() {
        //return MockCurriculum.get();
        return null;
    }


    @Override
    public Map<String, List<String>> getFreeClassrooms(List<String> seletedBuidings, List<Integer> selectedTime) {
        return null;
    }
}
