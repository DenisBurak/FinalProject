package com.zemelya.converters.drivingLicense;

import com.zemelya.controller.request.drivingLicenses.DrivingLicenseChangeRequest;
import com.zemelya.controller.request.drivingLicenses.DrivingLicenseCreateRequest;
import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.repository.drivingLicenses.DrivingLicensesSpringDataRepository;
import com.zemelya.service.user.UserServiceImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class DrivingLicenseEditRequestConverter extends DrivingLicenseBaseConverter<DrivingLicenseChangeRequest, HibernateDrivingLicense> {

  private final DrivingLicensesSpringDataRepository repository;

  public UserServiceImpl userService;

  public DrivingLicenseEditRequestConverter(DrivingLicensesSpringDataRepository repository) {
    this.repository = repository;
  }

  @Override
  public HibernateDrivingLicense convert(DrivingLicenseChangeRequest request) {

    HibernateDrivingLicense drivingLicense = repository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
    return doConvert(drivingLicense, request);
  }
}
