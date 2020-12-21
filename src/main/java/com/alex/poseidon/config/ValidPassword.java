package com.alex.poseidon.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Interface for the annotation @ValidPassword
 * Defines the default message
 *
 */
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}