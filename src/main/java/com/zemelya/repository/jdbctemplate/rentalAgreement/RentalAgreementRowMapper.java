package com.zemelya.repository.jdbctemplate.rentalAgreement;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.zemelya.repository.rentalAgreement.RentalAgreementlTableColumns.CAR_ID;
import static com.zemelya.repository.rentalAgreement.RentalAgreementlTableColumns.CHANGED;
import static com.zemelya.repository.rentalAgreement.RentalAgreementlTableColumns.CREATED;
import static com.zemelya.repository.rentalAgreement.RentalAgreementlTableColumns.EXPIRATION_DATE;
import static com.zemelya.repository.rentalAgreement.RentalAgreementlTableColumns.ID;
import static com.zemelya.repository.rentalAgreement.RentalAgreementlTableColumns.RENTAL_START_DATE;
import static com.zemelya.repository.rentalAgreement.RentalAgreementlTableColumns.TOTAL_COST;
import static com.zemelya.repository.rentalAgreement.RentalAgreementlTableColumns.USER_ID;

@Component
public class RentalAgreementRowMapper implements RowMapper<RentalAgreement> {

  @Override
  public RentalAgreement mapRow(ResultSet rs, int i) throws SQLException {
    RentalAgreement rentalAgreement = new RentalAgreement();

    rentalAgreement.setId(rs.getLong(ID));
    rentalAgreement.setUserId(rs.getInt(USER_ID));
    rentalAgreement.setCarId(rs.getLong(CAR_ID));
    rentalAgreement.setRentalStartDate(rs.getTimestamp(RENTAL_START_DATE));
    rentalAgreement.setExpirationDate(rs.getTimestamp(EXPIRATION_DATE));
    rentalAgreement.setTotalCost(rs.getDouble(TOTAL_COST));
    rentalAgreement.setCreationDate(rs.getTimestamp(CREATED));
    rentalAgreement.setModificationDate(rs.getTimestamp(CHANGED));


    return rentalAgreement;
  }
}
