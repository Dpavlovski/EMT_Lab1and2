package mk.ukim.finki.emt.lab.config;

import mk.ukim.finki.emt.lab.dto.AuthorDto;
import mk.ukim.finki.emt.lab.dto.BookDto;
import mk.ukim.finki.emt.lab.enums.Role;
import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.enums.Category;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.service.BookService;
import mk.ukim.finki.emt.lab.service.CountryService;
import mk.ukim.finki.emt.lab.service.UserService;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final BookService bookService;

    private final AuthorService authorService;
    private final CountryService countryService;

    private final UserService userService;

    public DataInitializer(BookService bookService, AuthorService authorService, CountryService countryService, UserService userService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
        this.userService = userService;
    }


    @PostConstruct
    public void initData() {
        Author author1=authorService.create(new AuthorDto("J.K. ","Rowling", countryService.create("UK","Europe").getId())).orElseThrow(InvalidAuthorIdException::new);
        Author author2=authorService.create(new AuthorDto("Stephen"," King",countryService.create("Canada","North America").getId())).orElseThrow(InvalidAuthorIdException::new);
        Author author3=authorService.create(new AuthorDto("Chimamanda Ngozi", "Adichie",countryService.create("Nigeria","Africa").getId())).orElseThrow(InvalidAuthorIdException::new);
        Author author4=authorService.create(new AuthorDto("Haruki","Murakami",countryService.create("Japan","Asia").getId())).orElseThrow(InvalidAuthorIdException::new);
        Author author5=authorService.create(new AuthorDto("Margaret"," Atwood",countryService.create("Canada","North America").getId())).orElseThrow(InvalidAuthorIdException::new);

        bookService.create(new BookDto("The Handmaid's Tale", Category.NOVEL,author5.getId(),20));
        bookService.create(new BookDto("The Shining", Category.THRILLER,author2.getId(),15));
        bookService.create(new BookDto("Americanah", Category.NOVEL,author3.getId(),10));
        bookService.create(new BookDto("Kafka on the Shore", Category.DRAMA,author4.getId(),5));
        bookService.create(new BookDto("Harry Potter", Category.BIOGRAPHY,author1.getId(),25));
        bookService.create(new BookDto("Cat's Eye", Category.FANTASY,author5.getId(),8));
        bookService.create(new BookDto("The Stand", Category.THRILLER,author2.getId(),12));
        bookService.create(new BookDto("Purple Hibiscus", Category.HISTORY,author3.getId(),6));
        bookService.create(new BookDto("Norwegian Wood", Category.NOVEL,author4.getId(),7));
        bookService.create(new BookDto("The Testaments", Category.CLASSICS,author5.getId(),18));

        userService.register("librarian","admin","admin","Admin","Admin",Role.ROLE_LIBRARIAN);
        userService.register("user","user","user","User","User", Role.ROLE_USER);
    }
}
