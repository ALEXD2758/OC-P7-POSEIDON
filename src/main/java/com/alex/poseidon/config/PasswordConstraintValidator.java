package com.alex.poseidon.config;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    /**
     * Boolean to check if password is valid according to:
     * Length: 8-125
     * Uppercase Letter: 1
     * Lowercase Letter: 1
     * Number of digit: 1
     * Number of special characters: 1
     * No whitespace
     *
     *
     * @param password string of the password entered by the user
     * @param context the ConstraintValidatorContext provides contextual data and operation when applying a given
     *                constraint validator (at least one)
     * @return true if password is valid, false if password is not meeting all validation constraints
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // at least 8 characters
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 125),
        // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
        // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
        // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),
        // at least one symbol (special character)
                new CharacterRule(EnglishCharacterData.Special, 1),
        // no whitespace
                new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);

        String messageTemplate = messages.stream().collect(Collectors.joining(","));
        if (context != null) {
            context.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return false;
    }
}