package com.pku.ipku.api;

import com.pku.ipku.model.pkuInfo.PkuInfoType;
import com.pku.ipku.model.pkuInfo.dto.PkuClubDTO;
import com.pku.ipku.model.pkuInfo.dto.PkuJobDTO;
import com.pku.ipku.model.pkuInfo.dto.PkuPublicInfo;
import com.pku.ipku.ui.AppContext;

import org.springframework.web.client.RestClientException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by XingLiang on 2015/1/6.
 */
public interface PkuInfoService {
    public static final String RECENT_SCHOOL_NOTICES_URI = "/svcpub/svc/pub/notice/recentschoolnotices" + AppContext.APP_KEY;
    public static final String RECENT_SCHOOL_NEWS_URI = "/svcpub/svc/pub/notice/recentschoolnews" + AppContext.APP_KEY;
    public static final String RECENT_DEPT_NOTICES_URI = "/svcpub/svc/pub/notice/recentdeptnotices" + AppContext.APP_KEY;
    public static final String TOP_NEWS_URI = "/svcpub/svc/pub/news/top8" + AppContext.APP_KEY;
    public static final String TOP_LECTURES_URI = "/svcpub/svc/pub/lecture/top10" + AppContext.APP_KEY;
    public static final String PAGED_CAREER_RECRUITS_URI = "/svcpub/svc/pub/career/recruit/%d" + AppContext.APP_KEY;
    public static final String LECTURE_URI = "/svcpub/svc/pub/lecture/date/%d/%d/%d" + AppContext.APP_KEY;
    public static final String PAGED_CAREER_INTERNS_URI = "/svcpub/svc/pub/career/intern/%d" + AppContext.APP_KEY;
    public static final String PAGED_CAREER_PROPA_URI = "/svcpub/svc/pub/career/propa/%d" + AppContext.APP_KEY;

    public List<PkuPublicInfo> getPkuPublicNotice(PkuInfoType pkuInfoType, Integer page) throws RestClientException;

    public List<PkuPublicInfo> getPkuLecture(Calendar calendar) throws RestClientException;

    /**
     * 获取学校公告
     * 服务ID RECENT_SCHOOL_NOTICES
     *
     * @return
     */
    public List<PkuPublicInfo> getPkuNotices() throws RestClientException;

    /**
     * 获取学校动态
     * 服务ID RECENT_SCHOOL_NEWS
     *
     * @return
     */
    public List<PkuPublicInfo> getPkuTrends() throws RestClientException;

    /**
     * 获取学校新闻
     *
     * @return
     */
    public List<PkuPublicInfo> getPkuNews() throws RestClientException;

    /**
     * 获取单位公告
     * 服务ID RECENT_DEPT_NOTICES
     *
     * @return
     */
    public List<PkuPublicInfo> getPkuApartmentNotice() throws RestClientException;

    /**
     * 获取校内讲座信息
     *
     * @return
     */
    public List<PkuPublicInfo> getPkuLectures() throws RestClientException;

    /**
     * 分页获取招聘信息
     *
     * @param page
     * @return
     */
    public List<PkuPublicInfo> getPkuCareer(int page) throws RestClientException;

    /**
     * 分页获取实习信息
     *
     * @param page
     * @return
     */
    public List<PkuPublicInfo> getPkuCareerInterns(int page) throws RestClientException;

    /**
     * 分页获取宣讲会信息
     *
     * @param page
     * @return
     */
    public List<PkuPublicInfo> getPkuCareerPropa(int page) throws RestClientException;

    public List<PkuClubDTO> getPkuClubActivities();

    public List<PkuJobDTO> getPkuJobs();
}
