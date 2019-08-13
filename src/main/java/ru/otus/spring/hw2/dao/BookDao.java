package ru.otus.spring.hw2.dao;

import java.util.List;

import ru.otus.spring.hw2.domain.Book;

public interface BookDao {

    int insert(Book book);

    boolean update(Book book);

    boolean updateName(int id, String name);

    boolean updateAuthor(int id, int authorId);

    boolean updateGenre(int id, int genreId);

    boolean deleteById(int id);

    int count();

    Book getById(int id);

    List<Book> getAll();
}
