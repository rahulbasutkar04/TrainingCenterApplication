package com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto;

import com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model.valueobject.Address;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class CenterResponseTest {

    @Test
    void shouldBeAbleToCreateCenterResponseInstance() {
        CenterResponse centerResponse = CenterResponse.builder().build();
        assertInstanceOf(CenterResponse.class, centerResponse);
    }

    @Test
    void shouldBeAbleToCorrectlySetAndGetFields() {
        Address address = new Address("123 Main St", "City", "State", "123456");
        Date date = new Date();

        CenterResponse centerResponse = CenterResponse.builder()
                .id("1")
                .centerName("Training Center A")
                .centerCode("ABC123456789")
                .address(address)
                .capacity(100)
                .coursesOffered(Arrays.asList("Math", "Science"))
                .date(date)
                .email("test@example.com")
                .phoneNumber("1234567890")
                .build();

        assertEquals("1", centerResponse.getId());
        assertEquals("Training Center A", centerResponse.getCenterName());
        assertEquals("ABC123456789", centerResponse.getCenterCode());
        assertEquals(address, centerResponse.getAddress());
        assertEquals(100, centerResponse.getCapacity());
        assertEquals(Arrays.asList("Math", "Science"), centerResponse.getCoursesOffered());
        assertEquals(date, centerResponse.getDate());
        assertEquals("test@example.com", centerResponse.getEmail());
        assertEquals("1234567890", centerResponse.getPhoneNumber());
    }


}
