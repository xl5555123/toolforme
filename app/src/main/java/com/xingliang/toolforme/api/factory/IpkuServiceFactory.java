package com.xingliang.toolforme.api.factory;

import com.xingliang.toolforme.api.PersonService;
import com.xingliang.toolforme.api.PkuInfoService;
import com.xingliang.toolforme.api.cache.PersonServiceCacheImpl;
import com.xingliang.toolforme.api.cache.PkuInfoServiceCacheImpl;
import com.xingliang.toolforme.api.net.PersonServiceNetImpl;
import com.xingliang.toolforme.api.net.PkuInfoServiceNetImpl;

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
