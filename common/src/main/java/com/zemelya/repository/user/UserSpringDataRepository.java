package com.zemelya.repository.user;

import com.zemelya.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface UserSpringDataRepository extends JpaRepository<HibernateUser, Long> {

  Optional<HibernateUser> findByCredentialsLogin(String login);

  List<HibernateUser> findByCredentialsLoginAndUserNameAndBirth(
      String login, String name, Timestamp birthDate);

  List<HibernateUser> findByCredentialsLoginAndUserNameOrBirthOrderByIdDescUserNameDesc(
      String login, String name, Timestamp birthDate);

  // select * from m_users where (login = ? and name = ?) or birth_date = ?

  @Query(value = "select u from HibernateUser u")
  List<HibernateUser> findByHQLQuery();

  @Query(value = "select * from rentalcars.users", nativeQuery = true)
  List<HibernateUser> findByHQLQueryNative();

  @Query(
      value = "select u from HibernateUser u where u.credentials.login = :login and u.userName = :userName")
  List<HibernateUser> findByHQLQuery(String login, @Param("userName") String name);

  @Query("select u.id, u.userName from HibernateUser u")
  List<Object[]> getPartsOfUser();

  @Modifying
  @Query(
      value = "insert into rentalcars.l_role_user(user_id, role_id) values (:user_id, :role_id)",
      nativeQuery = true)
  int createRoleRow(@Param("user_id") Long userId, @Param("role_id") Integer roleId);
}
