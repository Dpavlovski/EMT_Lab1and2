package mk.ukim.finki.emt.lab.web.controllers;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Country;
import mk.ukim.finki.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorController {
    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorController(AuthorService authorService, CountryService countryService) {
        this.countryService = countryService;
        this.authorService = authorService;
    }

    /**
     * This method should use the "listBooks.html" template to display all entities.
     * The method should be mapped on paths '/' and '/authors'.
     * The arguments that this method takes are optional and can be 'null'.
     *
     * @return The view "listAuthors.html".
     */
    @GetMapping(value = {"/authors"})
    public String showList(Model model) {
        model.addAttribute("authors", authorService.listAll());
        model.addAttribute("bodyContent","listAuthors");
        return "master-template";
    }

    /**
     * This method should display the "AuthorForm.html" template.
     * The method should be mapped on path '/authors/add'.
     *
     * @return The view "AuthorForm.html".
     */
    @GetMapping("/authors/add")
    public String showAdd(Model model) {
        model.addAttribute("countries",countryService.listAll());
        model.addAttribute("bodyContent","AuthorForm");
        return "master-template";
    }

    /**
     * This method should display the "AuthorForm.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the entity that is updated.
     * The method should be mapped on path '/authors/[id]/edit'.
     *
     * @return The view "AuthorForm.html".
     */
    @GetMapping("/authors/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        model.addAttribute("countries", countryService.listAll());
        model.addAttribute("bodyContent","AuthorForm");
        return "master-template";
    }

    /**
     * This method should create an entity given the arguments it takes.
     * The method should be mapped on path '/authors'.
     * After the entity is created, the list of entities should be displayed.
     *
     * @return The view "listAuthors.html".
     */
    @PostMapping("/authors")
    public String create(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam Long countryId){
        Country country=countryService.findById(countryId);
        this.authorService.create(new Author(name,surname,country));
        return "redirect:/authors";
    }

    /**
     * This method should update an entity given the arguments it takes.
     * The method should be mapped on path '/authors/[id]'.
     * After the entity is updated, the list of entities should be displayed.
     *
     * @return The view "listAuthors.html".
     */
    @PostMapping("/authors/{id}")
    public String update(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam Long countryId){
        Country country=countryService.findById(countryId);
        this.authorService.update(id,new Author(name,surname,country));
        return "redirect:/authors";
    }

    /**
     * This method should delete the entities that has the appropriate identifier.
     * The method should be mapped on path '/authors/[id]/delete'.
     * After the entity is deleted, the list of entities should be displayed.
     *
     * @return The view "listAuthors.html".
     */
    @PostMapping("/authors/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.authorService.delete(id);
        return "redirect:/authors";
    }

}
