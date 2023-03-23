package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Author create(Author authorDto) {
        return this.authorRepository.save(authorDto);
    }

    @Override
    public Author update(Long id,Author authorDto) {
        Optional<Author> author = findById(id);
        if(author.isPresent()){
        author.get().setName(authorDto.getName());
        author.get().setSurname(authorDto.getSurname());
        author.get().setCountry(authorDto.getCountry());
        return this.authorRepository.save(author.get());
        }
        return null;
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
