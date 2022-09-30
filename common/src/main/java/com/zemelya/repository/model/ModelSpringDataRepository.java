package com.zemelya.repository.model;

import com.zemelya.domain.hibernate.HibernateModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelSpringDataRepository extends JpaRepository<HibernateModel, Integer> {
}
