package com.zemelya.repository.brand;

import com.zemelya.domain.hibernate.HibernateBrand;
import com.zemelya.domain.hibernate.HibernateRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BrandSpringDataRepository extends CrudRepository<HibernateBrand, Integer>,
        JpaRepository<HibernateBrand, Integer>,
        PagingAndSortingRepository<HibernateBrand, Integer> {
}
