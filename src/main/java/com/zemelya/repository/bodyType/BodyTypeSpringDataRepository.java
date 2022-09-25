package com.zemelya.repository.bodyType;

import com.zemelya.domain.hibernate.HibernateBodyType;
import com.zemelya.domain.hibernate.HibernateBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BodyTypeSpringDataRepository extends CrudRepository<HibernateBodyType, Integer>,
        JpaRepository<HibernateBodyType, Integer>,
        PagingAndSortingRepository<HibernateBodyType, Integer> {
}
