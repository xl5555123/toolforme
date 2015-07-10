package com.xingliang.toolforme.util.task;

/**
 * Created by XingLiang on 2015/3/17.
 */
public class Result {

    public final static int NET_ERROR = 1;

    public final static int CACHE_ERROR = 2;

    public final static int ACCOUNT_ERROR = 3;

    public final static int NO_ERROR = 4;

    public int errorType;

    public Result() {

    }

    public boolean isNetError() {
        return errorType == NET_ERROR;
    }

    public boolean isCacheError() {
        return errorType == CACHE_ERROR;
    }

    public boolean isAccountError() {
        return errorType == ACCOUNT_ERROR;
    }

    public boolean hasNoError() {
        return errorType == NO_ERROR;
    }

    public Result(int errorType) {
        this.errorType = errorType;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }
}
