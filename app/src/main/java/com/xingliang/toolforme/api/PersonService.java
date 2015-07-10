package com.xingliang.toolforme.api;


import com.xingliang.toolforme.api.util.NetHelper;
import com.xingliang.toolforme.model.person.dto.ArrearageStateDTO;
import com.xingliang.toolforme.model.person.dto.LibBorrowDTO;
import com.xingliang.toolforme.model.person.dto.ScholarShipDTO;
import com.xingliang.toolforme.model.person.dto.ScoreDTO;
import com.xingliang.toolforme.model.person.dto.StuInfoDTO;
import com.xingliang.toolforme.model.person.studyguide.Lesson;

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
