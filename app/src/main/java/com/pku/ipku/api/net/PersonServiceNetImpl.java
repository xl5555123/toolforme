package com.pku.ipku.api.net;

import com.pku.ipku.api.PersonService;
import com.pku.ipku.api.mock.person.MockArrearageState;
import com.pku.ipku.api.mock.person.MockScore;
import com.pku.ipku.api.mock.person.MockStuInfo;
import com.pku.ipku.model.person.dto.ScoreDTO;
import com.pku.ipku.model.person.dto.StuInfoDTO;
import com.pku.ipku.model.person.dto.ArrearageStateDTO;

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
    public StuInfoDTO getStuInfo(){
        return MockStuInfo.get();
    }

    @Override
    public List<ScoreDTO> getScores(){
        return MockScore.get();
    }
}
