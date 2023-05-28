package com.example.url.shortner.microservices.authenticationservice.utils.validators;

import com.example.url.shortner.microservices.authenticationservice.utils.RegisterUserReg;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FirstNameValidator implements RegisterUserReg {
    @Override
    public Boolean isValid(String value) {

        Pattern pattern = Pattern.compile("^[a-z]{1,10}$");

        Matcher matcher = pattern.matcher(value);
        boolean valueIsValid = matcher.matches();


        if (valueIsValid) {
            return true;
        }

        return false;
    }
}
