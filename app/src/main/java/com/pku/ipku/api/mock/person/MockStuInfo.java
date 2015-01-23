package com.pku.ipku.api.mock.person;

import com.pku.ipku.model.person.dto.StuInfoDTO;

/**
 * Created by pktxq on 15-1-22.
 */
public class MockStuInfo {

    private static StuInfoDTO stuInfo = new StuInfoDTO("孙悟空","男","汉","花果山水帘洞","唐僧","群众","双证","身份证","111233222333322234","1111-11-11","2222-2-22","21325352154321523","2341231234",
            "大唐佛学院","降妖除魔","大惊刚经");
    public static StuInfoDTO get(){
        return stuInfo;
    }
}
