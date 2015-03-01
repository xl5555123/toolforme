package com.pku.ipku.api.mock.person;

import com.pku.ipku.model.person.dto.LibBorrowDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Allen on 2015/1/19.
 */
public class MockLibBorrowInfo {
    private static List<LibBorrowDTO> borrowDTOs = new ArrayList<LibBorrowDTO>() {
        {
            add(new LibBorrowDTO("具体数学——计算机科学基础", "TP301.6/150", "中心馆藏", "葛立恒", new Date(), new Date(2015, 1, 20)));
            add(new LibBorrowDTO("具体数学——计算机科学基础", "TP301.6/150", "中心馆藏", "葛立恒", new Date(), new Date(2015, 1, 20)));
            add(new LibBorrowDTO("具体数学——计算机科学基础", "TP301.6/150", "中心馆藏", "葛立恒", new Date(), new Date(2015, 2, 20)));
            add(new LibBorrowDTO("具体数学——计算机科学基础", "TP301.6/150", "中心馆藏", "葛立恒", new Date(), new Date(2015, 2, 20)));
            add(new LibBorrowDTO("具体数学——计算机科学基础", "TP301.6/150", "中心馆藏", "葛立恒", new Date(), new Date(2015, 2, 20)));
        }
    };

    public static List<LibBorrowDTO> get() {
        return borrowDTOs;
    }
}
