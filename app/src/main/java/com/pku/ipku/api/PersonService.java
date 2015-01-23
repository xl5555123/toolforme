package com.pku.ipku.api;

import com.pku.ipku.model.person.dto.ArrearageStateDTO;
import com.pku.ipku.model.person.dto.LibBorrowDTO;
import com.pku.ipku.model.person.dto.ScholarshipDTO;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/9.
 */
public interface PersonService {

    public ArrearageStateDTO getArrearageState();
    public List<LibBorrowDTO> getLibBorrowInfo();
    public List<ScholarshipDTO> getScholarShips();
}
