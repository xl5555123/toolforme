package com.pku.ipku.api.net;

import com.pku.ipku.api.StudyGuideService;
import com.pku.ipku.api.mock.pkuInfo.MockCurriculum;
import com.pku.ipku.model.studyguide.dto.CurriculumDTO;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class StudyGuideServiceNetImpl implements StudyGuideService {
    @Override
    public CurriculumDTO getCurriculum() {
        return MockCurriculum.get();
    }
}
