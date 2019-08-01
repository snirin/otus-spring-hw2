package ru.otus.spring.hw2.dao;

import java.util.List;

import ru.otus.spring.hw2.domain.Book;

public interface BookDao {

    int insert(Book book);

    boolean update(Book book);

    boolean updateName(Book book);

    boolean updateAuthor(Book book);

    boolean updateGenre(Book book);

    boolean deleteById(int id);

    int count();

    Book getById(int id);

    List<Book> getAll();
}
