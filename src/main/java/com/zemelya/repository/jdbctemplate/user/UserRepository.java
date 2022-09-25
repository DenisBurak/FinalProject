package com.zemelya.repository.jdbctemplate.user;

import com.zemelya.repository.user.UserRepositoryInterface;
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
public class UserRepository implements UserRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final UserRowMapper userRowMapper;

    @Override
    public User findById(Integer id) {
        return jdbcTemplate.queryForObject("select * from rentalcars.users where id = " + id, userRowMapper);
    }

    @Override
    public Optional<User> findOne(Integer id) {
        return Optional.of(findById(id));
    }

    @Override
    public List<User> findAll() {
        return findAll(DEFAULT_FIND_ALL_LIMIT, DEFAULT_FIND_ALL_OFFSET);
    }

    @Override
    public List<User> findAll(int limit, int offset) {
        return jdbcTemplate.query("select * from rentalcars.users limit " + limit + " offset " + offset, userRowMapper);
    }

    @Override
    public User create(User object) {

        final String insertQuery =
                "insert into rentalcars.users " +
                        "(user_name, " +
                        "surname, " +
                        "birth, " +
                        "email, " +
                        "is_deleted, " +
                        "creation_date, " +
                        "modification_date, " +
                        "role_id, " +
                        "login, " +
                        "password) " +
                        " values (" +
                        ":userName, " +
                        ":surname, " +
                        ":birth, " +
                        ":email, " +
                        ":isDeleted, " +
                        ":creationDate, " +
                        ":modificationDate, " +
                        ":roleId," +
                        ":login, " +
                        ":password);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("userName", object.getUserName());
        mapSqlParameterSource.addValue("surname", object.getSurname());
        mapSqlParameterSource.addValue("birth", object.getBirth());
        mapSqlParameterSource.addValue("email", object.getEmail());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());
        mapSqlParameterSource.addValue("roleId", object.getRoleId());
        mapSqlParameterSource.addValue("login", object.getLogin());
        mapSqlParameterSource.addValue("password", object.getPassword());

        namedParameterJdbcTemplate.update(insertQuery, mapSqlParameterSource);

        Integer lastInsertId = namedParameterJdbcTemplate.query("SELECT currval('rentalcars.users_id_seq') as last_id",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getInt("last_id");
                });

        return findById(lastInsertId);
    }

    @Override
    public User update(User object) {

        final String updateQuery =
                "update rentalcars.users " +
                        "set " +
                        "user_name = :userName, " +
                        "surname =  :surname, " +
                        "birth = :birth, " +
                        "email = :email, " +
                        "is_deleted = :isDeleted, " +
                        "creation_date = :creationDate, " +
                        "modification_date = :modificationDate, " +
                        "role_id = :role_id, " +
                        "login = :login, " +
                        "password = :password " +
                        "where id = :id";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", object.getId());
        mapSqlParameterSource.addValue("userName", object.getUserName());
        mapSqlParameterSource.addValue("surname", object.getSurname());
        mapSqlParameterSource.addValue("birth", object.getBirth());
        mapSqlParameterSource.addValue("email", object.getEmail());
        mapSqlParameterSource.addValue("isDeleted", object.getIsDeleted());
        mapSqlParameterSource.addValue("creationDate", object.getCreationDate());
        mapSqlParameterSource.addValue("modificationDate", object.getModificationDate());
        mapSqlParameterSource.addValue("role_id", object.getRoleId());
        mapSqlParameterSource.addValue("login", object.getLogin());
        mapSqlParameterSource.addValue("password", object.getPassword());

        namedParameterJdbcTemplate.update(updateQuery, mapSqlParameterSource);

        return findById(object.getId());

    }

    @Override
    public Integer delete(Integer id) {
        jdbcTemplate.update("delete from rentalcars.users where id = " + id);
        return id;
    }
}
