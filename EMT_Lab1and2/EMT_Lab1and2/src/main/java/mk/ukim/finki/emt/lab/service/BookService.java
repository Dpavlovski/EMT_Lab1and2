package mk.ukim.finki.emt.lab.emt.lab.service;

import mk.ukim.finki.emt.lab.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.emt.lab.model.Category;

import java.util.List;

public interface BookService {
    List<Book> listAll();

    Book findById(Long id);

    Book create(String name, Category category, Author author, Integer availableCopies);

    Book update(Long id, String name, Category category, Author author, Integer availableCopies);

    void delete(Long id);

    void borrowBook(Long id);
}
