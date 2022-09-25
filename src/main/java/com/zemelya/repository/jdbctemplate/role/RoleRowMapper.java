package com.zemelya.repository.jdbctemplate.role;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.zemelya.repository.role.RoleTableColumns.CHANGED;
import static com.zemelya.repository.role.RoleTableColumns.CREATED;
import static com.zemelya.repository.role.RoleTableColumns.ID;
import static com.zemelya.repository.role.RoleTableColumns.IS_DELETED;
import static com.zemelya.repository.role.RoleTableColumns.NAME;


@Component
public class RoleRowMapper implements RowMapper<Role> {

  @Override
  public Role mapRow(ResultSet rs, int i) throws SQLException {
    Role role = new Role();

    role.setId(rs.getInt(ID));
    role.setRoleName(rs.getString(NAME));
    role.setCreationDate(rs.getTimestamp(CREATED));
    role.setModificationDate(rs.getTimestamp(CHANGED));
    role.setIsDeleted(rs.getBoolean(IS_DELETED));

    return role;
  }
}
