package com.zemelya.repository.drivingLicenses;

import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrivingLicensesSpringDataRepository
    extends JpaRepository<HibernateDrivingLicense, Long> {
  HibernateDrivingLicense findByUserId(Long userId);
}
