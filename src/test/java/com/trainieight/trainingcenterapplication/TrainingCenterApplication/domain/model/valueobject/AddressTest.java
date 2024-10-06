package com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model.valueobject;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldBeAbleToPassValidationWithValidAddress() {
        Address address = new Address("123 Main St", "City", "State", "123456");

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertTrue(violations.isEmpty(), "There should be no validation errors for a valid address.");
    }

    @Test
    void shouldBeAbleToFailValidationWhenDetailedAddressIsEmpty() {
        Address address = new Address("", "City", "State", "123456");

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertEquals(1, violations.size());
        assertEquals("Detailed Address is Required", violations.iterator().next().getMessage());
    }

    @Test
    void shouldBeAbleToFailValidationWhenCityIsEmpty() {
        Address address = new Address("123 Main St", "", "State", "123456");

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertEquals(1, violations.size());
        assertEquals("City is required", violations.iterator().next().getMessage());
    }

    @Test
    void shouldBeAbleToFailValidationWhenStateIsEmpty() {
        Address address = new Address("123 Main St", "City", "", "123456");

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertEquals(1, violations.size());
        assertEquals("State is required", violations.iterator().next().getMessage());
    }

    @Test
    void shouldBeAbleToFailValidationWhenPinCodeIsNull() {
        Address address = new Address("123 Main St", "City", "State", null);

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertEquals(1, violations.size());
        assertEquals("PinCode is required", violations.iterator().next().getMessage());
    }

    @Test
    void shouldBeAbleToFailValidationWhenPinCodeIsInvalid() {
        Address address = new Address("123 Main St", "City", "State", "12345"); // Invalid pin code (5 digits)

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertEquals(1, violations.size());
        assertEquals("Pincode must be exactly 6 digits", violations.iterator().next().getMessage());
    }

    @Test
    void shouldBeAbleToFailValidationWhenPinCodeHasLetters() {
        Address address = new Address("123 Main St", "City", "State", "123A56"); // Invalid pin code (letters included)

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertEquals(1, violations.size());
        assertEquals("Pincode must be exactly 6 digits", violations.iterator().next().getMessage());
    }

}