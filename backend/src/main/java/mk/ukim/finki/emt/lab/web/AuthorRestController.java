package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.dto.AuthorDto;
import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;


    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public List<Author> findAll () {
       return authorService.listAll();
    }

    @GetMapping("/{id}")
    public Optional<Author> findAll (@PathVariable Long id) {
        return authorService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Author> create(@RequestBody AuthorDto authorDto) {
        return authorService.create(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> edit(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        return authorService.update(id,authorDto)
                .map(author-> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> delete(@PathVariable Long id) {
        authorService.delete(id);
        if(authorService.findById(id).isPresent())
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
