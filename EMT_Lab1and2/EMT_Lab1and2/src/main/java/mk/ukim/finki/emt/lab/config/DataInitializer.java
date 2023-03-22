package mk.ukim.finki.emt.lab.emt.lab.config;

import mk.ukim.finki.emt.lab.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.emt.lab.model.Category;
import mk.ukim.finki.emt.lab.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.emt.lab.service.BookService;
import mk.ukim.finki.emt.lab.emt.lab.service.CountryService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final BookService bookService;

    private final AuthorService authorService;
    private final CountryService countryService;

    public DataInitializer(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }


    @PostConstruct
    public void initData() {
        Author author1=authorService.create("J.K. ","Rowling", countryService.create("UK","Europe"));
        Author author2=authorService.create("Stephen"," King",countryService.create("Canada","North America"));
        Author author3=authorService.create("Chimamanda Ngozi", "Adichie",countryService.create("Nigeria","Africa"));
        Author author4=authorService.create("Haruki","Murakami",countryService.create("Japan","Asia"));
        Author author5=authorService.create("Margaret"," Atwood",countryService.create("Canada","North America"));

        bookService.create("The Handmaid's Tale", Category.NOVEL,author5,20);
        bookService.create("The Shining", Category.THRILLER,author2,15);
        bookService.create("Americanah", Category.NOVEL,author3,10);
        bookService.create("Kafka on the Shore", Category.DRAMA,author4,5);
        bookService.create("Harry Potter", Category.BIOGRAPHY,author1,25);
        bookService.create("Cat's Eye", Category.FANTASY,author5,8);
        bookService.create("The Stand", Category.THRILLER,author2,12);
        bookService.create("Purple Hibiscus", Category.HISTORY,author3,6);
        bookService.create("Norwegian Wood", Category.NOVEL,author4,7);
        bookService.create("The Testaments", Category.CLASSICS,author5,18);

    }
}
