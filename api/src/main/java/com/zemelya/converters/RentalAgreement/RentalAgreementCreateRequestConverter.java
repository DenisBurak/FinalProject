package com.zemelya.converters.RentalAgreement;

import com.zemelya.controller.request.rentalAgreement.RentalAgreementCreateRequest;
import com.zemelya.domain.hibernate.HibernateRentalAgreement;
import com.zemelya.service.car.CarService;
import com.zemelya.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class RentalAgreementCreateRequestConverter
    extends RentalAgreementBaseConverter<RentalAgreementCreateRequest, HibernateRentalAgreement> {

  private final UserService userService;

  private final CarService carService;

  @Override
  public HibernateRentalAgreement convert(RentalAgreementCreateRequest request) {

    HibernateRentalAgreement hibernateRentalAgreement = new HibernateRentalAgreement();

    hibernateRentalAgreement.setUser(userService.findById(request.getUserId()));

    hibernateRentalAgreement.setCar(carService.findById(request.getCarId()));

    hibernateRentalAgreement.setCreationDate(new Timestamp(new Date().getTime()));

    return doConvert(hibernateRentalAgreement, request);
  }
}
