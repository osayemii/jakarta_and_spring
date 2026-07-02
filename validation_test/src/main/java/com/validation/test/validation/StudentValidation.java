package com.validation.test.validation;

import jakarta.validation.*;

public class StudentValidation implements ConstraintValidator<Students, String> {
	@Override
	public void initialize(Students student) {
	}
	
	@Override
	public boolean isValid(String title, ConstraintValidatorContext context) {
		if (title == null) {
			return false;
		}
		String pattern = "^[\\w]{1,30}@gmail.com$";
		boolean result = title.matches(pattern);
		return result;
	}
}