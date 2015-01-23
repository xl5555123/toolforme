package com.pku.ipku.api;

import com.pku.ipku.model.person.dto.ScoreDTO;
import com.pku.ipku.model.person.dto.StuInfoDTO;
import com.pku.ipku.model.person.dto.ArrearageStateDTO;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/9.
 */
public interface PersonService {

    public ArrearageStateDTO getArrearageState();
    public StuInfoDTO getStuInfo();
    public List<ScoreDTO> getScores();
}
