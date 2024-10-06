package com.trainieight.trainingcenterapplication.TrainingCenterApplication.service.impl;

import com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model.Center;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto.CenterRequest;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto.CenterResponse;
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
    public CenterResponse createCenter(CenterRequest centerRequest) throws CenterAlreadyExistsException {

        if (centerRepository.existsByCenterName(centerRequest.getCenterName()) ||
                centerRepository.existsByCenterCode(centerRequest.getCenterCode())) {
            throw new CenterAlreadyExistsException("A center with the same name and  code already exists.");
        }

        Center center = Center.builder()
                .centerName(centerRequest.getCenterName())
                .centerCode(centerRequest.getCenterCode())
                .email(centerRequest.getEmail())
                .capacity(centerRequest.getCapacity())
                .coursesOffered(centerRequest.getCoursesOffered())
                .phoneNumber(centerRequest.getPhoneNumber())
                .address(centerRequest.getAddress())
                .date(new Date())
                .build();

        Center savedCenter = centerRepository.save(center);
        return mapToCenterResponse(savedCenter);
    }

    @Override
    public List<CenterResponse> getAllCenter() {
        List<CenterResponse> centerRequestList = new ArrayList<>();
        List<Center> centerList = centerRepository.findAll();
        for (Center c : centerList) {
            centerRequestList.add(mapToCenterResponse(c));
        }
        return centerRequestList;
    }

    @Override
    public CenterResponse getCenterByName(String centerName) {
        return mapToCenterResponse(centerRepository.findByCenterName(centerName));
    }

    private CenterResponse mapToCenterResponse(Center center) {

        System.out.println(center);
        if (center == null) {
            return null;
        }
        return CenterResponse.builder()
                .centerName(center.getCenterName())
                .centerCode(center.getCenterCode())
                .email(center.getEmail())
                .capacity(center.getCapacity())
                .coursesOffered(center.getCoursesOffered())
                .phoneNumber(center.getPhoneNumber())
                .address(center.getAddress())

                .build();
    }

}

