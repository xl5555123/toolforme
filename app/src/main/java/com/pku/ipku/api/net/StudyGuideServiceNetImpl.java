package com.pku.ipku.api.net;

import com.pku.ipku.api.StudyGuideService;
import com.pku.ipku.api.mock.studyGuide.MockFreeClassrooms;
import com.pku.ipku.api.mock.studyGuide.MockLesson;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.model.studyguide.dto.CurriculumDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class StudyGuideServiceNetImpl implements StudyGuideService {
    @Override
    public CurriculumDTO getCurriculum() {
        //return MockCurriculum.get();
        return null;
    }

    @Override
    public Map<String, List<Integer>> getFreeClassrooms(List<String> seletedBuidings, List<String> selectedTime) {
        return MockFreeClassrooms.get();
    }
}
