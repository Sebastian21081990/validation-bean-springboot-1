package com.example.beanvalidation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class CommPreferenceValidator implements ConstraintValidator<CommPreference, String> {

    private final List<String> commPreferences = Arrays.asList("email", "mobilePhone");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return commPreferences.contains(s);
    }

}
