package com.zemelya.converters.drivingLicense;

import com.zemelya.controller.request.drivingLicenses.DrivingLicenseCreateRequest;
import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class DrivingLicenseCreateRequestConverter
    extends DrivingLicenseBaseConverter<DrivingLicenseCreateRequest, HibernateDrivingLicense> {

  @Override
  public HibernateDrivingLicense convert(DrivingLicenseCreateRequest request) {

    HibernateDrivingLicense drivingLicense = new HibernateDrivingLicense();

    drivingLicense.setCreationDate(new Timestamp(new Date().getTime()));

    return doConvert(drivingLicense, request);
  }
}
