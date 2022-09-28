package com.zemelya.repository.rentalAgreement;

import com.zemelya.domain.hibernate.HibernateRentalAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RentalAgreementSpringDataRepository extends CrudRepository<HibernateRentalAgreement, Long>,
        JpaRepository<HibernateRentalAgreement, Long>,
        PagingAndSortingRepository<HibernateRentalAgreement, Long> {
}
