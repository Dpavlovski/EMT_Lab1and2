package mk.ukim.finki.emt.lab.emt.lab.service;

import mk.ukim.finki.emt.lab.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.emt.lab.model.Country;

import java.util.List;

public interface AuthorService {
    List<Author> listAll();

    Author findById(Long id);

    Author create(String name, String surname, Country country);

    Author update(Long id, String name, String surname, Country country);

    void delete(Long id);
}
