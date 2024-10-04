package com.trainieight.trainingcenterapplication.TrainingCenterApplication.controller;

import com.trainieight.trainingcenterapplication.TrainingCenterApplication.dto.CenterDTO;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.exception.CenterAlreadyExistsException;
import com.trainieight.trainingcenterapplication.TrainingCenterApplication.service.CenterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center/api/v1")
public class CenterController {

    @Autowired
    private CenterService centerService;

    @PostMapping("/create")
    public ResponseEntity<CenterDTO> createCenter(@Valid @RequestBody CenterDTO centerDTO) throws CenterAlreadyExistsException {
        return new ResponseEntity<>(centerService.createCenter(centerDTO), HttpStatus.CREATED);
    }


    @GetMapping("/get")
    public ResponseEntity<List<CenterDTO>> getAllCenters() {
        return new ResponseEntity<>(centerService.getAllCenter(), HttpStatus.OK);
    }


    @GetMapping("/get/{centerName}")
    public ResponseEntity<CenterDTO> getCenterByName(@PathVariable String centerName) {
        return new ResponseEntity<>(centerService.getCenterByName(centerName), HttpStatus.OK);
    }
}
