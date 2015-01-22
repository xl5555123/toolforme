package com.pku.ipku.api.cache;

import com.pku.ipku.api.PersonService;
import com.pku.ipku.api.mock.person.MockArrearageState;
import com.pku.ipku.api.mock.person.MockStuInfo;
import com.pku.ipku.dto.StuInfoDTO;
import com.pku.ipku.model.person.ArrearageState;
import com.pku.ipku.model.person.dto.ArrearageStateDTO;

/**
 * Created by XingLiang on 2015/1/9.
 */
public class PersonServiceCacheImpl implements PersonService {
    @Override
    public ArrearageStateDTO getArrearageState() {
        return MockArrearageState.get();
    }

    @Override
    public StuInfoDTO getStuInfo(){
        return MockStuInfo.get();
    }
}
