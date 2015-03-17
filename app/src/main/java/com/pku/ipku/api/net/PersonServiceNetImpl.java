package com.pku.ipku.api.net;

import com.pku.ipku.api.PersonService;
import com.pku.ipku.api.mock.person.MockArrearageState;
import com.pku.ipku.api.mock.person.MockLibBorrowInfo;
import com.pku.ipku.api.mock.person.MockScholarShip;
import com.pku.ipku.api.mock.person.MockScore;
import com.pku.ipku.api.mock.studyGuide.MockLesson;
import com.pku.ipku.api.util.NetHelper;
import com.pku.ipku.model.person.dto.ArrearageStateDTO;
import com.pku.ipku.model.person.dto.LibBorrowDTO;
import com.pku.ipku.model.person.dto.ScholarShipDTO;
import com.pku.ipku.model.person.dto.ScoreDTO;
import com.pku.ipku.model.person.dto.StuInfoDTO;
import com.pku.ipku.model.studyguide.Lesson;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/9.
 */
public class PersonServiceNetImpl implements PersonService {
    @Override
    public ArrearageStateDTO getArrearageState() {
        return MockArrearageState.get();
    }

    @Override
    public StuInfoDTO getStuInfo(int userId) throws Exception {
        return NetHelper.getForObjectWithAuth(PERSON_BASE_INFO_URL, StuInfoDTO.class, userId);
    }

    @Override
    public List<ScoreDTO> getScores() {
        return MockScore.get();
    }

    public List<LibBorrowDTO> getLibBorrowInfo() {
        return MockLibBorrowInfo.get();
    }

    public List<ScholarShipDTO> getScholarShips() {
        return MockScholarShip.get();
    }

    @Override
    public List<Lesson> queryLessons(String query) {
        return MockLesson.getList();
    }

}
