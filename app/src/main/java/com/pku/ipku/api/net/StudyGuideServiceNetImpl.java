package com.pku.ipku.api.net;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.pku.ipku.api.StudyGuideService;
import com.pku.ipku.api.mock.pkuInfo.MockCurriculum;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.model.studyguide.dto.CurriculumDTO;
import com.pku.ipku.model.studyguide.util.DayClass;

import java.util.List;
import java.util.Set;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class StudyGuideServiceNetImpl implements StudyGuideService {
    @Override
    public CurriculumDTO getCurriculum() {
        return MockCurriculum.get();
    }
}
