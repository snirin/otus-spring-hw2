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
import ru.otus.spring.hw2.domain.Book;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedJdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations namedJdbc) {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public int insert(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbc.update("insert into books (`name`, `author_id`, `genre_id`) values (:name, :authorId, :genreId)",
                new MapSqlParameterSource()
                        .addValue("name", book.getName())
                        .addValue("authorId", book.getAuthorId())
                        .addValue("genreId", book.getGenreId()),
                keyHolder);
        return (int) keyHolder.getKey();
    }

    @Override
    public boolean update(Book book) {
        return namedJdbc.update("update books set name = :name, author_id = :authorId, genre_id = :genreId where id = :id",
                ImmutableMap.of(
                        "id", book.getId(),
                        "name", book.getName(),
                        "authorId", book.getAuthorId(),
                        "genreId", book.getGenreId())
        ) > 0;
    }

    @Override
    public boolean updateName(Book book) {
        return namedJdbc.update("update books set name = :name where id = :id",
                ImmutableMap.of("id", book.getId(), "name", book.getName())) > 0;
    }

    @Override
    public boolean updateAuthor(Book book) {
        return namedJdbc.update("update books set author_id = :authorId where id = :id",
                ImmutableMap.of("id", book.getId(), "authorId", book.getAuthorId())) > 0;
    }

    @Override
    public boolean updateGenre(Book book) {
        return namedJdbc.update("update books set genre_id = :genreId where id = :id",
                ImmutableMap.of("id", book.getId(), "genreId", book.getGenreId())) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        return namedJdbc.update("delete from books where id = :id", ImmutableMap.of("id", id)) > 0;
    }

    @Override
    public int count() {
        return namedJdbc.queryForObject("select count(*) from books", emptyMap(), Integer.class);
    }

    @Override
    public Book getById(int id) {
        return namedJdbc.queryForObject("select * from books where id = :id", singletonMap("id", id), new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return namedJdbc.query("select * from books", new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int authorId = resultSet.getInt("author_id");
            int genreId = resultSet.getInt("genre_id");
            return new Book(id, name, authorId, genreId);
        }
    }
}
