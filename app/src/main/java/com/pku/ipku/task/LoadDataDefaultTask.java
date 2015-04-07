package com.pku.ipku.task;

import android.os.AsyncTask;

import com.pku.ipku.util.SystemHelper;

/**
 * Created by XingLiang on 2015/1/6.
 */
public class LoadDataDefaultTask {

    private LoadDataConfigure loadDataConfigure;
    private boolean loaded;

    public LoadDataDefaultTask(LoadDataConfigure loadDataView) {
        this.loadDataConfigure = loadDataView;
        loaded = false;
    }

    public void execute() {
        loadDataConfigure.showWaiting();
        new LoadDataFromCacheTask().execute();
        new LoadDataFromNetTask().execute();
    }

    private class LoadDataFromCacheTask extends AsyncTask<Void, Void, Result> {
        @Override
        protected Result doInBackground(Void... params) {
            try {
                return loadDataConfigure.getData(true);
            } catch (Exception e) {
                return new Result(Result.CACHE_ERROR);
            }
        }

        @Override
        protected void onPostExecute(Result result) {
            if (result.hasNoError() && !loaded && loadDataConfigure != null) {
                loadDataConfigure.showData();

                loadDataConfigure.stopWaiting();
            }
        }
    }

    private class LoadDataFromNetTask extends AsyncTask<Void, Void, Result> {
        @Override
        protected Result doInBackground(Void... params) {
            try {
                if (SystemHelper.haveNetwork()) {
                    return loadDataConfigure.getData(false);
                } else {
                    return new Result(Result.NET_ERROR);
                }
            } catch (Exception e) {
                return new Result(Result.ACCOUNT_ERROR);
            }

        }

        @Override
        protected void onPostExecute(Result result) {
            if (result.hasNoError() && loadDataConfigure != null) {
                loaded = true;
                loadDataConfigure.showData();
                loadDataConfigure.stopWaiting();
            } else {
                loadDataConfigure.processError(result);
            }
        }
    }
}
