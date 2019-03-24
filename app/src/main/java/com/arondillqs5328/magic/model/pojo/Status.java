package com.arondillqs5328.magic.model.pojo;

public class Status {

    private int error_code;
    private String error_message;

    public Status() {

    }

    public Status(int error_code, String error_message) {
        this.error_code = error_code;
        this.error_message = error_message;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
