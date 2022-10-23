package com.zemelya.converters.RentalAgreement;

import com.zemelya.controller.request.rentalAgreement.RentalAgreementChangeRequest;
import com.zemelya.domain.hibernate.HibernateRentalAgreement;
import com.zemelya.repository.rentalAgreement.RentalAgreementSpringDataRepository;
import com.zemelya.service.car.CarService;
import com.zemelya.service.user.UserService;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class RentalAgreementEditRequestConverter
    extends RentalAgreementBaseConverter<RentalAgreementChangeRequest, HibernateRentalAgreement> {

  private final RentalAgreementSpringDataRepository repository;

  private final UserService userService;

  private final CarService carService;

  public RentalAgreementEditRequestConverter(
      RentalAgreementSpringDataRepository repository,
      UserService userService,
      CarService carService) {
    this.repository = repository;
    this.userService = userService;
    this.carService = carService;
  }

  @Override
  public HibernateRentalAgreement convert(RentalAgreementChangeRequest request) {

    HibernateRentalAgreement hibernateRentalAgreement =
        repository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);

    hibernateRentalAgreement.setUser(userService.findById(request.getUserId()));

    hibernateRentalAgreement.setCar(carService.findById(request.getCarId()));

    return doConvert(hibernateRentalAgreement, request);
  }
}
