package com.trainieight.trainingcenterapplication.TrainingCenterApplication.service;

import com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto.CenterDTO;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.exception.CenterAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CenterService {
    CenterDTO createCenter(CenterDTO centerDTO) throws CenterAlreadyExistsException;

    List<CenterDTO> getAllCenter();

    CenterDTO getCenterByName(String centerName);

}
