package ru.otus.spring.hw2.domain;

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
}
