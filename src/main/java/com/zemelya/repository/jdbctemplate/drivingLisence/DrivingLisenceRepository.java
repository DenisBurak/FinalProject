package com.zemelya.repository.jdbctemplate.drivingLisence;

import com.zemelya.repository.drivingLisences.DrivingLisenceRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class DrivingLisenceRepository implements DrivingLisenceRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final DrivingLisenceRowMapper drivingLisenceRowMapper;

    @Override
    public DrivingLisence findById(Long id) {
        return jdbcTemplate.queryForObject("select * from rentalcars.driving_lisences where id = " + id, drivingLisenceRowMapper);
    }

    @Override
    public Optional<DrivingLisence> findOne(Long id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<DrivingLisence> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List<DrivingLisence> findAll(int limit, int offset) {
        return jdbcTemplate.query("select * from rentalcars.driving_lisences limit " + limit + " offset " + offset, drivingLisenceRowMapper);
    }

    @Override
    public DrivingLisence create(DrivingLisence object) {

        final String insertQuery =
                "insert into rentalcars.driving_lisences " +
                        "(serial_number, " +
                        "date_of_issue, " +
                        "expiration_date, " +
                        "category, " +
                        "is_deleted, " +
                        "creation_date, " +
                        "modification_date, " +
                        "user_id) " +
                        " values (" +
                        ":serialNumber, " +
                        ":dateOfIssue, " +
                        ":expirationDate, " +
                        ":category, " +
                        ":isDeleted, " +
                        ":creationDate, " +
                        ":modificationDate, " +
                        ":userId);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("serialNumber", object.getSerialNumber());
        mapSqlParameterSource.addValue("dateOfIssue", object.getDateOfIssue());
        mapSqlParameterSource.addValue("expirationDate", object.getExpirationDate());
        mapSqlParameterSource.addValue("category", object.getCategory());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());
        mapSqlParameterSource.addValue("userId", object.getUserId());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Long lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('rentalcars.driving_lisences_id_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getLong("last_id");
                });

        return findById(lastInsertId);
    }

    @Override
    public DrivingLisence update(DrivingLisence object) {

        final String updateQuery =
                "update rentalcars.driving_lisences " +
                        "set " +
                        "serial_number = :serialNumber, " +
                        "date_of_issue =  :dateOfIssue, " +
                        "expiration_date = :expirationDate, " +
                        "category = :category, " +
                        "is_deleted = :isDeleted, " +
                        "creation_date = :creationDate, " +
                        "modification_date = :modificationDate, " +
                        "user_id = :userId " +
                        "where id = :id";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", object.getId());
        mapSqlParameterSource.addValue("serialNumber", object.getSerialNumber());
        mapSqlParameterSource.addValue("dateOfIssue", object.getDateOfIssue());
        mapSqlParameterSource.addValue("expirationDate", object.getExpirationDate());
        mapSqlParameterSource.addValue("category", object.getCategory());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());
        mapSqlParameterSource.addValue("userId", object.getUserId());

        namedParameterJdbcTemplate.update(updateQuery, mapSqlParameterSource);

        return findById(object.getId());

    }

    @Override
    public Long delete(Long id) {
        jdbcTemplate.update("delete from rentalcars.driving_lisences where id = " + id);
        return id;
    }
}
