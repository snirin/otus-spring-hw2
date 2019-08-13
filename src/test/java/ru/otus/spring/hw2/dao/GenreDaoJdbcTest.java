package ru.otus.spring.hw2.dao;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.hw2.domain.Genre;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@JdbcTest
public class GenreDaoJdbcTest {

    private static final String GENRE_1 = "Genre1";
    private static final String GENRE_2 = "Genre2";

    @Autowired
    private GenreDao genreDao;

    private int genreId1;

    @Before
    public void setUp() {
        genreId1 = genreDao.insert(new Genre(0, GENRE_1));
    }

    @Test
    public void insert() {
        Genre expected = new Genre(genreId1, GENRE_1);
        Genre result = genreDao.getById(genreId1);
        assertEquals(expected, result);
    }

    @Test
    public void update() {
        Genre genre = new Genre(genreId1, GENRE_2);
        genreDao.update(genre);
        Genre result = genreDao.getById(genreId1);
        assertEquals(genre, result);
    }

    @Test
    public void deleteById() {
        Genre genre = new Genre(genreId1, GENRE_1);
        HashSet<Genre> genres = new HashSet<>(genreDao.getAll());
        assertTrue(genres.contains(genre));
        genreDao.deleteById(genreId1);
        genres = new HashSet<>(genreDao.getAll());
        assertFalse(genres.contains(genre));
    }

    @Test
    public void count() {
        assertTrue(genreDao.count() > 0);
    }

    @Test
    public void getAll() {
        assertFalse(genreDao.getAll().isEmpty());
    }
}
