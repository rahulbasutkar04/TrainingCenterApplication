package com.trainieight.trainingcenterapplication.TrainingCenterApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model.valueobject.Address;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto.CenterRequest;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto.CenterResponse;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.exception.CenterAlreadyExistsException;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.service.CenterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CenterController.class)
class CenterControllerTest {
    @MockBean
    CenterService centerService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldBeAbleToGetListOfCenters() throws Exception {

        // arrange
        List<CenterResponse> centerResponses = new ArrayList<>();
        CenterResponse centerResponse = CenterResponse.builder()
                .id("12345")
                .centerName("Tower")
                .centerCode("123456")
                .email("example@gmail.com")
                .address(new Address("flat 20", "Solapur", "Maharashtra", "413006"))
                .date(new Date())
                .capacity(50)
                .coursesOffered(List.of("physics", "Chemistry"))
                .phoneNumber("9284522484")
                .build();
        centerResponses.add(centerResponse);
        when(centerService.getAllCenter()).thenReturn(centerResponses);

        // act & assert
        mockMvc.perform(MockMvcRequestBuilders.get("/center/api/v1/get"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldBeAbleToCreateCenter() throws Exception, CenterAlreadyExistsException {
        // arrange
        CenterRequest centerRequest = CenterRequest.builder()
                .centerName("Training Center A")
                .centerCode("ABC123456789")
                .address(new Address("123 Main St", "City", "State", "123456"))
                .capacity(100)
                .coursesOffered(List.of("Math", "Science"))
                .email("test@example.com")
                .phoneNumber("1234567890")
                .build();

        CenterResponse centerResponse = CenterResponse.builder()
                .id("12345")
                .centerName("Tower")
                .centerCode("ABC123456789")
                .email("example@gmail.com")
                .address(new Address("flat 20", "Solapur", "Maharashtra", "413006"))
                .date(new Date())
                .capacity(50)
                .coursesOffered(List.of("physics", "Chemistry"))
                .phoneNumber("9284522484")
                .build();

        when(centerService.createCenter(centerRequest)).thenReturn(centerResponse);

        // act & assert
        mockMvc.perform(post("/center/api/v1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(centerRequest)))
                .andExpect(status().isCreated()) // Expect HTTP 201 Created
                .andExpect(jsonPath("$.id").value("12345"))
                .andExpect(jsonPath("$.centerName").value("Tower"))
                .andExpect(jsonPath("$.centerCode").value("ABC123456789"))
                .andExpect(jsonPath("$.email").value("example@gmail.com"))
                .andExpect(jsonPath("$.address.detailedAddress").value("flat 20"))
                .andExpect(jsonPath("$.address.city").value("Solapur"))
                .andExpect(jsonPath("$.address.state").value("Maharashtra"))
                .andExpect(jsonPath("$.address.pinCode").value("413006"))
                .andExpect(jsonPath("$.capacity").value(50))
                .andExpect(jsonPath("$.coursesOffered[0]").value("physics"))
                .andExpect(jsonPath("$.coursesOffered[1]").value("Chemistry"))
                .andExpect(jsonPath("$.phoneNumber").value("9284522484"));
    }

    @Test
    void shouldBeAbleToGetCenterByName() throws Exception {
        // arrange
        CenterResponse centerResponse = CenterResponse.builder()
                .id("12345")
                .centerName("Training")
                .centerCode("ABC123456789")
                .email("example@gmail.com")
                .address(new Address("123 Main St", "City", "State", "123456"))
                .date(new Date())
                .capacity(100)
                .coursesOffered(List.of("Math", "Science"))
                .phoneNumber("1234567890")
                .build();

        when(centerService.getCenterByName("Training")).thenReturn(centerResponse);

        // act & assert
        mockMvc.perform(MockMvcRequestBuilders.get("/center/api/v1/get/Training")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("12345"))
                .andExpect(jsonPath("$.centerName").value("Training"))
                .andExpect(jsonPath("$.centerCode").value("ABC123456789"))
                .andExpect(jsonPath("$.email").value("example@gmail.com"))
                .andExpect(jsonPath("$.address.detailedAddress").value("123 Main St"))
                .andExpect(jsonPath("$.address.city").value("City"))
                .andExpect(jsonPath("$.address.state").value("State"))
                .andExpect(jsonPath("$.address.pinCode").value("123456"))
                .andExpect(jsonPath("$.capacity").value(100))
                .andExpect(jsonPath("$.coursesOffered[0]").value("Math"))
                .andExpect(jsonPath("$.coursesOffered[1]").value("Science"))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"));
    }

}