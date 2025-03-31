package com.yinnohs.security.jwt.user.domain.vo;

import com.yinnohs.security.jwt.user.domain.exception.InvalidEmailException;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Email {
    private final String EMAIL_REGEX =
            "^(?=.{1,256}$)(?=.{1,64}@.{1,255}$)([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,})$";

    private final String value;

    public Email(String value) {
        if (!isValidEmail(value)) {
            throw new InvalidEmailException(String.format("Invalid Email %s", value));
        }
        this.value = value;
    }

    private boolean isValidEmail(String value){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
