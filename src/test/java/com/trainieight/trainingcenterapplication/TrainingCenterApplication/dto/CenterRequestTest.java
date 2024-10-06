package com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto;

import com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model.valueobject.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.*;

import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class CenterRequestTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    void shouldBeAbleToCreateCenterRequestInstance() {
        CenterRequest centerRequest = CenterRequest.builder().build();
        assertInstanceOf(CenterRequest.class, centerRequest);
    }

    @Test
    void shouldBeAbleToPassValidationWithValidCenterRequest() {
        CenterRequest centerRequest = CenterRequest.builder()
                .centerName("Training Center A")
                .centerCode("ABC123456789")
                .address(new Address("123 Main St", "City", "State", "123456"))
                .capacity(100)
                .coursesOffered(List.of("Math", "Science"))
                .email("test@example.com")
                .phoneNumber("1234567890")
                .build();

        Set<ConstraintViolation<CenterRequest>> violations = validator.validate(centerRequest);
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldBeAbleToFailWhenCenterNameIsEmpty() {
        CenterRequest centerRequest = CenterRequest.builder()
                .centerName("")
                .centerCode("ABC123456789")
                .address(new Address("123 Main St", "City", "State", "123456"))
                .capacity(100)
                .coursesOffered(List.of("Math", "Science"))
                .email("test@example.com")
                .phoneNumber("1234567890")
                .build();

        Set<ConstraintViolation<CenterRequest>> violations = validator.validate(centerRequest);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Center name is required")));
    }

    @Test
    void shouldBeAbleToFailWhenCenterCodeIsNotAlphanumeric() {
        CenterRequest centerRequest = CenterRequest.builder()
                .centerName("Training Center A")
                .centerCode("ABC123$56789")
                .address(new Address("123 Main St", "City", "State", "123456"))
                .capacity(100)
                .coursesOffered(List.of("Math", "Science"))
                .email("test@example.com")
                .phoneNumber("1234567890")
                .build();

        Set<ConstraintViolation<CenterRequest>> violations = validator.validate(centerRequest);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Center code must be alphanumeric and exactly 12 characters")));
    }

    @Test
    void shouldBeAbleToFailWhenCenterCodeLengthIsIncorrect() {
        CenterRequest centerRequest = CenterRequest.builder()
                .centerName("Training Center A")
                .centerCode("ABC123")
                .address(new Address("123 Main St", "City", "State", "123456"))
                .capacity(100)
                .coursesOffered(List.of("Math", "Science"))
                .email("test@example.com")
                .phoneNumber("1234567890")
                .build();

        Set<ConstraintViolation<CenterRequest>> violations = validator.validate(centerRequest);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Center code must be exactly 12 characters")));
    }

    @Test
    void shouldBeAbleToFailWhenAddressIsNull() {
        CenterRequest centerRequest = CenterRequest.builder()
                .centerName("Training Center A")
                .centerCode("ABC123456789")
                .address(null)
                .capacity(100)
                .coursesOffered(List.of("Math", "Science"))
                .email("test@example.com")
                .phoneNumber("1234567890")
                .build();

        Set<ConstraintViolation<CenterRequest>> violations = validator.validate(centerRequest);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Address is required")));
    }

    @Test
    void shouldBeAbleToFailWhenCapacityIsLessThanOne() {
        CenterRequest centerRequest = CenterRequest.builder()
                .centerName("Training Center A")
                .centerCode("ABC123456789")
                .address(new Address("123 Main St", "City", "State", "123456"))
                .capacity(0)
                .coursesOffered(List.of("Math", "Science"))
                .email("test@example.com")
                .phoneNumber("1234567890")
                .build();

        Set<ConstraintViolation<CenterRequest>> violations = validator.validate(centerRequest);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Student capacity must be greater than 0")));
    }

    @Test
    void shouldBeAbleToFailWhenCoursesOfferedIsEmpty() {
        CenterRequest centerRequest = CenterRequest.builder()
                .centerName("Training Center A")
                .centerCode("ABC123456789")
                .address(new Address("123 Main St", "City", "State", "123456"))
                .capacity(100)
                .coursesOffered(List.of())
                .email("test@example.com")
                .phoneNumber("1234567890")
                .build();

        Set<ConstraintViolation<CenterRequest>> violations = validator.validate(centerRequest);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Courses offered cannot be empty")));
    }

    @Test
    void shouldBeAbleToFailWhenEmailIsInvalid() {
        CenterRequest centerRequest = CenterRequest.builder()
                .centerName("Training Center A")
                .centerCode("ABC123456789")
                .address(new Address("123 Main St", "City", "State", "123456"))
                .capacity(100)
                .coursesOffered(List.of("Math", "Science"))
                .email("invalid-email")
                .phoneNumber("1234567890")
                .build();

        Set<ConstraintViolation<CenterRequest>> violations = validator.validate(centerRequest);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Invalid email format")));
    }

    @Test
    void shouldBeAbleTOFailWhenPhoneNumberIsNotExactlyTenDigits() {
        CenterRequest centerRequest = CenterRequest.builder()
                .centerName("Training Center A")
                .centerCode("ABC123456789")
                .address(new Address("123 Main St", "City", "State", "123456"))
                .capacity(100)
                .coursesOffered(List.of("Math", "Science"))
                .email("test@example.com")
                .phoneNumber("123456")
                .build();

        Set<ConstraintViolation<CenterRequest>> violations = validator.validate(centerRequest);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Phone number must be exactly 10 digits")));
    }
}
