package com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model.valueobject;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Address {

    @NotEmpty(message = "Detailed Address is Required")
    private String detailedAddress;

    @NotEmpty(message = "City is required")
    private String city;

    @NotEmpty(message = "State is required")
    private String state;

    @NotNull(message = "PinCode is required")
    @Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be exactly 6 digits")
    private String pinCode;
}
