package com.pku.portal.api.cache;

import com.google.common.collect.Lists;
import com.pku.portal.api.PkuInfoService;
import com.pku.portal.model.PubInfo;
import com.pku.portal.model.pkuInfo.PkuInfoType;
import com.pku.portal.model.pkuInfo.dto.PkuClubDTO;
import com.pku.portal.model.pkuInfo.dto.PkuJobDTO;
import com.pku.portal.util.DaoHelper;

import org.springframework.web.client.RestClientException;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by XingLiang on 2015/1/6.
 */
public class PkuInfoServiceCacheImpl implements PkuInfoService {
    @Override
    public List<PubInfo> getPkuPublicNotice(PkuInfoType pkuInfoType, Integer page) throws RestClientException {
        return null;
    }

    @Override
    public List<PubInfo> getPkuLecture(Calendar calendar) throws RestClientException {
        String pkuLectureUrl = String.format(LECTURE_URI, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

        String key = String.format("lecture%s", pkuLectureUrl);
        PubInfo[] pkuPublicInfos = DaoHelper.readData(key, PubInfo[].class);
        if (pkuPublicInfos == null) {
            return Lists.newArrayList();
        }
        else {
            return Lists.newArrayList(pkuPublicInfos);
        }
     }

    @Override
    public void collect(PubInfo pkuPublicInfo, PkuInfoType pkuInfoType) {
        String key = pkuInfoType.getType();
        PubInfo[] data = DaoHelper.readData(key, PubInfo[].class);

        if (data == null) {
            data = new PubInfo[1];
            data[0] = pkuPublicInfo;
        }
        else {
            List<PubInfo> result = Lists.newArrayList(data);
            result.add(pkuPublicInfo);
            data = result.toArray(new PubInfo[0]);
        }
        DaoHelper.saveData(key, data);
    }

    @Override
    public void unCollect(PubInfo pkuPublicInfo, PkuInfoType pkuInfoType) {
        String key = pkuInfoType.getType();
        PubInfo[] data = DaoHelper.readData(key, PubInfo[].class);
        if (data == null) {
            return;
        }
        else {
            List<PubInfo> result = Lists.newArrayList(data);
            Iterator<PubInfo> iterator = result.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getTitle().equals(pkuPublicInfo.getTitle())) {
                    iterator.remove();
                }
            }
            DaoHelper.saveData(key, result);
        }
    }

    @Override
    public List<PubInfo> getCollected(PkuInfoType pkuInfoType) {
        String key = pkuInfoType.getType();
        PubInfo[] data = DaoHelper.readData(key, PubInfo[].class);

        if (data == null) {
            return Lists.newArrayList();
        }
        else {
            List<PubInfo> result = Lists.newArrayList(data);
            for (PubInfo pubInfo : result) {
                pubInfo.setCollected(true);
            }
            return result;
        }
    }

    @Override
    public List<PubInfo> getPkuNotices() throws RestClientException {
        return null;
    }

    @Override
    public List<PubInfo> getPkuTrends() throws RestClientException {
        return null;
    }

    @Override
    public List<PubInfo> getPkuNews() throws RestClientException {
        return null;
    }

    @Override
    public List<PubInfo> getPkuApartmentNotice() throws RestClientException {
        return null;
    }

    @Override
    public List<PubInfo> getPkuLectures() throws RestClientException {
        return null;
    }

    @Override
    public List<PubInfo> getPkuCareer(int page) throws RestClientException {
        return null;
    }

    @Override
    public List<PubInfo> getPkuCareerInterns(int page) throws RestClientException {
        return null;
    }

    @Override
    public List<PubInfo> getPkuCareerPropa(int page) throws RestClientException {
        return null;
    }

    @Override
    public List<PkuClubDTO> getPkuClubActivities() {
        return Lists.newArrayList();
    }

    @Override
    public List<PkuJobDTO> getPkuJobs() {
        return Lists.newArrayList();
    }
}
