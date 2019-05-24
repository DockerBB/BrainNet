package com.brainsci.security.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestForm extends BaseAuthForm {
    private String verification;

    public LoginRequestForm(String verification) {
        this.verification = verification;
    }

    public LoginRequestForm() {
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
}
