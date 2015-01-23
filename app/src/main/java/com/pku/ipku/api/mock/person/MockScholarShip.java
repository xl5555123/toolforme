package com.pku.ipku.api.mock.person;

import com.pku.ipku.model.person.dto.ScholarshipDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2015/1/19.
 */
public class MockScholarShip {
    private static List<ScholarshipDTO> scholarshipDTOs = new ArrayList<ScholarshipDTO>(){
        {
            add(new ScholarshipDTO("五四奖学金","2014-2015年度",5000));
            add(new ScholarshipDTO("五四奖学金","2014-2015年度",5000));
            add(new ScholarshipDTO("五四奖学金","2014-2015年度",5000));
            add(new ScholarshipDTO("五四奖学金","2014-2015年度",5000));
        }
    };

    public static List<ScholarshipDTO> get(){
        return scholarshipDTOs;
    }
}
