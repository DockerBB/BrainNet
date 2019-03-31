package com.brainsci.security.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestForm extends BaseAuthForm {
    private String verification;
}
