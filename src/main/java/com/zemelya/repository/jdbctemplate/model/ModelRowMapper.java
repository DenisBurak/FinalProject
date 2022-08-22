package com.zemelya.repository.jdbctemplate.model;

import com.zemelya.domain.Model;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.zemelya.repository.model.ModelTableColumns.BODY_TYPE_ID;
import static com.zemelya.repository.model.ModelTableColumns.BRAND_ID;
import static com.zemelya.repository.model.ModelTableColumns.CHANGED;
import static com.zemelya.repository.model.ModelTableColumns.CREATED;
import static com.zemelya.repository.model.ModelTableColumns.ID;
import static com.zemelya.repository.model.ModelTableColumns.IS_AVAILABLE;
import static com.zemelya.repository.model.ModelTableColumns.NAME;

@Component
public class ModelRowMapper implements RowMapper<Model> {

  @Override
  public Model mapRow(ResultSet rs, int i) throws SQLException {
    Model model = new Model();

    model.setId(rs.getInt(ID));
    model.setModelName(rs.getString(NAME));
    model.setBrandId(rs.getInt(BRAND_ID));
    model.setBodyTypeId(rs.getInt(BODY_TYPE_ID));
    model.setCreationDate(rs.getTimestamp(CREATED));
    model.setModificationDate(rs.getTimestamp(CHANGED));
    model.setIsAvailable(rs.getBoolean(IS_AVAILABLE));

    return model;
  }
}
