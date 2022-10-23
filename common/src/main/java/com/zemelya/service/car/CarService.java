package com.zemelya.service.car;

import com.zemelya.domain.hibernate.HibernateCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
  HibernateCar create(HibernateCar hibernateCar);

  HibernateCar delete(Long carId);

  HibernateCar update(HibernateCar hibernateCar);

  Page<HibernateCar> findAll(Pageable pageable);

  List<HibernateCar> findAll();

  HibernateCar findById(Long carId);
}
