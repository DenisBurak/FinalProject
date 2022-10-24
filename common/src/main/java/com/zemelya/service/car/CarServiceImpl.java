package com.zemelya.service.car;

import com.zemelya.domain.hibernate.HibernateCar;
import com.zemelya.repository.car.CarSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

  private final CarSpringDataRepository repository;

  @Override
  public HibernateCar create(HibernateCar hibernateCar) {

    return saveCar(hibernateCar);
  }

  @Override
  public HibernateCar delete(Long carId) {

    HibernateCar hibernateCar = findById(carId);

    hibernateCar.setIsAvailable(false);
    repository.save(hibernateCar);

    return findById(carId);
  }

  @Transactional
  @Override
  public HibernateCar update(HibernateCar hibernateCar) {

    return saveCar(hibernateCar);
  }

  @Override
  public Page<HibernateCar> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public List<HibernateCar> findAll() {
    return repository.findAll();
  }

  @Override
  public HibernateCar findById(Long carId) {
    Optional<HibernateCar> result = repository.findById(carId);

    if (result.isPresent()) {
      return result.get();
    } else {
      throw new EntityNotFoundException(
          String.format("Car with this id \"%s\" is not found", carId));
    }
  }

  @Override
  public List<Object> showAvailableCars(Timestamp date) {
    return repository.showAvailableCars(date);
  }

  @Override
  public List<HibernateCar> showTopPopularCars(Integer selectedLimit) {
    return repository.showTopPopularCars(selectedLimit);
  }

  private HibernateCar saveCar(HibernateCar hibernateCar) {
    return repository.save(hibernateCar);
  }
}
