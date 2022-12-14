package com.zemelya.service.drivingLicense;

import com.zemelya.domain.hibernate.HibernateDrivingLicense;
import com.zemelya.repository.drivingLicenses.DrivingLicensesSpringDataRepository;
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
public class DrivingLicenseServiceImpl implements DrivingLicenseService {

  private final DrivingLicensesSpringDataRepository repository;

  @Override
  public HibernateDrivingLicense create(HibernateDrivingLicense hibernateDrivingLicense) {

    return saveDrivingLicense(hibernateDrivingLicense);
  }

  @Override
  public HibernateDrivingLicense delete(Long drivingLicenseId) {

    HibernateDrivingLicense hibernateDrivingLicense = findById(drivingLicenseId);

    hibernateDrivingLicense.setIsDeleted(true);
    repository.save(hibernateDrivingLicense);

    return findById(drivingLicenseId);
  }

  @Transactional
  @Override
  public HibernateDrivingLicense update(HibernateDrivingLicense hibernateDrivingLicense) {

    return saveDrivingLicense(hibernateDrivingLicense);
  }

  @Override
  public Page<HibernateDrivingLicense> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public List<HibernateDrivingLicense> findAll() {
    return repository.findAll();
  }

  @Override
  public HibernateDrivingLicense findById(Long drivingLicenseId) {
    Optional<HibernateDrivingLicense> result = repository.findById(drivingLicenseId);

    if (result.isPresent()) {
      return result.get();
    } else {
      throw new EntityNotFoundException(
          String.format("Driving license with this id \"%s\" is not found", drivingLicenseId));
    }
  }

  @Override
  public HibernateDrivingLicense findByUserId(Long id) {
    HibernateDrivingLicense hibernateDrivingLicense = repository.findByUserId(id);
    if (hibernateDrivingLicense != null) {
      return hibernateDrivingLicense;
    } else {
      throw new EntityNotFoundException(String.format("User's driving license is not found"));
    }
  }

  private HibernateDrivingLicense saveDrivingLicense(
      HibernateDrivingLicense hibernateDrivingLicense) {
    return repository.save(hibernateDrivingLicense);
  }
}
