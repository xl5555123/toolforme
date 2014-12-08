package com.pku.ipku.util;

import com.pku.ipku.cache.Cache;
import com.pku.ipku.cache.CacheDao;
import com.pku.ipku.cache.DaoMaster;
import com.pku.ipku.cache.DaoSession;
import com.pku.ipku.ui.AppContext;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by R on 2014/10/13.
 */
public class DaoHelper {

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    /**
     * 取得DaoMaster
     *
     * @return
     */
    public static DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            AppContext appContext = AppContextHolder.getAppContext();
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(appContext, "cache", null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @return
     */
    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    public static CacheDao getCacheDao() {
        if (daoSession == null) {
            daoSession = getDaoSession();
        }
        return daoSession.getCacheDao();
    }

    public static <T> T readData(String key, final Class<T> type) {
        QueryBuilder<Cache> qb = getCacheDao().queryBuilder();
        qb.where(CacheDao.Properties.Key.eq(key));
        String data = "";
        if (qb.list().size() > 0) {
            data = qb.list().get(0).getData();
        } else {
            return null;
        }
        return DataHandleUtil.StringToObject(type, data);
    }

    public static void saveData(String key, Object data) {
        if (data == null) {
            return;
        }
        String json = DataHandleUtil.objectToJson(data);
        Cache cache = new Cache();
        cache.setKey(key);
        cache.setData(json);
        getCacheDao().insertOrReplace(cache);
    }

    public static void removeData(String key) {
        getCacheDao().deleteByKey(key);
    }
}
