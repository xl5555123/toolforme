package com.pku.ipku.api.cache;

import com.pku.ipku.api.StudyGuideService;
import com.pku.ipku.api.mock.pkuInfo.MockCurriculum;
import com.pku.ipku.model.studyguide.Curriculum;
import com.pku.ipku.model.studyguide.dto.CurriculumDTO;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class StudyGuideServiceCacheImpl implements StudyGuideService {
    @Override
    public CurriculumDTO getCurriculum() {
        return MockCurriculum.get();
    }
}
