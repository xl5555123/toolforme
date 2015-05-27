package com.pku.portal.api.net;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pku.portal.api.StudyGuideService;
import com.pku.portal.api.util.NetHelper;
import com.pku.portal.model.person.freeclass.ClassroomDTO;
import com.pku.portal.model.person.studyguide.dto.CurriculumDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by XingLiang on 2015/1/7.
 */
public class StudyGuideServiceNetImpl implements StudyGuideService {
    @Override
    public CurriculumDTO getCurriculum() {
        //return MockCurriculum.get();
        return null;
    }

    @Override
    public Map<String, List<String>> getFreeClassrooms(List<String> seletedBuidings, List<Integer> selectedTime) {
        Map<String, List<String>> result = Maps.newHashMap();
        for (String building : seletedBuidings) {
            String uri = String.format(GET_FREE_CLASSROOM, building);
            List<ClassroomDTO> classroomDTOs = Lists.newArrayList(NetHelper.getForObject(uri, ClassroomDTO[].class));
            result.put(building, getClassroomInABuilding(building, classroomDTOs, selectedTime));
        }

        return result;
    }

    private List<String> getClassroomInABuilding(String buildingName, List<ClassroomDTO> classroomDTOs, List<Integer> selectedTime) {
        List<String> result = Lists.newArrayList();
        for (ClassroomDTO classroomDTO : classroomDTOs) {
            if (isFree(classroomDTO, selectedTime)) {
                String classNumber = classroomDTO.getRoom().replace(buildingName, "");
                result.add(classNumber);
            }
        }
        return result;
    }

    private boolean isFree(ClassroomDTO classroomDTO, List<Integer> selectedTime) {
        List<String> c;
        c = Lists.newArrayList();
        c.add(classroomDTO.getC1());
        c.add(classroomDTO.getC2());
        c.add(classroomDTO.getC3());
        c.add(classroomDTO.getC4());
        c.add(classroomDTO.getC5());
        c.add(classroomDTO.getC6());
        c.add(classroomDTO.getC7());
        c.add(classroomDTO.getC8());
        c.add(classroomDTO.getC9());
        c.add(classroomDTO.getC10());
        c.add(classroomDTO.getC11());
        c.add(classroomDTO.getC12());
        for (Integer time : selectedTime) {
            String hasClass = c.get(time - 1);
            if (hasClass != null && hasClass.length() != 0) {
                return false;
            }
        }
        return true;
    }
}
