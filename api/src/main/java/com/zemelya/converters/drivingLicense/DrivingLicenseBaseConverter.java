package com.zemelya.converters.drivingLicense;

import com.zemelya.controller.request.drivingLicenses.DrivingLicenseCreateRequest;
import com.zemelya.controller.request.user.UserCreateRequest;
import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import com.zemelya.domain.hibernate.HibernateUser;
import com.zemelya.service.user.UserServiceImpl;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class DrivingLicenseBaseConverter<S, T> implements Converter<S, T> {

    public UserServiceImpl userService;

    protected HibernateDrivingLicense doConvert(HibernateDrivingLicense drivingLicense, DrivingLicenseCreateRequest request) {

        drivingLicense.setSerialNumber(request.getSerialNumber());
        drivingLicense.setCategory(request.getCategory());
        drivingLicense.setDateOfIssue(request.getDateOfIssue());
        drivingLicense.setExpirationDate(request.getExpirationDate());
        drivingLicense.setUser(userService.findById(request.getUserId()));

        /*System fields filling*/
        drivingLicense.setModificationDate(new Timestamp(new Date().getTime()));
        drivingLicense.setIsDeleted(false);

        return drivingLicense;
    }
}
