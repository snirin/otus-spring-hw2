package ru.otus.spring.hw2.dao;

import java.util.List;

import ru.otus.spring.hw2.domain.Genre;

public interface GenreDao {

    int insert(Genre genre);

    boolean update(Genre genre);

    boolean deleteById(int id);

    int count();

    Genre getById(int id);

    List<Genre> getAll();
}
