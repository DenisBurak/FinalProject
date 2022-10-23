package com.zemelya.repository.rentalAgreement;

import com.zemelya.domain.hibernate.HibernateRentalAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalAgreementSpringDataRepository
    extends JpaRepository<HibernateRentalAgreement, Long> {
  HibernateRentalAgreement findByUserId(Long userId);
}
