package com.arondillqs5328.magic.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("timestamp")
    @Expose
    private String timeStamp;

    @SerializedName("error_code")
    @Expose
    private int errorCode;

    @SerializedName("error_message")
    @Expose
    private String errorMessage;

    @SerializedName("elapsed")
    @Expose
    private int elapsed;

    @SerializedName("credit_count")
    @Expose
    private int creditCount;

    public Status() {
    }

    public Status(String timeStamp, int errorCode, String errorMessage, int elapsed, int creditCount) {
        this.timeStamp = timeStamp;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.elapsed = elapsed;
        this.creditCount = creditCount;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getElapsed() {
        return elapsed;
    }

    public void setElapsed(int elapsed) {
        this.elapsed = elapsed;
    }

    public int getCreditCount() {
        return creditCount;
    }

    public void setCreditCount(int creditCount) {
        this.creditCount = creditCount;
    }
}
