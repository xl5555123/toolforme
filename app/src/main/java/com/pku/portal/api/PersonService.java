package com.pku.portal.api;


import com.pku.portal.api.util.NetHelper;
import com.pku.portal.model.person.dto.ArrearageStateDTO;
import com.pku.portal.model.person.dto.LibBorrowDTO;
import com.pku.portal.model.person.dto.ScholarShipDTO;
import com.pku.portal.model.person.dto.ScoreDTO;
import com.pku.portal.model.person.dto.StuInfoDTO;
import com.pku.portal.model.person.studyguide.Lesson;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/9.
 */
public interface PersonService {

    public static final String PERSON_NETWORK_FEE = "/svcpub/svc/pro/finance/netbalance";

    public static final String PERSON_CARD_REMAIN = "/svcpub/svc/pro/finance/cardbalance";

    public static final String PERSON_BASE_INFO_URL = "/svcpub/svc/pro/person/baseinfo";

    public static final String PERSON_COURSE_TABLE_URL = "/svcpub/svc/pro/student/coursetable";

    public static final String QUERY_LESSON = "/svcpub/svc/pub/course/list?courseName=%s&appKey=" + NetHelper.APP_KEY;

    public static final String GET_SCORE = "/svcpub/svc/pro/student/scores";


    public ArrearageStateDTO getArrearageState(String userId) throws Exception;

    public StuInfoDTO getStuInfo(String userId) throws Exception;

    public List<ScoreDTO> getScores(String userId);

    public List<LibBorrowDTO> getLibBorrowInfo();

    public List<ScholarShipDTO> getScholarShips();

    public List<Lesson> queryLessons(String query);
}
