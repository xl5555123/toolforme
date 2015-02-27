package com.pku.ipku.api.net;

import com.google.common.collect.Lists;
import com.pku.ipku.api.PkuInfoService;
import com.pku.ipku.api.mock.pkuInfo.MockPkuClubActivityList;
import com.pku.ipku.api.mock.pkuInfo.MockPkuJob;
import com.pku.ipku.api.util.NetHelper;
import com.pku.ipku.model.pkuInfo.PkuInfoType;
import com.pku.ipku.model.pkuInfo.dto.PkuClubDTO;
import com.pku.ipku.model.pkuInfo.dto.PkuJobDTO;
import com.pku.ipku.model.pkuInfo.dto.PkuPublicInfo;

import org.springframework.web.client.RestClientException;

import java.util.List;

/**
 * Created by XingLiang on 2015/1/6.
 */
public class PkuInfoServiceNetImpl implements PkuInfoService {


    @Override
    public List<PkuPublicInfo> getPkuPublicNotice(PkuInfoType pkuInfoType, Integer page) throws RestClientException {
        if (pkuInfoType.isPkuCareer()) {
            return getPkuCareer(page);
        } else if (pkuInfoType.isPkuLectures()) {
            return getPkuLectures();
        } else if (pkuInfoType.isPkuNews()) {
            List<PkuPublicInfo> pkuTrend = getPkuTrends();
            List<PkuPublicInfo> pkuNews = getPkuNews();
            if (pkuTrend == null) {
                pkuTrend = Lists.newArrayList();
            }
            if (pkuNews != null) {
                pkuTrend.addAll(pkuNews);
            }
            return pkuTrend;
        } else if (pkuInfoType.isPkuNotices()) {
            List<PkuPublicInfo> pkuApartmentNotices = getPkuApartmentNotice();
            List<PkuPublicInfo> pkuNotices = getPkuNotices();
            if (pkuApartmentNotices == null) {
                pkuApartmentNotices = Lists.newArrayList();
            }
            if (pkuNotices != null) {
                pkuApartmentNotices.addAll(pkuNotices);
            }
            return pkuApartmentNotices;
        }
        return null;
    }

    @Override
    public List<PkuPublicInfo> getPkuNotices() throws RestClientException {
        return Lists.newArrayList(NetHelper.getForObject(RECENT_SCHOOL_NEWS_URI, PkuPublicInfo[].class));
    }

    @Override
    public List<PkuPublicInfo> getPkuTrends() throws RestClientException {
        return Lists.newArrayList(NetHelper.getForObject(RECENT_SCHOOL_NEWS_URI, PkuPublicInfo[].class));
    }

    @Override
    public List<PkuPublicInfo> getPkuNews() throws RestClientException {
        return Lists.newArrayList(NetHelper.getForObject(TOP_NEWS_URI, PkuPublicInfo[].class));
    }

    @Override
    public List<PkuPublicInfo> getPkuApartmentNotice() throws RestClientException {
        return Lists.newArrayList(NetHelper.getForObject(RECENT_DEPT_NOTICES_URI, PkuPublicInfo[].class));
    }

    @Override
    public List<PkuPublicInfo> getPkuLectures() throws RestClientException {
        return Lists.newArrayList(NetHelper.getForObject(TOP_LECTURES_URI, PkuPublicInfo[].class));
    }

    @Override
    public List<PkuPublicInfo> getPkuCareer(int page) throws RestClientException {
        String uri = String.format(PAGED_CAREER_RECRUITS_URI, page);
        return Lists.newArrayList(NetHelper.getForObject(uri, PkuPublicInfo[].class));
    }

    public List<PkuClubDTO> getPkuClubActivities() {
        return MockPkuClubActivityList.get();
    }

    @Override
    public List<PkuJobDTO> getPkuJobs() {
        return MockPkuJob.get();
    }
}
