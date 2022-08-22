package com.zemelya.repository.jdbctemplate.bodyType;

import com.zemelya.domain.BodyType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.zemelya.repository.bodyType.BodyTypeTableColumns.ID;
import static com.zemelya.repository.bodyType.BodyTypeTableColumns.IS_AVAILABLE;
import static com.zemelya.repository.bodyType.BodyTypeTableColumns.NAME;

@Component
public class BodyTypeRowMapper implements RowMapper<BodyType> {

  @Override
  public BodyType mapRow(ResultSet rs, int i) throws SQLException {
    BodyType bodyType = new BodyType();

    bodyType.setId(rs.getInt(ID));
    bodyType.setBodyTypeName(rs.getString(NAME));
    bodyType.setIsAvailable(rs.getBoolean(IS_AVAILABLE));

    return bodyType;
  }
}
