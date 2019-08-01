package ru.otus.spring.hw2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.hw2.domain.Author;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedJdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedJdbc)
    {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public int insert(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbc.update("insert into authors (`name`) values (:name)",
                new MapSqlParameterSource("name", author.getName()),
                keyHolder);
        return (int) keyHolder.getKey();
    }

    @Override
    public boolean update(Author author) {
        return namedJdbc.update("update authors set name = :name where id = :id",
                ImmutableMap.of("id", author.getId(), "name", author.getName())) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        return namedJdbc.update("delete from authors where id = :id", ImmutableMap.of("id", id)) > 0;
    }

    @Override
    public int count() {
        return namedJdbc.queryForObject("select count(*) from authors", emptyMap(), Integer.class);
    }

    @Override
    public Author getById(int id) {
        return namedJdbc.queryForObject("select * from authors where id = :id", singletonMap("id", id), new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return namedJdbc.query("select * from authors", new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
