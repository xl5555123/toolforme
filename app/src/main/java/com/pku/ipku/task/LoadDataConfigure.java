package com.pku.ipku.task;

/**
 * Created by XingLiang on 2015/1/6.
 */
public interface LoadDataConfigure {
    void showData();

    boolean getData(boolean cache);

    void showWaiting();

    void stopWaiting();
}
