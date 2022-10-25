package com.zemelya.converters.RentalAgreement;

import com.zemelya.controller.request.rentalAgreement.RentalAgreementChangeRequest;
import com.zemelya.domain.hibernate.HibernateRentalAgreement;
import com.zemelya.service.car.CarService;
import com.zemelya.service.rentalAgreement.RentalAgreementService;
import com.zemelya.service.user.UserService;
import org.springframework.stereotype.Component;

@Component
public class RentalAgreementEditRequestConverter
    extends RentalAgreementBaseConverter<RentalAgreementChangeRequest, HibernateRentalAgreement> {

  private final RentalAgreementService service;

  private final UserService userService;

  private final CarService carService;

  public RentalAgreementEditRequestConverter(
      RentalAgreementService service, UserService userService, CarService carService) {
    this.service = service;
    this.userService = userService;
    this.carService = carService;
  }

  @Override
  public HibernateRentalAgreement convert(RentalAgreementChangeRequest request) {

    HibernateRentalAgreement hibernateRentalAgreement = service.findById(request.getId());

    hibernateRentalAgreement.setUser(userService.findById(request.getUserId()));

    hibernateRentalAgreement.setCar(carService.findById(request.getCarId()));

    return doConvert(hibernateRentalAgreement, request);
  }
}
