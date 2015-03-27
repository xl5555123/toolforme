package com.pku.ipku.api;


import com.pku.ipku.api.util.NetHelper;
import com.pku.ipku.model.person.dto.ArrearageStateDTO;
import com.pku.ipku.model.person.dto.LibBorrowDTO;
import com.pku.ipku.model.person.dto.ScholarShipDTO;
import com.pku.ipku.model.person.dto.ScoreDTO;
import com.pku.ipku.model.person.dto.StuInfoDTO;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.ui.AppContext;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/9.
 */
public interface PersonService {

    public static final String PERSON_BASE_INFO_URL = "/svcpub/svc/pro/person/baseinfo";

    public static final String PERSON_COURSE_TABLE_URL = "/svcpub/svc/pro/student/coursetable";

    public static final String QUERY_LESSON = "/svcpub/svc/pub/course/list?courseName=%s&appKey=" + NetHelper.APP_KEY;;

    public ArrearageStateDTO getArrearageState();

    public StuInfoDTO getStuInfo(int userId) throws Exception;

    public List<ScoreDTO> getScores();

    public List<LibBorrowDTO> getLibBorrowInfo();

    public List<ScholarShipDTO> getScholarShips();

    public List<Lesson> queryLessons(String query);
}
