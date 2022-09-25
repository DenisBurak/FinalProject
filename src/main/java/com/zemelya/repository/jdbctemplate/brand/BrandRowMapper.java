package com.zemelya.repository.jdbctemplate.brand;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.zemelya.repository.brand.BrandTableColumns.ID;
import static com.zemelya.repository.brand.BrandTableColumns.IS_AVAILABLE;
import static com.zemelya.repository.brand.BrandTableColumns.NAME;

@Component
public class BrandRowMapper implements RowMapper<Brand> {

  @Override
  public Brand mapRow(ResultSet rs, int i) throws SQLException {
    Brand brand = new Brand();

    brand.setId(rs.getInt(ID));
    brand.setBrandName(rs.getString(NAME));
    brand.setIsAvailable(rs.getBoolean(IS_AVAILABLE));

    return brand;
  }
}
