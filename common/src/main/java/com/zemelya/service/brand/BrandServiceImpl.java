package com.zemelya.service.brand;

import com.zemelya.domain.hibernate.HibernateBodyType;
import com.zemelya.domain.hibernate.HibernateBrand;
import com.zemelya.repository.bodyType.BodyTypeSpringDataRepository;
import com.zemelya.repository.brand.BrandSpringDataRepository;
import com.zemelya.service.bodyType.BodyTypeService;
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
public class BrandServiceImpl implements BrandService {

  private final BrandSpringDataRepository repository;

  @Override
  public HibernateBrand create(HibernateBrand hibernateBrand) {

    return saveBrand(hibernateBrand);
  }

  @Override
  public HibernateBrand delete(Integer brandId) {

    HibernateBrand hibernateBrand = findById(brandId);

    hibernateBrand.setIsAvailable(false);
    repository.save(hibernateBrand);

    return findById(brandId);
  }

  @Transactional
  @Override
  public HibernateBrand update(HibernateBrand hibernateBrand) {

    return saveBrand(hibernateBrand);
  }

  @Override
  public Page<HibernateBrand> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public List<HibernateBrand> findAll() {
    return repository.findAll();
  }

  @Override
  public HibernateBrand findById(Integer brandId) {
    Optional<HibernateBrand> result = repository.findById(brandId);

    if (result.isPresent()) {
      return result.get();
    } else {
      throw new EntityNotFoundException(
          String.format("Brand with this id \"%s\" is not found", brandId));
    }
  }

  private HibernateBrand saveBrand(HibernateBrand hibernateBrand) {
    return repository.save(hibernateBrand);
  }
}
