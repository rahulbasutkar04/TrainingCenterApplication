package com.trainieight.trainingcenterapplication.TrainingCenterApplication.service.impl;

import com.trainieight.trainingcenterapplication.TrainingCenterApplication.repository.CenterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;


@SpringBootTest
class CenterServiceImplTest {

    @Mock
    private CenterRepository centerRepository;

    @InjectMocks
    private CenterServiceImpl centerServiceImpl;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldBeAbleToCreateInstanceOfServiceClass() {
        assertInstanceOf(CenterServiceImpl.class, centerServiceImpl);
    }


}




