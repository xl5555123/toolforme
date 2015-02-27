package com.pku.ipku.api.mock.pkuInfo;

import com.pku.ipku.model.pkuInfo.dto.PkuJobDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Allen on 2015/1/9.
 */
public class MockPkuJob {
    private static List<PkuJobDTO> pkuJobs = new ArrayList<PkuJobDTO>() {
        {
            add(new PkuJobDTO("中邮创业基金管理有限公司诚聘英才", "就业", new Date(), "http://jobplatform.pku.edu.cn/portal/viewemploy/id/12649"));
            add(new PkuJobDTO("中邮创业基金管理有限公司诚聘英才", "实习", new Date(), "http://jobplatform.pku.edu.cn/portal/viewemploy/id/12649"));
            add(new PkuJobDTO("中邮创业基金管理有限公司诚聘英才", "就业", new Date(), "http://jobplatform.pku.edu.cn/portal/viewemploy/id/12649"));
            add(new PkuJobDTO("中邮创业基金管理有限公司诚聘英才", "就业", new Date(), "http://jobplatform.pku.edu.cn/portal/viewemploy/id/12649"));
        }
    };

    public static List<PkuJobDTO> get() {
        return pkuJobs;
    }

}
