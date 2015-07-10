package com.xingliang.toolforme.api.net;

import com.google.common.collect.Lists;
import com.xingliang.toolforme.api.PkuInfoService;
import com.xingliang.toolforme.api.util.NetHelper;
import com.xingliang.toolforme.model.PubInfo;
import com.xingliang.toolforme.model.pkuInfo.PkuInfoType;
import com.xingliang.toolforme.model.pkuInfo.dto.PkuClubDTO;
import com.xingliang.toolforme.model.pkuInfo.dto.PkuJobDTO;
import com.xingliang.toolforme.model.pkuInfo.dto.PkuPublicInfo;
import com.xingliang.toolforme.util.DaoHelper;
import com.xingliang.toolforme.util.DataHandleUtil;

import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by XingLiang on 2015/1/6.
 */
public class PkuInfoServiceNetImpl implements PkuInfoService {


    @Override
    public List<PubInfo> getPkuPublicNotice(PkuInfoType pkuInfoType, Integer page) throws RestClientException {

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
    public List<PubInfo> getPkuLecture(Calendar calendar) throws RestClientException {
        String pkuLectureUrl = String.format(LECTURE_URI, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        PkuPublicInfo[] result = NetHelper.getForObject(pkuLectureUrl, PkuPublicInfo[].class);
        List<PubInfo> resultList = null;
        if (result == null || result.length == 0) {
            resultList = Lists.newArrayList();
        }
        else {
            resultList = DataHandleUtil.PubArrayToList(result);
        }
        String key = String.format("lecture%s", pkuLectureUrl);
        DaoHelper.saveData(key, result);

        return resultList;
    }

    @Override
    public void collect(PubInfo pkuPublicInfo, PkuInfoType pkuInfoType) {

    }

    @Override
    public void unCollect(PubInfo pkuPublicInfo, PkuInfoType pkuInfoType) {

    }

    @Override
    public List<PubInfo> getCollected(PkuInfoType pkuInfoType) {
        return null;
    }

    @Override
    public List<PubInfo> getPkuNotices() throws RestClientException {
        return DataHandleUtil.PubArrayToList(NetHelper.getForObject(RECENT_SCHOOL_NEWS_URI, PkuPublicInfo[].class));
    }

    @Override
    public List<PubInfo> getPkuTrends() throws RestClientException {
        return DataHandleUtil.PubArrayToList(NetHelper.getForObject(RECENT_SCHOOL_NOTICES_URI, PkuPublicInfo[].class));
    }

    @Override
    public List<PubInfo> getPkuNews() throws RestClientException {
        return DataHandleUtil.PubArrayToList(NetHelper.getForObject(TOP_NEWS_URI, PkuPublicInfo[].class));
    }

    @Override
    public List<PubInfo> getPkuApartmentNotice() throws RestClientException {
        return DataHandleUtil.PubArrayToList(NetHelper.getForObject(RECENT_DEPT_NOTICES_URI, PkuPublicInfo[].class));
    }

    @Override
    public List<PubInfo> getPkuLectures() throws RestClientException {
        return DataHandleUtil.PubArrayToList(NetHelper.getForObject(TOP_LECTURES_URI, PkuPublicInfo[].class));
    }

    @Override
    public List<PubInfo> getPkuCareer(int page) throws RestClientException {
        String uri = String.format(PAGED_CAREER_RECRUITS_URI, page);
        return DataHandleUtil.PubArrayToList(NetHelper.getForObject(uri, PkuPublicInfo[].class));
    }

    @Override
    public List<PubInfo> getPkuCareerInterns(int page) throws RestClientException {
        String uri = String.format(PAGED_CAREER_INTERNS_URI, page);
        return DataHandleUtil.PubArrayToList(NetHelper.getForObject(uri, PkuPublicInfo[].class));
    }

    @Override
    public List<PubInfo> getPkuCareerPropa(int page) throws RestClientException {
        String uri = String.format(PAGED_CAREER_PROPA_URI, page);
        return DataHandleUtil.PubArrayToList(NetHelper.getForObject(uri, PkuPublicInfo[].class));
    }

    public List<PkuClubDTO> getPkuClubActivities() {
        return new ArrayList<PkuClubDTO>();
    }

    @Override
    public List<PkuJobDTO> getPkuJobs() {
        return new ArrayList<PkuJobDTO>();
    }
}
