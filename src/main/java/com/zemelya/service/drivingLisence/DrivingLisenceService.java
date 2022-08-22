package com.zemelya.service.drivingLisence;

import com.zemelya.domain.DrivingLisence;

import java.util.List;
import java.util.Optional;

public interface DrivingLisenceService {

    DrivingLisence findById(Long id);

    Optional<DrivingLisence> findOne(Long id);

    List<DrivingLisence> findAll();

    List<DrivingLisence> findAll(int limit, int offset);

    DrivingLisence create(DrivingLisence object);

    DrivingLisence update(DrivingLisence object);

    Long delete(Long id);
}
