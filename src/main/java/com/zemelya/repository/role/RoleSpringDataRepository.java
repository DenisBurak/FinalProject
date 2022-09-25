package com.zemelya.repository.role;

import com.zemelya.domain.hibernate.HibernateRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleSpringDataRepository extends CrudRepository<HibernateRole, Integer>,
        JpaRepository<HibernateRole, Integer>,
        PagingAndSortingRepository<HibernateRole, Integer> {
}
