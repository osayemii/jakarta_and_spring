package com.product.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductIdValidator implements ConstraintValidator<ProductId, String> {

    @Override
    public void initialize(ProductId productId) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.matches("^P\\d{5}$");
    }
}
