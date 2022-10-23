package com.zemelya.service.rentalAgreement;

import com.zemelya.domain.hibernate.HibernateRentalAgreement;
import com.zemelya.repository.rentalAgreement.RentalAgreementSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalAgreementServiceImpl implements RentalAgreementService {

  private final RentalAgreementSpringDataRepository repository;

  @Override
  public HibernateRentalAgreement create(HibernateRentalAgreement hibernateRentalAgreement) {

    return saveRentalAgreement(hibernateRentalAgreement);
  }

  @Transactional
  @Override
  public HibernateRentalAgreement update(HibernateRentalAgreement hibernateRentalAgreement) {

    return saveRentalAgreement(hibernateRentalAgreement);
  }

  @Override
  public Page<HibernateRentalAgreement> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public List<HibernateRentalAgreement> findAll() {
    return repository.findAll();
  }

  @Override
  public HibernateRentalAgreement findById(Long rentalAgreementId) {
    Optional<HibernateRentalAgreement> result = repository.findById(rentalAgreementId);

    if (result.isPresent()) {
      return result.get();
    } else {
      throw new EntityNotFoundException(
          String.format("Rental agreement with this id \"%s\" is not found", rentalAgreementId));
    }
  }

  private HibernateRentalAgreement saveRentalAgreement(
      HibernateRentalAgreement hibernateRentalAgreement) {
    return repository.save(hibernateRentalAgreement);
  }
}
