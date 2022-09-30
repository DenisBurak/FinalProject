package com.zemelya.repository.car;

import com.zemelya.domain.hibernate.HibernateCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarSpringDataRepository extends JpaRepository<HibernateCar, Long> {
}
