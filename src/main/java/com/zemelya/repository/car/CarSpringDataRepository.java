package com.zemelya.repository.car;

import com.zemelya.domain.hibernate.HibernateCar;
import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarSpringDataRepository extends CrudRepository<HibernateCar, Long>,
        JpaRepository<HibernateCar, Long>,
        PagingAndSortingRepository<HibernateCar, Long> {
}
