package ru.otus.spring.hw2.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.hw2.domain.Genre;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookDaoJdbcTest {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private BookDao bookDao;

    @Before
    public void setUp() throws Exception {
        int genreId = genreDao.insert(new Genre(0, "Genre"));
    }

    @Test
    public void insert() {
    }

    @Test
    public void update() {
    }

    @Test
    public void updateName() {
    }

    @Test
    public void updateAuthor() {
    }

    @Test
    public void updateGenre() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void count() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void getAll() {
    }
}
