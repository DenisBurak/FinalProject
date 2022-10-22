package com.zemelya.service.drivingLicense;

import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DrivingLicenseService {
  HibernateDrivingLicense create(HibernateDrivingLicense hibernateDrivingLicense);

  HibernateDrivingLicense delete(Long drivingLicenseId);

  HibernateDrivingLicense update(HibernateDrivingLicense hibernateDrivingLicense);

  Page<HibernateDrivingLicense> findAll(Pageable pageable);

  List<HibernateDrivingLicense> findAll();

  HibernateDrivingLicense findById(Long id);

  HibernateDrivingLicense findByUserId(Long id);
}
