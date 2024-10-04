package com.trainieight.trainingcenterapplication.TrainingCenterApplication.repository;

import com.trainieight.trainingcenterapplication.TrainingCenterApplication.domain.model.Center;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends MongoRepository<Center, String> {
    Center findByCenterName(String centerName);

    boolean existsByCenterName(String centerName);

    boolean existsByCenterCode(String centerCode);

}
