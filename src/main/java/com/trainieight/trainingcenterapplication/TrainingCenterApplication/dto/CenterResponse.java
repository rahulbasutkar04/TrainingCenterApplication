package com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model.valueobject.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CenterResponse {

    private String id;
    private String centerName;
    private String centerCode;
    private Address address;
    private int capacity;
    private List<String> coursesOffered;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private String email;
    private String phoneNumber;
}
