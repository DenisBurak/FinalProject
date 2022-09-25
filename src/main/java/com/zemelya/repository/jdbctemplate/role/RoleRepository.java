package com.zemelya.repository.jdbctemplate.role;

import com.zemelya.repository.role.RoleRepositoryInterface;
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
public class RoleRepository implements RoleRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RoleRowMapper roleRowMapper;

    @Override
    public Role findById(Integer id) {
        return jdbcTemplate.queryForObject("select * from rentalcars.roles where id = " + id, roleRowMapper);
    }

    @Override
    public Optional<Role> findOne(Integer id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<Role> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List<Role> findAll(int limit, int offset) {
        return jdbcTemplate.query("select * from rentalcars.roles limit " + limit + " offset " + offset, roleRowMapper);
    }

    @Override
    public Role create(Role object) {

        final String insertQuery =
                "insert into rentalcars.roles " +
                        "(role_name, " +
                        "is_deleted, " +
                        "creation_date, " +
                        "modification_date) " +
                        " values (" +
                        ":roleName, " +
                        ":isDeleted, " +
                        ":creationDate, " +
                        ":modificationDate);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("roleName", object.getRoleName());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Integer lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('rentalcars.roles_id_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getInt("last_id");
                });

        return findById(lastInsertId);
    }

    @Override
    public Role update(Role object) {

        final String updateQuery =
                "update rentalcars.roles " +
                        "set " +
                        "role_name = :roleName, " +
                        "is_deleted = :isDeleted, " +
                        "creation_date = :creationDate, " +
                        "modification_date = :modificationDate, " +
                        "where id = :id";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", object.getId());
        mapSqlParameterSource.addValue("roleName", object.getRoleName());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());

        namedParameterJdbcTemplate.update(updateQuery, mapSqlParameterSource);

        return findById(object.getId());

    }

    @Override
    public Integer delete(Integer id) {
        jdbcTemplate.update("delete from rentalcars.roles where id = " + id);
        return id;
    }
}
