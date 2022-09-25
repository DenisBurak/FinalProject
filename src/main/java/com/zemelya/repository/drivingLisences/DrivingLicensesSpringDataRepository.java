package com.zemelya.repository.drivingLisences;

import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DrivingLicensesSpringDataRepository extends CrudRepository<HibernateDrivingLicense, Long>,
        JpaRepository<HibernateDrivingLicense, Long>,
        PagingAndSortingRepository<HibernateDrivingLicense, Long> {
}
