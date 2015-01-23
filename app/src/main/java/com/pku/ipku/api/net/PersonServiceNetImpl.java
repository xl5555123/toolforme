package com.pku.ipku.api.net;

import com.pku.ipku.api.PersonService;
import com.pku.ipku.api.mock.person.MockArrearageState;
import com.pku.ipku.api.mock.person.MockLibBorrowInfo;
import com.pku.ipku.api.mock.person.MockScholarShip;
import com.pku.ipku.model.person.dto.ArrearageStateDTO;
import com.pku.ipku.model.person.dto.LibBorrowDTO;
import com.pku.ipku.model.person.dto.ScholarShipDTO;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/9.
 */
public class PersonServiceNetImpl implements PersonService {
    @Override
    public ArrearageStateDTO getArrearageState() {
        return MockArrearageState.get();
    }
    public List<LibBorrowDTO> getLibBorrowInfo(){return MockLibBorrowInfo.get();}
    public List<ScholarShipDTO> getScholarShips(){return MockScholarShip.get();}
}
