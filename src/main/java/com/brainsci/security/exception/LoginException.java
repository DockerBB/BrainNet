package com.brainsci.security.exception;

public class LoginException extends Exception {
    private int status;
    private String result;

    @Override
    public String getMessage() {
        return this.result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LoginException(int status, String result) {
        this.status = status;
        this.result = result;
    }
}
