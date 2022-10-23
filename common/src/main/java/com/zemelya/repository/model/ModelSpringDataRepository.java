package com.zemelya.repository.model;

import com.zemelya.domain.hibernate.HibernateModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelSpringDataRepository extends JpaRepository<HibernateModel, Integer> {
    @Cacheable("models")
    @Query(value = "select hm.id, hm.modelName from HibernateModel hm")
    List<Object[]> findOnlyModels();
}
