package com.zemelya.service.drivingLisence;

import com.zemelya.domain.DrivingLisence;
import com.zemelya.repository.jdbctemplate.drivingLisence.DrivingLisenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrivingLisenceServiceImpl implements DrivingLisenceService {

    private final DrivingLisenceRepository drivingLisenceRepository;

    @Override
    public DrivingLisence findById(Long id) {
        return drivingLisenceRepository.findById(id);
    }

    @Override
    public Optional<DrivingLisence> findOne(Long id) {
        return drivingLisenceRepository.findOne(id);
    }

    @Override
    public List<DrivingLisence> findAll() {
        return drivingLisenceRepository.findAll();
    }

    @Override
    public List<DrivingLisence> findAll(int limit, int offset) {
        return drivingLisenceRepository.findAll(limit, offset);
    }

    @Override
    public DrivingLisence create(DrivingLisence object) {
        return drivingLisenceRepository.create(object);
    }

    @Override
    public DrivingLisence update(DrivingLisence object) {
        return drivingLisenceRepository.update(object);
    }

    @Override
    public Long delete(Long id) {
        return drivingLisenceRepository.delete(id);
    }
}
