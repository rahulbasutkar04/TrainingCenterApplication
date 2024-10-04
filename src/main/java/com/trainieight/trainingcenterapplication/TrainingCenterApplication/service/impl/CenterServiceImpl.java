package com.trainieight.trainingcenterapplication.TrainingCenterApplication.service.impl;

import com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model.Center;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto.CenterDTO;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.exception.CenterAlreadyExistsException;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.repository.CenterRepository;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    private CenterRepository centerRepository;

    @Override
    public CenterDTO createCenter(CenterDTO centerDTO) throws CenterAlreadyExistsException {

        if (centerRepository.existsByCenterName(centerDTO.getCenterName()) ||
                centerRepository.existsByCenterCode(centerDTO.getCenterCode())) {
            throw new CenterAlreadyExistsException("A center with the same name and  code already exists.");
        }

        Center center1 = Center.builder()
                .centerName(centerDTO.getCenterName())
                .centerCode(centerDTO.getCenterCode())
                .email(centerDTO.getEmail())
                .capacity(centerDTO.getCapacity())
                .coursesOffered(centerDTO.getCoursesOffered())
                .phoneNumber(centerDTO.getPhoneNumber())
                .address(centerDTO.getAddress())
                .date(new Date())
                .build();

        Center savedCenter = centerRepository.save(center1);
        return mapToCenterDTO(savedCenter);
    }

    @Override
    public List<CenterDTO> getAllCenter() {
        List<CenterDTO> centerDTOList = new ArrayList<>();
        List<Center> centerList = centerRepository.findAll();
        for (Center c : centerList) {
            centerDTOList.add(mapToCenterDTO(c));
        }
        return centerDTOList;
    }

    @Override
    public CenterDTO getCenterByName(String centerName) {
        return mapToCenterDTO(centerRepository.findByCenterName(centerName));
    }

    private CenterDTO mapToCenterDTO(Center center1) {
        return CenterDTO.builder()
                .centerName(center1.getCenterName())
                .centerCode(center1.getCenterCode())
                .email(center1.getEmail())
                .capacity(center1.getCapacity())
                .coursesOffered(center1.getCoursesOffered())
                .phoneNumber(center1.getPhoneNumber())
                .address(center1.getAddress())
                .date(center1.getDate())
                .build();
    }

}

