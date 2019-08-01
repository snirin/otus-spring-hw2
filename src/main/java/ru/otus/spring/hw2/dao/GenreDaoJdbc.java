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
import ru.otus.spring.hw2.domain.Genre;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations namedJdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedJdbc)
    {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public int insert(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbc.update("insert into genres (`name`) values (:name)",
                new MapSqlParameterSource("name", genre.getName()),
                keyHolder);
        return (int) keyHolder.getKey();
    }

    @Override
    public boolean update(Genre genre) {
        return namedJdbc.update("update genres set name = :name where id = :id",
                ImmutableMap.of("id", genre.getId(), "name", genre.getName())) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        return namedJdbc.update("delete from genres where id = :id", ImmutableMap.of("id", id)) > 0;
    }

    @Override
    public int count() {
        return namedJdbc.queryForObject("select count(*) from genres", emptyMap(), Integer.class);
    }

    @Override
    public Genre getById(int id) {
        return namedJdbc.queryForObject("select * from genres where id = :id", singletonMap("id", id), new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return namedJdbc.query("select * from genres", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
