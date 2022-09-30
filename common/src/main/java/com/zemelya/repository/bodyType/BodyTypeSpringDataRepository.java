package com.zemelya.repository.bodyType;

import com.zemelya.domain.hibernate.HibernateBodyType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BodyTypeSpringDataRepository extends JpaRepository<HibernateBodyType, Integer> {

  @Cacheable("bodyTypes")
  @Query(
      value =
          "select bt.id, bt.body_type_name, bt.is_available from rentalcars.body_types AS bt",
      nativeQuery = true)
  List<HibernateBodyType> findAllOnlyBrands();
}
