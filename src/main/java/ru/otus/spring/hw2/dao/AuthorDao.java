package ru.otus.spring.hw2.dao;

import java.util.List;

import ru.otus.spring.hw2.domain.Author;

public interface AuthorDao {

    int insert(Author author);

    boolean update(Author author);

    boolean deleteById(int id);

    int count();

    Author getById(int id);

    List<Author> getAll();
}
