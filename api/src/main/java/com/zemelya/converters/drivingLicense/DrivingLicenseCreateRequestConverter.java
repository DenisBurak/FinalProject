package com.zemelya.converters.drivingLicense;

import com.zemelya.controller.request.drivingLicenses.DrivingLicenseCreateRequest;
import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import com.zemelya.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class DrivingLicenseCreateRequestConverter
    extends DrivingLicenseBaseConverter<DrivingLicenseCreateRequest, HibernateDrivingLicense> {

  private final UserService userService;

  @Override
  public HibernateDrivingLicense convert(DrivingLicenseCreateRequest request) {

    HibernateDrivingLicense drivingLicense = new HibernateDrivingLicense();

    drivingLicense.setUser(userService.findById(request.getUserId()));

    drivingLicense.setCreationDate(new Timestamp(new Date().getTime()));

    return doConvert(drivingLicense, request);
  }
}
