package com.pku.ipku.api.mock.pkuInfo;

import com.pku.ipku.model.pkuInfo.dto.PkuLectureDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by XingLiang on 2015/1/6.
 */
public class MockPkuLectureList {

    public static List<PkuLectureDTO> pkuLectureDTOs = new ArrayList<PkuLectureDTO>() {
        {
            add(new PkuLectureDTO("http://www.pku.edu.cn/images/index/3_02.gif", new Date(), new Date(), "新常态资源经济发展论坛", "守仁中心", "https://portal.pku.edu.cn" ));
            add(new PkuLectureDTO("http://www.pku.edu.cn/images/index/3_02.gif", new Date(), new Date(), "新常态资源经济发展论坛", "守仁中心", "https://portal.pku.edu.cn" ));
            add(new PkuLectureDTO("http://www.pku.edu.cn/images/index/3_02.gif", new Date(), new Date(), "新常态资源经济发展论坛", "守仁中心", "https://portal.pku.edu.cn" ));
            add(new PkuLectureDTO("http://www.pku.edu.cn/images/index/3_02.gif", new Date(), new Date(), "新常态资源经济发展论坛", "守仁中心", "https://portal.pku.edu.cn" ));
            add(new PkuLectureDTO("http://www.pku.edu.cn/images/index/3_02.gif", new Date(), new Date(), "新常态资源经济发展论坛", "守仁中心", "https://portal.pku.edu.cn" ));
            add(new PkuLectureDTO("http://www.pku.edu.cn/images/index/3_02.gif", new Date(), new Date(), "新常态资源经济发展论坛", "守仁中心", "https://portal.pku.edu.cn" ));
            add(new PkuLectureDTO("http://www.pku.edu.cn/images/index/3_02.gif", new Date(), new Date(), "新常态资源经济发展论坛", "守仁中心", "https://portal.pku.edu.cn" ));

        }
    };

    public static List<PkuLectureDTO> get() {
        return pkuLectureDTOs;
    }
}
