package com.zemelya.converters.drivingLicense;

import com.zemelya.controller.request.drivingLicenses.DrivingLicenseChangeRequest;
import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import com.zemelya.service.drivingLicense.DrivingLicenseService;
import com.zemelya.service.user.UserService;
import org.springframework.stereotype.Component;

@Component
public class DrivingLicenseEditRequestConverter
    extends DrivingLicenseBaseConverter<DrivingLicenseChangeRequest, HibernateDrivingLicense> {

  private final DrivingLicenseService service;

  private final UserService userService;

  public DrivingLicenseEditRequestConverter(
      DrivingLicenseService service, UserService userService) {
    this.service = service;
    this.userService = userService;
  }

  @Override
  public HibernateDrivingLicense convert(DrivingLicenseChangeRequest request) {

    HibernateDrivingLicense drivingLicense = service.findById(request.getId());

    drivingLicense.setUser(userService.findById(request.getUserId()));

    return doConvert(drivingLicense, request);
  }
}
