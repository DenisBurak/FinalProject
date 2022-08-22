package com.zemelya.service.car;

import com.zemelya.domain.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CarService {

    Car findById(Long id);

    Optional<Car> findOne(Long id);

    List<Car> findAll();

    List<Car> findAll(int limit, int offset);

    Car create(Car object);

    Car update(Car object);

    Long delete(Long id);

    List<Map<String, Object>> getListOfCars();

    List<Map<String, Object>> getListOfAvailableCars();

}
