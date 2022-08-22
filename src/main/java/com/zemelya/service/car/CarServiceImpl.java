package com.zemelya.service.car;

import com.zemelya.domain.Car;
import com.zemelya.repository.jdbctemplate.car.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Optional<Car> findOne(Long id) {
        return carRepository.findOne(id);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> findAll(int limit, int offset) {
        return carRepository.findAll(limit, offset);
    }

    @Override
    public Car create(Car object) {
        return carRepository.create(object);
    }

    @Override
    public Car update(Car object) {
        return carRepository.update(object);
    }

    @Override
    public Long delete(Long id) {
        return carRepository.delete(id);
    }

    @Override
    public List<Map<String, Object>> getListOfCars() {
        return carRepository.getListOfCars();
    }

    public List<Map<String, Object>> getListOfAvailableCars()
    {
        return carRepository.getListOfAvailableCars();
    };
}
