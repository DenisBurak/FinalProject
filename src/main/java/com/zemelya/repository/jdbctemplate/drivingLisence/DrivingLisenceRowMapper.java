package com.zemelya.repository.jdbctemplate.drivingLisence;

import com.zemelya.domain.DrivingLisence;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.zemelya.repository.drivingLisences.DrivingLisenceTableColumns.CATEGORY;
import static com.zemelya.repository.drivingLisences.DrivingLisenceTableColumns.CHANGED;
import static com.zemelya.repository.drivingLisences.DrivingLisenceTableColumns.CREATED;
import static com.zemelya.repository.drivingLisences.DrivingLisenceTableColumns.DATE_OF_ISSUE;
import static com.zemelya.repository.drivingLisences.DrivingLisenceTableColumns.EXPIRATION_DATE;
import static com.zemelya.repository.drivingLisences.DrivingLisenceTableColumns.ID;
import static com.zemelya.repository.drivingLisences.DrivingLisenceTableColumns.IS_DELETED;
import static com.zemelya.repository.drivingLisences.DrivingLisenceTableColumns.SERIAL_NUMBER;
import static com.zemelya.repository.drivingLisences.DrivingLisenceTableColumns.USER_ID;


@Component
public class DrivingLisenceRowMapper implements RowMapper<DrivingLisence> {

  @Override
  public DrivingLisence mapRow(ResultSet rs, int i) throws SQLException {
    DrivingLisence drivingLisences = new DrivingLisence();

    drivingLisences.setId(rs.getLong(ID));
    drivingLisences.setSerialNumber(rs.getString(SERIAL_NUMBER));
    drivingLisences.setDateOfIssue(rs.getTimestamp(DATE_OF_ISSUE));
    drivingLisences.setExpirationDate(rs.getTimestamp(EXPIRATION_DATE));
    drivingLisences.setCategory(rs.getString(CATEGORY));
    drivingLisences.setUserId(rs.getInt(USER_ID));
    drivingLisences.setCreationDate(rs.getTimestamp(CREATED));
    drivingLisences.setModificationDate(rs.getTimestamp(CHANGED));
    drivingLisences.setIsDeleted(rs.getBoolean(IS_DELETED));

    return drivingLisences;
  }
}
