package com.zemelya.service.bodyType;

import com.zemelya.domain.hibernate.HibernateBodyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BodyTypeService {
  HibernateBodyType create(HibernateBodyType hibernateBodyType);

  HibernateBodyType delete(Integer userId);

  HibernateBodyType update(HibernateBodyType hibernateBodyType);

  Page<HibernateBodyType> findAll(Pageable pageable);

  List<HibernateBodyType> findAll();

  HibernateBodyType findById(Integer id);

  List<Object[]> findOnlyBodyTypes();
}
