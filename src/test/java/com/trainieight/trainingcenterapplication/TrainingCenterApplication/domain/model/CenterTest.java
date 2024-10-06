package com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CenterTest {


    @Test
    void shouldBeAbleToCreateInstanceOfCenterClass()
    {
        Center center=Center.builder().build();
        assertInstanceOf(Center.class,center);
    }
}