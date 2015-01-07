package com.pku.ipku.api.factory;

import com.pku.ipku.api.PkuInfoService;
import com.pku.ipku.api.StudyGuideService;
import com.pku.ipku.api.cache.PkuInfoServiceCacheImpl;
import com.pku.ipku.api.cache.StudyGuideServiceCacheImpl;
import com.pku.ipku.api.net.PkuInfoServiceNetImpl;
import com.pku.ipku.api.net.StudyGuideServiceNetImpl;

/**
 * Created by XingLiang on 2015/1/6.
 */
public class IpkuServiceFactory {

    public static PkuInfoService getPkuInfoService(boolean cache) {
        if (cache) {
            return new PkuInfoServiceCacheImpl();
        }
        return new PkuInfoServiceNetImpl();
    }

    public static StudyGuideService getStudyGuideService(boolean cache) {
        if (cache) {
            return new StudyGuideServiceCacheImpl();
        }
        return new StudyGuideServiceNetImpl();
    }
}
