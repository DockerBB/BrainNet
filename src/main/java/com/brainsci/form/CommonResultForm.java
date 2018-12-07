package com.brainsci.form;

public class CommonResultForm {
    Integer status;
    String message;
    Object data;

    public CommonResultForm(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public CommonResultForm(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public static CommonResultForm of200(String message, Object data){
        return new CommonResultForm(0, message, data);
    }
    public static CommonResultForm of204(String message){
        return new CommonResultForm(0, message);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
