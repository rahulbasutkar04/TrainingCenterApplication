package com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model.valueobject.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CenterRequest {
    @NotEmpty(message = "Center name is required")
    @Size(max = 40, message = "Center name must be less than 40 characters")
    private String centerName;

    @NotEmpty(message = "Center code is required")
    @Size(min = 12, max = 12, message = "Center code must be exactly 12 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]{12}$", message = "Center code must be alphanumeric and exactly 12 characters")
    private String centerCode;

    @Valid
    @NotNull(message = "Address is required")
    private Address address;

    @NotNull(message = "Student capacity is required")
    @Min(value = 1, message = "Student capacity must be greater than 0")
    private Integer capacity;

    @NotEmpty(message = "Courses offered cannot be empty")
    private List<String> coursesOffered;

    @Email(message = "Invalid email format")
    @NotEmpty
    private String email;

    @NotNull(message = "Contact phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String phoneNumber;



}



