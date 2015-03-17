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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by XingLiang on 2015/1/6.
 */
public class PkuInfoServiceNetImpl implements PkuInfoService {


    @Override
    public List<PkuPublicInfo> getPkuPublicNotice(PkuInfoType pkuInfoType, Integer page) throws RestClientException {

        if (pkuInfoType.isPAGED_CAREER_RECRUITS()) {
            return getPkuCareer(page);
        } else if (pkuInfoType.isTOP_LECTURES()) {
            return getPkuLectures();
        } else if (pkuInfoType.isPAGED_CAREER_INTERNS()) {
            return getPkuCareerInterns(page);
        } else if (pkuInfoType.isPAGED_CAREER_PROPA()) {
            return getPkuCareerPropa(page);
        } else if (pkuInfoType.isTOP_NEWS()) {
            return getPkuNews();
        } else if (pkuInfoType.isRECENT_DEPT_NOTICES()) {
            return getPkuApartmentNotice();
        } else if (pkuInfoType.isRECENT_SCHOOL_NEWS()) {
            return getPkuNotices();
        } else if (pkuInfoType.isRECENT_SCHOOL_NOTICES()) {
            return getPkuTrends();
        }
        return null;
    }

    @Override
    public List<PkuPublicInfo> getPkuLecture(Calendar calendar) throws RestClientException {
        String pkuLectureUrl = String.format(LECTURE_URI, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        PkuPublicInfo[] result = NetHelper.getForObject(pkuLectureUrl, PkuPublicInfo[].class);
        if (result == null || result.length == 0) {
            return Lists.newArrayList();
        }
        return Lists.newArrayList(result);
    }

    @Override
    public List<PkuPublicInfo> getPkuNotices() throws RestClientException {
        return Lists.newArrayList(NetHelper.getForObject(RECENT_SCHOOL_NEWS_URI, PkuPublicInfo[].class));
    }

    @Override
    public List<PkuPublicInfo> getPkuTrends() throws RestClientException {
        return Lists.newArrayList(NetHelper.getForObject(RECENT_SCHOOL_NOTICES_URI, PkuPublicInfo[].class));
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

    @Override
    public List<PkuPublicInfo> getPkuCareerInterns(int page) throws RestClientException {
        String uri = String.format(PAGED_CAREER_INTERNS_URI, page);
        return Lists.newArrayList(NetHelper.getForObject(uri, PkuPublicInfo[].class));
    }

    @Override
    public List<PkuPublicInfo> getPkuCareerPropa(int page) throws RestClientException {
        String uri = String.format(PAGED_CAREER_PROPA_URI, page);
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
