package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.dto.AuthorDto;
import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.repository.CountryRepository;
import mk.ukim.finki.emt.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository,
                             CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
    }
    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> create(AuthorDto authorDto) {
        Country country=countryRepository.findById(authorDto.getCountry()).orElseThrow(InvalidCountryIdException::new);
        Author author=new Author(authorDto.getName(),authorDto.getSurname(),country);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public Optional<Author> update(Long id,AuthorDto authorDto) {
        Author author= authorRepository.findById(id)
                .orElseThrow(InvalidAuthorIdException::new);
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        Country country=countryRepository.findById(authorDto.getCountry()).orElseThrow(InvalidCountryIdException::new);
        author.setCountry(country);
        authorRepository.save(author);
        return  Optional.of(author);
    }

    @Override
    public void delete(Long id) {
        Optional<Author> author = findById(id);
        if(author.isPresent()){
            List<Book> books=bookRepository.findAllBooksByAuthorId(id);
            bookRepository.deleteAll(books);
            this.authorRepository.delete(author.get());
        }
    }

}
