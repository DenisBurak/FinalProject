package com.zemelya.repository.jdbctemplate.user;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.zemelya.repository.user.UserTableColumns.BIRTH_DATE;
import static com.zemelya.repository.user.UserTableColumns.CHANGED;
import static com.zemelya.repository.user.UserTableColumns.CREATED;
import static com.zemelya.repository.user.UserTableColumns.ID;
import static com.zemelya.repository.user.UserTableColumns.IS_DELETED;
import static com.zemelya.repository.user.UserTableColumns.NAME;
import static com.zemelya.repository.user.UserTableColumns.SURNAME;
import static com.zemelya.repository.user.UserTableColumns.LOGIN;
import static com.zemelya.repository.user.UserTableColumns.PASSWORD;
import static com.zemelya.repository.user.UserTableColumns.EMAIL;
import static com.zemelya.repository.user.UserTableColumns.ROLE_ID;

@Component
public class UserRowMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet rs, int i) throws SQLException {
    User user = new User();

    user.setId(rs.getInt(ID));
    user.setUserName(rs.getString(NAME));
    user.setSurname(rs.getString(SURNAME));
    user.setLogin(rs.getString(LOGIN));
    user.setPassword(rs.getString(PASSWORD));
    user.setEmail(rs.getString(EMAIL));
    user.setBirth(rs.getTimestamp(BIRTH_DATE));
    user.setRoleId(rs.getInt(ROLE_ID));
    user.setCreationDate(rs.getTimestamp(CREATED));
    user.setModificationDate(rs.getTimestamp(CHANGED));
    user.setIsDeleted(rs.getBoolean(IS_DELETED));

    return user;
  }
}
