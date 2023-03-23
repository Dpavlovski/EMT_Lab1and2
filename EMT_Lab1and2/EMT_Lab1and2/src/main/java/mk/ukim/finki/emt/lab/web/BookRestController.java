package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = {"/", "/books"})
    public List<Book> findAll() {
        return bookService.listAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/books/add")
    public ResponseEntity<Book> create(@RequestBody Book bookDto) {
        return Optional.of(bookService.create(bookDto))
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/books/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody Book bookDto) {
        return Optional.of(bookService.update(id,bookDto))
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/books/delete/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {
         bookService.delete(id);
         if(bookService.findById(id).isPresent())
             return ResponseEntity.ok().build();
         return ResponseEntity.notFound().build();
    }

   @PutMapping("/books/borrow/{id}")
    public ResponseEntity<Book> borrow(@PathVariable Long id) {
        return bookService.borrowBook(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/books/pagination")
    public List<Book> findAllWithPagination(Pageable pageable) {
        return bookService.listAllWithPagination(pageable).getContent();
    }
}