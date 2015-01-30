package com.pku.ipku.api.cache;

import com.pku.ipku.api.PkuInfoService;
import com.pku.ipku.api.mock.pkuInfo.MockPkuClubActivityList;
import com.pku.ipku.api.mock.pkuInfo.MockPkuJob;
import com.pku.ipku.api.mock.pkuInfo.MockPkuLectureList;
import com.pku.ipku.model.pkuInfo.PkuInfoType;
import com.pku.ipku.model.pkuInfo.dto.PkuClubDTO;
import com.pku.ipku.model.pkuInfo.dto.PkuJobDTO;
import com.pku.ipku.model.pkuInfo.dto.PkuLectureDTO;
import com.pku.ipku.model.pkuInfo.dto.PkuPublicInfo;

import org.springframework.web.client.RestClientException;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/6.
 */
public class PkuInfoServiceCacheImpl implements PkuInfoService {
    @Override
    public List<PkuPublicInfo> getPkuPublicNotice(PkuInfoType pkuInfoType, Integer page) throws RestClientException {
        return null;
    }

    @Override
    public List<PkuPublicInfo> getPkuNotices() throws RestClientException {
        return null;
    }

    @Override
    public List<PkuPublicInfo> getPkuTrends() throws RestClientException {
        return null;
    }

    @Override
    public List<PkuPublicInfo> getPkuNews() throws RestClientException {
        return null;
    }

    @Override
    public List<PkuPublicInfo> getPkuApartmentNotice() throws RestClientException {
        return null;
    }

    @Override
    public List<PkuPublicInfo> getPkuLectures() throws RestClientException {
        return null;
    }

    @Override
    public List<PkuPublicInfo> getPkuCareer(int page) throws RestClientException {
        return null;
    }

    @Override
    public List<PkuClubDTO> getPkuClubActivities(){
        return MockPkuClubActivityList.get();
    }

    @Override
    public List<PkuJobDTO> getPkuJobs() {
        return MockPkuJob.get();
    }
}
