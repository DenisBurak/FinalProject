package com.zemelya.repository.role;

import com.zemelya.domain.hibernate.HibernateRole;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleSpringDataRepository extends JpaRepository<HibernateRole, Integer> {

//    List<HibernateRole> findByUserId(Long UserId);
    @Query(value = "select * from rentalcars.roles " +
            "inner join rentalcars.l_role_user " +
            "on rentalcars.roles.id = rentalcars.l_role_user.role_id " +
            "where rentalcars.l_role_user.user_id = :userId"
            , nativeQuery = true)
    List<HibernateRole> findByUserId(Long userId);

    @Cacheable("roles")
    @Query(value = "select r from HibernateRole r")
    List<HibernateRole> findAllCustom();
}
