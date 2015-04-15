package com.pku.ipku.api.cache;

import com.google.common.collect.Lists;
import com.pku.ipku.api.PersonService;
import com.pku.ipku.api.mock.person.MockLibBorrowInfo;
import com.pku.ipku.api.mock.person.MockScholarShip;
import com.pku.ipku.model.person.dto.ArrearageStateDTO;
import com.pku.ipku.model.person.dto.LibBorrowDTO;
import com.pku.ipku.model.person.dto.ScholarShipDTO;
import com.pku.ipku.model.person.dto.ScoreDTO;
import com.pku.ipku.model.person.dto.StuInfoDTO;
import com.pku.ipku.model.studyguide.Lesson;
import com.pku.ipku.util.DaoHelper;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/9.
 */
public class PersonServiceCacheImpl implements PersonService {

    @Override
    public ArrearageStateDTO getArrearageState(int userId) throws Exception {
        return DaoHelper.readData("arrearage", ArrearageStateDTO.class);
    }

    @Override
    public StuInfoDTO getStuInfo(int userId) throws Exception {
        return DaoHelper.readData(PERSON_BASE_INFO_URL + userId, StuInfoDTO.class);
    }

    @Override
    public List<ScoreDTO> getScores(int userId) {
        return null;
    }

    @Override
    public List<LibBorrowDTO> getLibBorrowInfo() {
        return MockLibBorrowInfo.get();
    }

    @Override
    public List<ScholarShipDTO> getScholarShips() {
        return MockScholarShip.get();

    }

    @Override
    public List<Lesson> queryLessons(String query) {
        return Lists.newArrayList(DaoHelper.readData(QUERY_LESSON + query, Lesson[].class));
    }

}
