package com.pku.ipku.api.mock.person;

import com.pku.ipku.model.person.dto.ScholarShipDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2015/1/19.
 */
public class MockScholarShip {
    private static List<ScholarShipDTO> scholarshipDTOs = new ArrayList<ScholarShipDTO>(){
        {
            add(new ScholarShipDTO("五四奖学金","2014-2015年度",5000));
            add(new ScholarShipDTO("五四奖学金","2014-2015年度",5000));
            add(new ScholarShipDTO("五四奖学金","2014-2015年度",5000));
            add(new ScholarShipDTO("五四奖学金","2014-2015年度",5000));
        }
    };

    public static List<ScholarShipDTO> get(){
        return scholarshipDTOs;
    }
}
