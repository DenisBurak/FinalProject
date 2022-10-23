package com.zemelya.converters.RentalAgreement;

import com.zemelya.controller.request.rentalAgreement.RentalAgreementCreateRequest;
import com.zemelya.domain.hibernate.HibernateRentalAgreement;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class RentalAgreementBaseConverter<S, T> implements Converter<S, T> {

  protected HibernateRentalAgreement doConvert(
      HibernateRentalAgreement hibernateRentalAgreement, RentalAgreementCreateRequest request) {

    hibernateRentalAgreement.setRentalStartDate(request.getRentalStartDate());
    hibernateRentalAgreement.setExpirationDate(request.getExpirationDate());
    hibernateRentalAgreement.setTotalCost(request.getTotalCost());

    /*System fields filling*/
    hibernateRentalAgreement.setModificationDate(new Timestamp(new Date().getTime()));

    return hibernateRentalAgreement;
  }
}
