package com.pku.ipku.api.mock.person;

import com.pku.ipku.model.person.dto.LibBorrowDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2015/1/19.
 */
public class MockLibBorrowInfo {
    private static List<LibBorrowDTO> borrowDTOs = new ArrayList<LibBorrowDTO>() {
        {
            add(new LibBorrowDTO("具体数学——计算机科学基础", "TP301.6/150", "葛立恒", "2014-12-30", "2015-1-30"));
            add(new LibBorrowDTO("具体数学——计算机科学基础", "TP301.6/150", "葛立恒", "2014-12-30", "2015-1-30"));
            add(new LibBorrowDTO("具体数学——计算机科学基础", "TP301.6/150", "葛立恒", "2014-12-30", "2015-1-30"));
            add(new LibBorrowDTO("具体数学——计算机科学基础", "TP301.6/150", "葛立恒", "2014-12-30", "2015-1-30"));
            add(new LibBorrowDTO("具体数学——计算机科学基础", "TP301.6/150", "葛立恒", "2014-12-30", "2015-1-30"));
        }
    };

    public static List<LibBorrowDTO> get() {
        return borrowDTOs;
    }
}
