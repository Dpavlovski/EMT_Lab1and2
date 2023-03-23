package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthorRestController {
    private final AuthorService authorService;


    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public List<Author> findAll () {
       return authorService.listAll();
    }

    @GetMapping("/authors/{id}")
    public Optional<Author> findAll (@PathVariable Long id) {
        return authorService.findById(id);
    }

    @PostMapping("/authors/add")
    public ResponseEntity<Author> create(@RequestBody Author authorDto) {
        return Optional.of(authorService.create(authorDto))
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/authors/edit/{id}")
    public ResponseEntity<Author> edit(@PathVariable Long id, @RequestBody Author authorDto) {
        return Optional.of(authorService.update(id,authorDto))
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/authors/delete/{id}")
    public ResponseEntity<Author> delete(@PathVariable Long id) {
        authorService.delete(id);
        if(authorService.findById(id).isPresent())
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
