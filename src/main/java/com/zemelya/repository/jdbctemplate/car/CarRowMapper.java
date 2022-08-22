package com.zemelya.repository.jdbctemplate.car;

import com.zemelya.domain.Car;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.zemelya.repository.car.CarTableColumns.CHANGED;
import static com.zemelya.repository.car.CarTableColumns.CREATED;
import static com.zemelya.repository.car.CarTableColumns.ID;
import static com.zemelya.repository.car.CarTableColumns.IS_AVAILABLE;
import static com.zemelya.repository.car.CarTableColumns.MODEL_ID;
import static com.zemelya.repository.car.CarTableColumns.PRICE;
import static com.zemelya.repository.car.CarTableColumns.VIN_NUMBER;
import static com.zemelya.repository.car.CarTableColumns.VOLUME;


@Component
public class CarRowMapper implements RowMapper<Car> {

  @Override
  public Car mapRow(ResultSet rs, int i) throws SQLException {
    Car car = new Car();

    car.setId(rs.getLong(ID));
    car.setModelId(rs.getInt(MODEL_ID));
    car.setVolume(rs.getDouble(VOLUME));
    car.setVinNumber(rs.getString(VIN_NUMBER));
    car.setPrice(rs.getDouble(PRICE));
    car.setCreationDate(rs.getTimestamp(CREATED));
    car.setModificationDate(rs.getTimestamp(CHANGED));
    car.setIsAvailable(rs.getBoolean(IS_AVAILABLE));

    return car;
  }
}
