package ru.otus.spring.hw2.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.hw2.dao.AuthorDao;
import ru.otus.spring.hw2.dao.BookDao;
import ru.otus.spring.hw2.dao.GenreDao;
import ru.otus.spring.hw2.domain.Author;
import ru.otus.spring.hw2.domain.Book;
import ru.otus.spring.hw2.domain.Genre;

@ShellComponent
public class ShellCommands {

    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final BookDao bookDao;

    public ShellCommands(AuthorDao authorDao, GenreDao genreDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.bookDao = bookDao;
    }

    /*
    AUTHORS
     */
    @ShellMethod(value = "Author:Insert", key = {"ai"})
    public int authorInsert(@ShellOption String name) {
        return authorDao.insert(new Author(0, name));
    }

    @ShellMethod(value = "Author:Update", key = {"au"})
    public boolean authorUpdate(@ShellOption int id, @ShellOption String name) {
        return authorDao.update(new Author(id, name));
    }

    @ShellMethod(value = "Author:Delete", key = {"ad"})
    public boolean authorDelete(@ShellOption int id) {
        return authorDao.deleteById(id);
    }

    @ShellMethod(value = "Author:Get", key = {"ag"})
    public String authorGet(@ShellOption int id) {
        return authorDao.getById(id).toString();
    }

    @ShellMethod(value = "Author:GetAll", key = {"aga"})
    public String authorGet() {
        return authorDao.getAll().toString();
    }

    @ShellMethod(value = "Author:Count", key = {"ac"})
    public int authorCount() {
        return authorDao.count();
    }
    /*
    AUTHORS - END
     */

    /*
    GENRES
     */
    @ShellMethod(value = "Genre:Insert", key = {"gi"})
    public int genreInsert(@ShellOption String name) {
        return genreDao.insert(new Genre(0, name));
    }

    @ShellMethod(value = "Genre:Update", key = {"gu"})
    public boolean genreUpdate(@ShellOption int id, @ShellOption String name) {
        return genreDao.update(new Genre(id, name));
    }

    @ShellMethod(value = "Genre:Delete", key = {"gd"})
    public boolean genreDelete(@ShellOption int id) {
        return genreDao.deleteById(id);
    }

    @ShellMethod(value = "Genre:Get", key = {"gg"})
    public String genreGet(@ShellOption int id) {
        return genreDao.getById(id).toString();
    }

    @ShellMethod(value = "Genre:GetAll", key = {"gga"})
    public String genreGet() {
        return genreDao.getAll().toString();
    }

    @ShellMethod(value = "Genre:Count", key = {"gc"})
    public int genreCount() {
        return genreDao.count();
    }
    /*
    GENRES - END
     */


    /*
    BOOKS
     */
    @ShellMethod(value = "Book:Insert", key = {"bi"})
    public int bookInsert(@ShellOption String name, @ShellOption int authorId, @ShellOption int genreId) {
        return bookDao.insert(new Book(0, name, authorId, genreId));
    }

    @ShellMethod(value = "Book:Update", key = {"bu"})
    public boolean bookUpdate(@ShellOption int id, @ShellOption String name, @ShellOption int authorId, @ShellOption int genreId) {
        return bookDao.update(new Book(id, name, authorId, genreId));
    }

    @ShellMethod(value = "Book:UpdateName", key = {"bun"})
    public boolean bookUpdateName(@ShellOption int id, @ShellOption String name) {
        return bookDao.updateName(id, name);
    }

    @ShellMethod(value = "Book:UpdateAuthor", key = {"bua"})
    public boolean bookUpdateAuthor(@ShellOption int id, @ShellOption int authorId) {
        return bookDao.updateAuthor(id, authorId);
    }

    @ShellMethod(value = "Book:UpdateGenre", key = {"bug"})
    public boolean bookUpdateGenre(@ShellOption int id, @ShellOption int genreId) {
        return bookDao.updateGenre(id, genreId);
    }

    @ShellMethod(value = "Book:Delete", key = {"bd"})
    public boolean bookDelete(@ShellOption int id) {
        return bookDao.deleteById(id);
    }

    @ShellMethod(value = "Book:Get", key = {"bg"})
    public String bookGet(@ShellOption int id) {
        return bookDao.getById(id).toString();
    }

    @ShellMethod(value = "Book:GetAll", key = {"bga"})
    public String bookGet() {
        return bookDao.getAll().toString();
    }

    @ShellMethod(value = "Book:Count", key = {"bc"})
    public int bookCount() {
        return bookDao.count();
    }
    /*
    BOOKS - END
     */
}
