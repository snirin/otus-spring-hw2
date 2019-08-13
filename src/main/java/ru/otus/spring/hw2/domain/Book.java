package ru.otus.spring.hw2.domain;

import java.util.Objects;

public class Book {

    private final int id;
    private final String name;
    private final int authorId;
    private final int genreId;

    public Book(int id, String name, int authorId, int genreId) {
        this.id = id;
        this.name = name;
        this.authorId = authorId;
        this.genreId = genreId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getGenreId() {
        return genreId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorId=" + authorId +
                ", genreId=" + genreId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return id == book.id &&
                authorId == book.authorId &&
                genreId == book.genreId &&
                name.equals(book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authorId, genreId);
    }
}
