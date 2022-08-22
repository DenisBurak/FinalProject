package com.zemelya.repository.jdbctemplate.car;

import com.zemelya.domain.Car;
import com.zemelya.repository.car.CarRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class CarRepository implements CarRepositoryInterface {

  private final JdbcTemplate jdbcTemplate;

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private final CarRowMapper carRowMapper;

  @Override
  public Car findById(Long id) {
    return jdbcTemplate.queryForObject(
        "select * from rentalcars.cars where id = " + id, carRowMapper);
  }

  @Override
  public Optional<Car> findOne(Long id) {
    return Optional.of(findById(id));
  }

  @Override
  public List<Car> findAll() {
    return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
  }

  @Override
  public List<Car> findAll(int limit, int offset) {
    return jdbcTemplate.query(
        "select * from rentalcars.cars limit " + limit + " offset " + offset, carRowMapper);
  }

  @Override
  public Car create(Car object) {

    final String insertQuery =
        "insert into rentalcars.cars "
            + "(model_id, "
            + "volume, "
            + "vin_number, "
            + "price, "
            + "is_available, "
            + "creation_date, "
            + "modification_date) "
            + " values ("
            + ":modelId, "
            + ":volume, "
            + ":vinNumber, "
            + ":price, "
            + ":isAvailable, "
            + ":creationDate, "
            + ":modificationDate);";

    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("modelId", object.getModelId());
    mapSqlParameterSource.addValue("volume", object.getVolume());
    mapSqlParameterSource.addValue("vinNumber", object.getVinNumber());
    mapSqlParameterSource.addValue("price", object.getPrice());
    mapSqlParameterSource.addValue("isAvailable", object.getIsAvailable());
    mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
    mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());

    namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

    Long lastInsertId =
        namedParameterJdbcTemplate.query(
            "SELECT currval('rentalcars.cars_id_seq') as last_id",
            resultSet -> {
              resultSet.next();
              return resultSet.getLong("last_id");
            });

    return findById(lastInsertId);
  }

  @Override
  public Car update(Car object) {

    final String updateQuery =
        "update rentalcars.cars "
            + "set "
            + "model_id = :modelId, "
            + "volume = :volume, "
            + "vin_number = :vinNumber, "
            + "price = :price, "
            + "is_deleted = :isDeleted, "
            + "creation_date = :creationDate, "
            + "modification_date = :modificationDate, "
            + "where id = :id";

    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("id", object.getId());
    mapSqlParameterSource.addValue("modelId", object.getModelId());
    mapSqlParameterSource.addValue("volume", object.getVolume());
    mapSqlParameterSource.addValue("vinNumber", object.getVinNumber());
    mapSqlParameterSource.addValue("price", object.getPrice());
    mapSqlParameterSource.addValue("isAvailable", object.getIsAvailable());
    mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
    mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());

    namedParameterJdbcTemplate.update(updateQuery, mapSqlParameterSource);

    return findById(object.getId());
  }

  @Override
  public Long delete(Long id) {
    jdbcTemplate.update("delete from rentalcars.cars where id = " + id);
    return id;
  }

  @Override
  public List<Map<String, Object>> getListOfCars() {

    final String query =
        "select "
            + "brand.brand_name AS brand, "
            + "model.model_name AS model, "
            + "body.body_type_name AS \"body type\" , "
            + "car.volume, "
            + "car.price "
            + "from rentalcars.cars AS car "
            + "inner join rentalcars.models AS model "
            + "           on car.model_id = model.id "
            + "inner join rentalcars.brands AS brand "
            + "           on model.brand_id = brand.id "
            + "inner join rentalcars.body_types AS body "
            + "            on model.body_type_id = body.id "
            + "where car.is_available "
            + "group by brand_name, model_name, body_type_name, volume, price;";

    return jdbcTemplate.queryForList(query);
  }

  public List<Map<String, Object>> getListOfAvailableCars() {
    final String query =
        "select "
            + "car.id,\n"
            + "brand.brand_name AS brand, "
            + "model.model_name AS model, "
            + "body.body_type_name AS \"body type\", "
            + "car.vin_number AS \"vin number\", "
            + "car.volume, "
            + "car.price "
            + "from rentalcars.cars AS car "
            + "inner join rentalcars.models AS model "
            + "           on car.model_id = model.id "
            + "inner join rentalcars.brands AS brand "
            + "           on model.brand_id = brand.id "
            + "inner join rentalcars.body_types AS body "
            + "            on model.body_type_id = body.id "
            + "left join rentalcars.rental_agreements AS ra "
            + "    on car.id = ra.car_id "
            + "where car.is_available "
            + "and (ra.car_id is null or ra.rental_start_date > current_date or ra.expiration_date < current_date)";

    return jdbcTemplate.queryForList(query);
  }
}
