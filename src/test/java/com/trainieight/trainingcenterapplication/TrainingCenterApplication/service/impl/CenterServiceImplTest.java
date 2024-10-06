package com.trainieight.trainingcenterapplication.TrainingCenterApplication.service.impl;

import com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model.Center;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model.valueobject.Address;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto.CenterRequest;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto.CenterResponse;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.exception.CenterAlreadyExistsException;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.repository.CenterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class CenterServiceImplTest {

    @Mock
    CenterRepository centerRepository;

    @InjectMocks
    CenterServiceImpl centerServiceImpl;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldBeAbleToCreateInstanceOfServiceClass() {
        assertInstanceOf(CenterServiceImpl.class, centerServiceImpl);
    }

    @Test
    void shouldBeAbleToSaveCenter() throws CenterAlreadyExistsException {
        // arrange
        String centerName = "RB Tower4";
        String centerCode = "123456";
        Address address = new Address("Floor 4 tower b", "Pune", "Maharashtra", "413006");
        List<String> courseOffered = List.of("Python", "java");
        String phoneNumber = "9284522484";
        String email = "rahulbasutkar33@gmail.com";
        int capacity = 90;

        CenterRequest centerRequest = CenterRequest.builder()
                .centerCode(centerCode)
                .coursesOffered(courseOffered)
                .centerName(centerName)
                .address(address)
                .email(email)
                .phoneNumber(phoneNumber)
                .capacity(capacity)
                .build();

        Center center = Center.builder()
                .id("123456789")
                .date(new Date())
                .centerName(centerRequest.getCenterName())
                .phoneNumber(centerRequest.getPhoneNumber())
                .centerCode(centerRequest.getCenterCode())
                .email(centerRequest.getEmail())
                .capacity(centerRequest.getCapacity())
                .coursesOffered(centerRequest.getCoursesOffered())
                .address(centerRequest.getAddress())
                .build();

        when(centerRepository.save(any(Center.class))).thenReturn(center);

        // act
        CenterResponse centerResponse = centerServiceImpl.createCenter(centerRequest);

        // assert
        assertEquals(centerResponse.getCenterName(), centerRequest.getCenterName());
        assertNotNull(centerResponse);

    }

    @Test
    void shouldBeAbleToGetListOfCenter()
    {
        // arrange

        when(centerRepository.findAll()).thenReturn(List.of());
        // act
        List<CenterResponse> centerResponses=centerServiceImpl.getAllCenter();

        // assert
        assertEquals(0,centerResponses.size());
    }

    @Test
    void shouldBeAbleToGetCenterByName()
    {
        // arrange
        String centerName = "RB Tower4";
        String centerCode = "123456";
        Address address = new Address("Floor 4 tower b", "Pune", "Maharashtra", "413006");
        List<String> courseOffered = List.of("Python", "java");
        String phoneNumber = "9284522484";
        String email = "rahulbasutkar33@gmail.com";
        int capacity = 90;

        CenterRequest centerRequest = CenterRequest.builder()
                .centerCode(centerCode)
                .coursesOffered(courseOffered)
                .centerName(centerName)
                .address(address)
                .email(email)
                .phoneNumber(phoneNumber)
                .capacity(capacity)
                .build();

        Center center = Center.builder()
                .id("123456789")
                .date(new Date())
                .centerName(centerRequest.getCenterName())
                .phoneNumber(centerRequest.getPhoneNumber())
                .centerCode(centerRequest.getCenterCode())
                .email(centerRequest.getEmail())
                .capacity(centerRequest.getCapacity())
                .coursesOffered(centerRequest.getCoursesOffered())
                .address(centerRequest.getAddress())
                .build();

        when(centerRepository.findByCenterName(centerName)).thenReturn(center);

        CenterResponse centerResponse=centerServiceImpl.getCenterByName(centerName);

        assertEquals(centerResponse.getCenterName(),centerName);

    }


}




