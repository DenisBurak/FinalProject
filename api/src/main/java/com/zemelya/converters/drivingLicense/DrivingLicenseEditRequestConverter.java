package com.zemelya.converters.drivingLicense;

import com.zemelya.controller.request.drivingLicenses.DrivingLicenseChangeRequest;
import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import com.zemelya.repository.drivingLicenses.DrivingLicensesSpringDataRepository;
import com.zemelya.service.user.UserService;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class DrivingLicenseEditRequestConverter
    extends DrivingLicenseBaseConverter<DrivingLicenseChangeRequest, HibernateDrivingLicense> {

  private final DrivingLicensesSpringDataRepository repository;

  private final UserService userService;

  public DrivingLicenseEditRequestConverter(DrivingLicensesSpringDataRepository repository, UserService userService) {
    this.repository = repository;
    this.userService = userService;
  }

  @Override
  public HibernateDrivingLicense convert(DrivingLicenseChangeRequest request) {

    HibernateDrivingLicense drivingLicense =
        repository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
    drivingLicense.setUser(userService.findById(request.getUserId()));
    return doConvert(drivingLicense, request);
  }
}
