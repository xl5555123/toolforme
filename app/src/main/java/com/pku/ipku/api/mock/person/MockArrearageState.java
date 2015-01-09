package com.pku.ipku.api.mock.person;

import com.pku.ipku.model.person.dto.ArrearageStateDTO;

/**
 * Created by XingLiang on 2015/1/9.
 */
public class MockArrearageState {

    public static ArrearageStateDTO get() {
        ArrearageStateDTO arrearageStateDTO = new ArrearageStateDTO();
        arrearageStateDTO.setLibraryFee(10);
        arrearageStateDTO.setNetBalance(5.00);
        arrearageStateDTO.setSchoolCardBalance(99.99);
        return arrearageStateDTO;
    }
}
