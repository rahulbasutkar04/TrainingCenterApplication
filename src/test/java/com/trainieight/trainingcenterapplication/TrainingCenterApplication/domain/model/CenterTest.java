package com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model;

import com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model.valueobject.Address;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CenterTest {


    @Test
    void shouldBeAbleToCreateInstanceOfCenterClass() {
        Center center = Center.builder().build();
        assertInstanceOf(Center.class, center);
    }

    @Test
    void shouldBeAbleToCreateCenterWithTheProperties() {
        // arrange
        String centerName = "RB Tower4";
        String centerCode = "123456";
        Address address = new Address("Floor 4 tower b", "Pune", "Maharashtra", "413006");
        List<String> courseOffered = List.of("Python", "java");
        String phoneNumber = "9284522484";
        String email = "rahulbasutkar33@gmail.com";
        int capacity = 90;
        String id = "123456";
        Date date = new Date();
        Center center = Center.builder()
                .address(address)
                .centerCode(centerCode)
                .centerName(centerName)
                .coursesOffered(courseOffered)
                .id(id)
                .email(email)
                .date(date)
                .phoneNumber(phoneNumber)
                .capacity(capacity)
                .build();


        // act & assert
        assertNotNull(center);
    }
}