package com.pku.ipku.api;


import com.pku.ipku.model.person.dto.ArrearageStateDTO;
import com.pku.ipku.model.person.dto.LibBorrowDTO;
import com.pku.ipku.model.person.dto.ScholarShipDTO;
import com.pku.ipku.model.person.dto.ScoreDTO;
import com.pku.ipku.model.person.dto.StuInfoDTO;
import com.pku.ipku.model.studyguide.Lesson;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/9.
 */
public interface PersonService {

    public ArrearageStateDTO getArrearageState();

    public StuInfoDTO getStuInfo();

    public List<ScoreDTO> getScores();

    public List<LibBorrowDTO> getLibBorrowInfo();

    public List<ScholarShipDTO> getScholarShips();

    public List<Lesson> queryLessons(String query);
}
