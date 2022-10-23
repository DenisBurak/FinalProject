package com.zemelya.service.rentalAgreement;

import com.zemelya.domain.hibernate.HibernateRentalAgreement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RentalAgreementService {
  HibernateRentalAgreement create(HibernateRentalAgreement hibernateRentalAgreement);

  HibernateRentalAgreement update(HibernateRentalAgreement hibernateRentalAgreement);

  Page<HibernateRentalAgreement> findAll(Pageable pageable);

  List<HibernateRentalAgreement> findAll();

  HibernateRentalAgreement findById(Long rentalAgreementId);
}
