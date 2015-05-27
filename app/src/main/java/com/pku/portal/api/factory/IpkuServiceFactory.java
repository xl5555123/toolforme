package com.pku.portal.api.factory;

import com.pku.portal.api.PersonService;
import com.pku.portal.api.PkuInfoService;
import com.pku.portal.api.cache.PersonServiceCacheImpl;
import com.pku.portal.api.cache.PkuInfoServiceCacheImpl;
import com.pku.portal.api.net.PersonServiceNetImpl;
import com.pku.portal.api.net.PkuInfoServiceNetImpl;

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

    public static PersonService getPersonService(boolean cache) {
        if (cache) {
            return new PersonServiceCacheImpl();
        }
        return new PersonServiceNetImpl();
    }
}
