package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.dto.AuthorDto;
import mk.ukim.finki.emt.lab.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAll();

    Optional<Author> findById(Long id);

    Optional<Author> create(AuthorDto authorDto);

    Optional<Author> update(Long id, AuthorDto authorDto);

    void delete(Long id);
}
