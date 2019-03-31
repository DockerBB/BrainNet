package com.brainsci.security.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestForm extends BaseAuthForm {
    private String name;
    private String avatar;
    private String eMail;
    private String institution;
    private String phone;
    private String qq;
}
