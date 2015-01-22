package com.pku.ipku.api;

import com.pku.ipku.dto.StuInfoDTO;
import com.pku.ipku.model.person.ArrearageState;
import com.pku.ipku.model.person.dto.ArrearageStateDTO;

/**
 * Created by XingLiang on 2015/1/9.
 */
public interface PersonService {

    public ArrearageStateDTO getArrearageState();
    public StuInfoDTO getStuInfo();
}
