package com.zemelya.repository.model;

import com.zemelya.domain.hibernate.HibernateModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ModelSpringDataRepository extends CrudRepository<HibernateModel, Integer>,
        PagingAndSortingRepository<HibernateModel, Integer> {
}
