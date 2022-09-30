package com.zemelya.repository.rentalAgreement;

import com.zemelya.domain.hibernate.HibernateBodyType;
import com.zemelya.domain.hibernate.HibernateRentalAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalAgreementSpringDataRepository extends JpaRepository<HibernateRentalAgreement, Long> {

    @Query(
            value =
                    "select * from rentalcars.rental_agreements",
            nativeQuery = true)
    List<HibernateRentalAgreement> findAllInfo();
}
