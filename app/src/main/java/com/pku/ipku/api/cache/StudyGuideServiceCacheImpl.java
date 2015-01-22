package com.pku.ipku.api.cache;

import com.pku.ipku.api.StudyGuideService;
import com.pku.ipku.api.mock.studyGuide.MockCurriculum;
import com.pku.ipku.api.mock.studyGuide.MockFreeClassrooms;
import com.pku.ipku.api.mock.studyGuide.MockLesson;
import com.pku.ipku.model.studyguide.FreeClassroom;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.model.studyguide.dto.CurriculumDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class StudyGuideServiceCacheImpl implements StudyGuideService {
    @Override
    public CurriculumDTO getCurriculum() {
        return MockCurriculum.get();
    }

    @Override
    public Map<String, List<Integer>> getFreeClassrooms(List<String> seletedBuidings, List<String> selectedTime) {
        return MockFreeClassrooms.get();
    }

    @Override
    public Lesson queryLesson(String year, String term, String lessonName) {
        return MockLesson.get();
    }
}
