package com.pku.portal.api;

import com.pku.portal.model.person.studyguide.dto.CurriculumDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by XingLiang on 2015/1/7.
 */
public interface StudyGuideService {

    public final static String GET_FREE_CLASSROOM = "/svcpub/svc/pub/classroom/today?appKey=579d8718c1b911e49c500050568508a5&buildingName=%s";

    public CurriculumDTO getCurriculum();

    public Map<String, List<String>> getFreeClassrooms(List<String> seletedBuidings, List<Integer> selectedTime);
}
