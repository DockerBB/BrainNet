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
}
