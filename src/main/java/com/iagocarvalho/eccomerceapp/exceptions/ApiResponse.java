package com.iagocarvalho.eccomerceapp.exceptions;

public class ApiResponse {
    public String messagge;
    private boolean status;

    public ApiResponse() {
    }

    public ApiResponse(String messagge, boolean status) {
        this.messagge = messagge;
        this.status = status;

    }

    public String getMessagge() {
        return messagge;
    }

    public void setMessagge(String messagge) {
        this.messagge = messagge;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
