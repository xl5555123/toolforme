package com.pku.ipku.api.net;

import com.pku.ipku.api.PkuInfoService;
import com.pku.ipku.api.mock.pkuInfo.MockPkuClubActivityList;
import com.pku.ipku.api.mock.pkuInfo.MockPkuJob;
import com.pku.ipku.api.mock.pkuInfo.MockPkuLectureList;
import com.pku.ipku.model.pkuInfo.dto.PkuClubDTO;
import com.pku.ipku.model.pkuInfo.dto.PkuJobDTO;
import com.pku.ipku.model.pkuInfo.dto.PkuLectureDTO;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/6.
 */
public class PkuInfoServiceNetImpl implements PkuInfoService {
    @Override
    public List<PkuLectureDTO> getPkuLectures() {
        return MockPkuLectureList.get();
    }
    public List<PkuClubDTO> getPkuClubActivities(){
        return MockPkuClubActivityList.get();
    }

    @Override
    public List<PkuJobDTO> getPkuJobs() {
        return MockPkuJob.get();
    }
}
