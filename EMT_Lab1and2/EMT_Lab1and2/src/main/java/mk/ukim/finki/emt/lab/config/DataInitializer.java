package mk.ukim.finki.emt.lab.config;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Book;
import mk.ukim.finki.emt.lab.model.Category;
import mk.ukim.finki.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.service.BookService;
import mk.ukim.finki.emt.lab.service.CountryService;
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
        Author author1=authorService.create(new Author("J.K. ","Rowling", countryService.create("UK","Europe")));
        Author author2=authorService.create(new Author("Stephen"," King",countryService.create("Canada","North America")));
        Author author3=authorService.create(new Author("Chimamanda Ngozi", "Adichie",countryService.create("Nigeria","Africa")));
        Author author4=authorService.create(new Author("Haruki","Murakami",countryService.create("Japan","Asia")));
        Author author5=authorService.create(new Author("Margaret"," Atwood",countryService.create("Canada","North America")));

        bookService.create(new Book("The Handmaid's Tale", Category.NOVEL,author5,20));
        bookService.create(new Book("The Shining", Category.THRILLER,author2,15));
        bookService.create(new Book("Americanah", Category.NOVEL,author3,10));
        bookService.create(new Book("Kafka on the Shore", Category.DRAMA,author4,5));
        bookService.create(new Book("Harry Potter", Category.BIOGRAPHY,author1,25));
        bookService.create(new Book("Cat's Eye", Category.FANTASY,author5,8));
        bookService.create(new Book("The Stand", Category.THRILLER,author2,12));
        bookService.create(new Book("Purple Hibiscus", Category.HISTORY,author3,6));
        bookService.create(new Book("Norwegian Wood", Category.NOVEL,author4,7));
        bookService.create(new Book("The Testaments", Category.CLASSICS,author5,18));

    }
}
