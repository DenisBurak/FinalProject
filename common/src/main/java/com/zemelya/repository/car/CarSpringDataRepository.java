package com.zemelya.repository.car;

import com.zemelya.domain.hibernate.HibernateCar;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarSpringDataRepository extends JpaRepository<HibernateCar, Long> {
  @Cacheable("cars")
  List<HibernateCar> findAll();
}
