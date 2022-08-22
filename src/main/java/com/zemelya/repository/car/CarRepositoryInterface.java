package com.zemelya.repository.car;

import com.zemelya.domain.Car;
import com.zemelya.repository.CRUDRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface CarRepositoryInterface extends CRUDRepository<Long, Car> {

    List<Map<String, Object>> getListOfCars();

    List<Map<String, Object>> getListOfAvailableCars(Timestamp date);

}
