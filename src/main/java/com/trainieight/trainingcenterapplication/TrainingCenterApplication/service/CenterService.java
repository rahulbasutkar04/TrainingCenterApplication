package com.trainieight.trainingcenterapplication.TrainingCenterApplication.service;

import com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto.CenterRequest;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto.CenterResponse;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.exception.CenterAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CenterService {
    CenterResponse createCenter(CenterRequest centerRequest) throws CenterAlreadyExistsException;

    List<CenterResponse> getAllCenter();

    CenterResponse getCenterByName(String centerName);

}
