package mk.ukim.finki.emt.lab.web.controllers;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.Category;
import mk.ukim.finki.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    /**
     * This method should use the "listBooks.html" template to display all entities.
     * The method should be mapped on paths '/' and '/books'.
     * The arguments that this method takes are optional and can be 'null'.
     *
     * @return The view "listBooks.html".
     */
    @GetMapping(value = {"/", "/books"})
    public String showList(Model model) {
        model.addAttribute("books",bookService.listAll());
        model.addAttribute("bodyContent","listBooks");
        return "master-template";
    }

    /**
     * This method should display the "bookForm.html" template.
     * The method should be mapped on path '/books/add'.
     *
     * @return The view "bookForm.html".
     */
    @GetMapping("/books/add")
    public String showAdd(Model model) {
        model.addAttribute("categories", Category.values());
        model.addAttribute("authors",authorService.listAll());
        model.addAttribute("bodyContent","bookForm");
        return "master-template";
    }

    /**
     * This method should display the "bookForm.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the entity that is updated.
     * The method should be mapped on path '/books/[id]/edit'.
     *
     * @return The view "bookForm.html".
     */
    @GetMapping("/books/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("book",bookService.findById(id));
        model.addAttribute("categories", Category.values());
        model.addAttribute("authors", authorService.listAll());
        model.addAttribute("bodyContent","bookForm");
        return "master-template";
    }

    /**
     * This method should create an entity given the arguments it takes.
     * The method should be mapped on path '/books'.
     * After the entity is created, the list of entities should be displayed.
     *
     * @return The view "listBooks.html".
     */
    @PostMapping("/books")
    public String create(
            @RequestParam String name,
            @RequestParam Category category,
            @RequestParam Long authorId,
            @RequestParam Integer availableCopies){
        Optional<Author> author =authorService.findById(authorId);
        author.ifPresent(value -> this.bookService.create(new Book(name, category, value, availableCopies)));
        return "redirect:/books";
    }

    /**
     * This method should update an entity given the arguments it takes.
     * The method should be mapped on path '/books/[id]'.
     * After the entity is updated, the list of entities should be displayed.
     *
     * @return The view "listBooks.html".
     */
    @PostMapping("/books/{id}")
    public String update(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam Category category,
            @RequestParam Long authorId,
            @RequestParam Integer availableCopies){
        Optional<Author> author =authorService.findById(authorId);
        author.ifPresent(value -> this.bookService.update(id,new Book(name, category, value, availableCopies)));
        return "redirect:/books";
    }

    /**
     * This method should delete the entities that has the appropriate identifier.
     * The method should be mapped on path '/books/[id]/delete'.
     * After the entity is deleted, the list of entities should be displayed.
     *
     * @return The view "listBooks.html".
     */
    @PostMapping("/books/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.bookService.delete(id);
        return "redirect:/books";
    }

    /**
     * This method should decrement the number of availableCopies of the entities that has the appropriate identifier.
     * The method should be mapped on path '/books/[id]/borrow'.
     * After the entity is edited, the list of entities should be displayed.
     *
     * @return The view "listBooks.html".
     */
    @PostMapping("/books/{id}/borrow")
    public String borrowBook(@PathVariable Long id) {
        this.bookService.borrowBook(id);
        return "redirect:/books";
    }
}
