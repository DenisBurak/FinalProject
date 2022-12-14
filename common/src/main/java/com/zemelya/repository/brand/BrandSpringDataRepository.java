package com.zemelya.repository.brand;

import com.zemelya.domain.hibernate.HibernateBrand;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BrandSpringDataRepository extends JpaRepository<HibernateBrand, Integer> {

    @Cacheable("brands")
    List<HibernateBrand> findAll();
}
