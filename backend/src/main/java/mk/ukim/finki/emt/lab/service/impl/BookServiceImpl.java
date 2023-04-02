package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.dto.BookDto;
import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidBookIdException;
import mk.ukim.finki.emt.lab.repository.AuthorRepository;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        Author author=authorRepository.findById(bookDto.getAuthor()).orElseThrow(InvalidAuthorIdException::new);
        Book book=new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(InvalidBookIdException::new);
        book.setName(bookDto.getName());
        book.setAuthor(authorRepository.findById(bookDto.getAuthor()).orElseThrow(InvalidAuthorIdException::new));
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void delete(Long id) {
        Book book =bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        bookRepository.delete(book);
    }
    @Override
    public Optional<Book> borrowBook(Long id) {
        Book book=bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        if(book.getAvailableCopies()>0)
            book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Page<Book> listAllWithPagination(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
