package com.zemelya.repository.user;

import com.zemelya.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserSpringDataRepository extends JpaRepository<HibernateUser, Long> {

  Optional<HibernateUser> findByCredentialsLogin(String login);

  @Modifying
  @Query(
      value = "insert into rentalcars.l_role_user(user_id, role_id) values (:user_id, :role_id)",
      nativeQuery = true)
  int createRoleRow(@Param("user_id") Long userId, @Param("role_id") Integer roleId);
}
