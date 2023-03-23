package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();

    Optional<Book> findById(Long id);

    Book create(Book book);

    Book update(Long id, Book book);

    void delete(Long id);

    Optional<Book> borrowBook(Long id);

    Page<Book> listAllWithPagination (Pageable pageable);
}
