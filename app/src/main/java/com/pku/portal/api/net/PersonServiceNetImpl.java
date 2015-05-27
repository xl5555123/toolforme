package com.pku.portal.api.net;

import com.google.common.collect.Lists;
import com.pku.portal.api.PersonService;
import com.pku.portal.api.util.NetHelper;
import com.pku.portal.model.person.dto.ArrearageStateDTO;
import com.pku.portal.model.person.dto.BalanceDTO;
import com.pku.portal.model.person.dto.LibBorrowDTO;
import com.pku.portal.model.person.dto.ScholarShipDTO;
import com.pku.portal.model.person.dto.ScoreDTO;
import com.pku.portal.model.person.dto.StuInfoDTO;
import com.pku.portal.model.person.studyguide.Lesson;
import com.pku.portal.util.DaoHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XingLiang on 2015/1/9.
 */
public class PersonServiceNetImpl implements PersonService {
    @Override
    public ArrearageStateDTO getArrearageState(String userId) throws Exception {
        ArrearageStateDTO arrearageStateDTO = new ArrearageStateDTO();
        BalanceDTO card = NetHelper.getForObjectWithAuth(PERSON_CARD_REMAIN, BalanceDTO.class, userId);
        BalanceDTO network = NetHelper.getForObjectWithAuth(PERSON_NETWORK_FEE, BalanceDTO.class, userId);
        if (card != null) {
            arrearageStateDTO.setSchoolCardBalance(card.getBalance());
        }
        if (network != null) {
            arrearageStateDTO.setNetBalance(network.getBalance());
        }
        DaoHelper.saveData("arrearage", arrearageStateDTO);
        return arrearageStateDTO;
    }

    @Override
    public StuInfoDTO getStuInfo(String userId) throws Exception {
        return NetHelper.getForObjectWithAuth(PERSON_BASE_INFO_URL, StuInfoDTO.class, userId);
    }

    @Override
    public List<ScoreDTO> getScores(String userId) {
        String uri = String.format(GET_SCORE);
        try {
            return Lists.newArrayList(NetHelper.getForObjectWithAuth(uri, ScoreDTO[].class, userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LibBorrowDTO> getLibBorrowInfo() {
        return new ArrayList<LibBorrowDTO>();
    }

    public List<ScholarShipDTO> getScholarShips() {
        return new ArrayList<ScholarShipDTO>();
    }

    @Override
    public List<Lesson> queryLessons(String query) {
        String uri = String.format(QUERY_LESSON, query);
        return Lists.newArrayList(NetHelper.getForObject(uri, Lesson[].class));
    }

}
