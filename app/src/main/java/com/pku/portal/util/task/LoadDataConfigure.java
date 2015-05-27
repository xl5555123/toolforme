package com.pku.portal.util.task;

/**
 * Created by XingLiang on 2015/1/6.
 */
public interface LoadDataConfigure {
    void showData();

    Result getData(boolean cache) throws Exception;

    void showWaiting();

    void stopWaiting();

    void processError(Result result);
}
