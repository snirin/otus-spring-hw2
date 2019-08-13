package ru.otus.spring.hw2.dao;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.hw2.domain.Author;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@JdbcTest
public class AuthorDaoJdbcTest {

    private static final String AUTHOR_1 = "Author1";
    private static final String AUTHOR_2 = "Author2";

    @Autowired
    private AuthorDao authorDao;

    private int authorId1;

    @Before
    public void setUp() {
        authorId1 = authorDao.insert(new Author(0, AUTHOR_1));
    }

    @Test
    public void insert() {
        Author expected = new Author(authorId1, AUTHOR_1);
        Author result = authorDao.getById(authorId1);
        assertEquals(expected, result);
    }

    @Test
    public void update() {
        Author author = new Author(authorId1, AUTHOR_2);
        authorDao.update(author);
        Author result = authorDao.getById(authorId1);
        assertEquals(author, result);
    }

    @Test
    public void deleteById() {
        Author author = new Author(authorId1, AUTHOR_1);
        HashSet<Author> authors = new HashSet<>(authorDao.getAll());
        assertTrue(authors.contains(author));
        authorDao.deleteById(authorId1);
        authors = new HashSet<>(authorDao.getAll());
        assertFalse(authors.contains(author));
    }

    @Test
    public void count() {
        assertTrue(authorDao.count() > 0);
    }

    @Test
    public void getAll() {
        assertFalse(authorDao.getAll().isEmpty());
    }
}
