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
import ru.otus.spring.hw2.domain.Book;
import ru.otus.spring.hw2.domain.Genre;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@JdbcTest
public class BookDaoJdbcTest {

    private static final String AUTHOR_1 = "Author1";
    private static final String AUTHOR_2 = "Author2";
    private static final String GENRE_1 = "Genre1";
    private static final String GENRE_2 = "Genre2";
    private static final String BOOK_1 = "Book1";
    private static final String BOOK_2 = "Book2";

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private BookDao bookDao;

    private int authorId1;
    private int authorId2;
    private int genreId1;
    private int genreId2;
    private int bookId;

    @Before
    public void setUp() {
        authorId1 = authorDao.insert(new Author(0, AUTHOR_1));
        authorId2 = authorDao.insert(new Author(0, AUTHOR_1));
        genreId1 = genreDao.insert(new Genre(0, GENRE_1));
        genreId2 = genreDao.insert(new Genre(0, GENRE_1));
        bookId = bookDao.insert(new Book(0, BOOK_1, authorId1, genreId1));
    }

    @Test
    public void insert() {
        Book expected = new Book(bookId, BOOK_1, authorId1, genreId1);
        Book result = bookDao.getById(bookId);
        assertEquals(expected, result);
    }

    @Test
    public void update() {
        Book book = new Book(bookId, BOOK_2, authorId2, genreId2);
        bookDao.update(book);
        Book result = bookDao.getById(bookId);
        assertEquals(book, result);
    }

    @Test
    public void updateName() {
        bookDao.updateName(bookId, BOOK_2);

        Book expected = new Book(bookId, BOOK_2, authorId1, genreId1);
        Book result = bookDao.getById(bookId);
        assertEquals(expected, result);
    }

    @Test
    public void updateAuthor() {
        bookDao.updateAuthor(bookId, authorId2);

        Book expected = new Book(bookId, BOOK_1, authorId2, genreId1);
        Book result = bookDao.getById(bookId);
        assertEquals(expected, result);
    }

    @Test
    public void updateGenre() {
        bookDao.updateGenre(bookId, genreId2);

        Book expected = new Book(bookId, BOOK_1, authorId1, genreId2);
        Book result = bookDao.getById(bookId);
        assertEquals(expected, result);
    }

    @Test
    public void deleteById() {
        Book book = new Book(bookId, BOOK_1, authorId1, genreId1);
        HashSet<Book> books = new HashSet<>(bookDao.getAll());
        assertTrue(books.contains(book));
        bookDao.deleteById(bookId);
        books = new HashSet<>(bookDao.getAll());
        assertFalse(books.contains(book));
    }

    @Test
    public void count() {
        assertTrue(bookDao.count() > 0);
    }

    @Test
    public void getAll() {
        assertFalse(bookDao.getAll().isEmpty());
    }
}
