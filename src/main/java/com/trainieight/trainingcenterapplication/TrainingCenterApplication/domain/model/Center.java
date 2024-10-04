package com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model;

import com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model.valueobject.Address;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Document(value = "center")
public class Center {
    @Id
    private String id;
    private String centerName;
    private String centerCode;
    private Address address;
    private int capacity;
    private List<String> coursesOffered;
    private Date date;
    private String email;
    private String phoneNumber;

}
