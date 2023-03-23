package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAll();

    Optional<Author> findById(Long id);

    Author create(Author authorDto);

    Author update(Long id, Author authorDto);

    void delete(Long id);
}
