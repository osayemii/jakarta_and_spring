package com.validation.test.validation;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StudentValidation.class)
public @interface Students {
	String message() default "Invalid Format";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}