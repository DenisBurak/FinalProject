package com.zemelya.repository.car;

import com.zemelya.domain.hibernate.HibernateCar;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface CarSpringDataRepository extends JpaRepository<HibernateCar, Long> {
  @Cacheable("cars")
  Page<HibernateCar> findAll(Pageable pageable);

  @Query(
      value =
          "select distinct car.id, brand.brand_name, model.model_name, body.body_type_name, car.vin_number, car.volume, car.price "
              + "from rentalcars.cars AS car "
              + "inner join rentalcars.models AS model on car.model_id = model.id "
              + "inner join rentalcars.brands AS brand on model.brand_id = brand.id "
              + "inner join rentalcars.body_types AS body on model.body_type_id = body.id "
              + "where car.is_available "
              + "and NOT car.id IN(select ra.car_id from rentalcars.rental_agreements AS ra where ra.rental_start_date <= :dateParam and ra.expiration_date >= :dateParam)",
      nativeQuery = true)
  List<Object> showAvailableCars(Timestamp dateParam);

//  @Query(
//      value =
//          "select id, model_id, volume, vin_number, is_available, creation_date, modification_date, price "
//              + "from rentalcars.cars AS car "
//              + "where car.is_available "
//              + "and NOT car.id IN(select ra.car_id from rentalcars.rental_agreements AS ra where ra.rental_start_date <= :dateParam and ra.expiration_date >= :dateParam)",
//      nativeQuery = true)
//  List<HibernateCar> showAvailableCars(Timestamp dateParam);

  @Query(
      value =
          "select ra.car_id AS id, car.creation_date, car.modification_date, car.vin_number, car.price, car.is_available, car.model_id, car.volume, "
              + "Sum(DATE_PART('day', expiration_date - rental_start_date)) as CountOfDays "
              + "from rentalcars.rental_agreements AS ra "
              + "inner join rentalcars.cars as car on ra.car_id = car.id "
              + "group by ra.car_id, car.creation_date, car.modification_date, car.vin_number, car.price, car.is_available, car.model_id, car.volume "
              + "order by CountOfDays desc Limit :selectedLimit",
      nativeQuery = true)
  List<HibernateCar> showTopPopularCars(Integer selectedLimit);
}
