package com.trainieight.trainingcenterapplication.TrainingCenterApplication.service.impl;

import com.trainieight.trainingcenterapplication.TrainingCenterApplication.exception.CenterAlreadyExistsException;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.repository.CenterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;


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

    }


}




