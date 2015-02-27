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

    private class LoadDataFromCacheTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                return loadDataConfigure.getData(true);
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result && !loaded && loadDataConfigure != null) {
                loadDataConfigure.showData();

                loadDataConfigure.stopWaiting();
            }
        }
    }

    private class LoadDataFromNetTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                if (SystemHelper.haveNetwork()) {
                    return loadDataConfigure.getData(false);
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result && loadDataConfigure != null) {
                loaded = true;
                loadDataConfigure.showData();
                loadDataConfigure.stopWaiting();
            }
        }
    }
}
