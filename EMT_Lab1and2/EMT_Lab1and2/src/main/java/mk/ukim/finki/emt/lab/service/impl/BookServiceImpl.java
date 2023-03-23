package mk.ukim.finki.emt.lab.service.impl;

import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidBookIdException;
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

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
    public Book create(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public Book update(Long id, Book bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(InvalidBookIdException::new);
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        Optional<Book> book = findById(id);
        book.ifPresent(this.bookRepository::delete);
    }
    @Override
    public Optional<Book> borrowBook(Long id) {
        Optional<Book> book = findById(id);
        if(book.isPresent()){
            if(book.get().getAvailableCopies()==0) return Optional.empty();
            book.get().setAvailableCopies(book.get().getAvailableCopies()-1);
            bookRepository.save(book.get());
        }
        return book;
    }

    @Override
    public Page<Book> listAllWithPagination(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
