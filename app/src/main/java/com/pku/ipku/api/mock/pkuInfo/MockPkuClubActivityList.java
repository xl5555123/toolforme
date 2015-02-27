package com.pku.ipku.api.mock.pkuInfo;

import com.pku.ipku.model.pkuInfo.dto.PkuClubDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Allen on 2015/1/7.
 */
public class MockPkuClubActivityList {

    public static List<PkuClubDTO> pkuClubDTOs = new ArrayList<PkuClubDTO>() {
        {
            add(new PkuClubDTO("爱心社", "关爱留守儿童", "海淀区农民子弟学校", new Date(), new Date(), "https://portal.pku.edu.cn"));
            add(new PkuClubDTO("爱心社", "关爱留守儿童", "海淀区农民子弟学校", new Date(), new Date(), "https://portal.pku.edu.cn"));
            add(new PkuClubDTO("爱心社", "关爱留守儿童", "海淀区农民子弟学校", new Date(), new Date(), "https://portal.pku.edu.cn"));
            add(new PkuClubDTO("爱心社", "关爱留守儿童", "海淀区农民子弟学校", new Date(), new Date(), "https://portal.pku.edu.cn"));
            add(new PkuClubDTO("爱心社", "关爱留守儿童", "海淀区农民子弟学校", new Date(), new Date(), "https://portal.pku.edu.cn"));
        }

    };

    public static List<PkuClubDTO> get() {
        return pkuClubDTOs;
    }
}
