package com.brainsci.security.form;

public class VerficationDataForm {
    private String vercodeimg;

    public VerficationDataForm(String vercodeimg) {
        this.vercodeimg = vercodeimg;
    }

    public VerficationDataForm() {
    }

    public void setVercodeimg(String vercodeimg) {

        this.vercodeimg = vercodeimg;
    }

    public String getVercodeimg() {

        return vercodeimg;
    }
}