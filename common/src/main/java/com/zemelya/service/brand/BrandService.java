package com.zemelya.service.brand;

import com.zemelya.domain.hibernate.HibernateBrand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrandService {
  HibernateBrand create(HibernateBrand hibernateBrand);

  HibernateBrand delete(Integer userId);

  HibernateBrand update(HibernateBrand hibernateBrand);

  Page<HibernateBrand> findAll(Pageable pageable);

  List<HibernateBrand> findAll();

  HibernateBrand findById(Integer id);

}
