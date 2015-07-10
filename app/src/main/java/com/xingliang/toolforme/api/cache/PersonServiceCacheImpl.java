package com.xingliang.toolforme.api.cache;

import com.google.common.collect.Lists;
import com.xingliang.toolforme.api.PersonService;
import com.xingliang.toolforme.model.person.dto.ArrearageStateDTO;
import com.xingliang.toolforme.model.person.dto.LibBorrowDTO;
import com.xingliang.toolforme.model.person.dto.ScholarShipDTO;
import com.xingliang.toolforme.model.person.dto.ScoreDTO;
import com.xingliang.toolforme.model.person.dto.StuInfoDTO;
import com.xingliang.toolforme.model.person.studyguide.Lesson;
import com.xingliang.toolforme.util.DaoHelper;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/9.
 */
public class PersonServiceCacheImpl implements PersonService {

    @Override
    public ArrearageStateDTO getArrearageState(String userId) throws Exception {
        return DaoHelper.readData("arrearage", ArrearageStateDTO.class);
    }

    @Override
    public StuInfoDTO getStuInfo(String userId) throws Exception {
        return DaoHelper.readData(PERSON_BASE_INFO_URL + userId, StuInfoDTO.class);
    }

    @Override
    public List<ScoreDTO> getScores(String userId) {
        return null;
    }

    @Override
    public List<LibBorrowDTO> getLibBorrowInfo() {
        return Lists.newArrayList();
    }

    @Override
    public List<ScholarShipDTO> getScholarShips() {
        return null;

    }

    @Override
    public List<Lesson> queryLessons(String query) {
        return Lists.newArrayList(DaoHelper.readData(QUERY_LESSON + query, Lesson[].class));
    }

}
