package mk.ukim.finki.emt.lab.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.emt.lab.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.emt.lab.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.emt.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(InvalidAuthorIdException::new);
    }

    @Override
    public Author create(String name, String surname, Country country) {
        return this.authorRepository.save(new Author(name, surname, country));
    }

    @Override
    public Author update(Long id, String name, String surname, Country country) {
        Author author = findById(id);
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return this.authorRepository.save(author);
    }

    @Override
    public void delete(Long id) {
        Author author = findById(id);
        List<Book> books=bookRepository.findAllBooksByAuthorId(id);
        bookRepository.deleteAll(books);
        this.authorRepository.delete(author);
    }

}
