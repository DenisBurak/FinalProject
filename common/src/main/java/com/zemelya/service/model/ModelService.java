package com.zemelya.service.model;

import com.zemelya.domain.hibernate.HibernateModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModelService {
  HibernateModel create(HibernateModel hibernateModel);

  HibernateModel delete(Integer modelId);

  HibernateModel update(HibernateModel hibernateModel);

  Page<HibernateModel> findAll(Pageable pageable);

  List<HibernateModel> findAll();

  HibernateModel findById(Integer modelId);

  List<Object[]> findOnlyModels();

}
