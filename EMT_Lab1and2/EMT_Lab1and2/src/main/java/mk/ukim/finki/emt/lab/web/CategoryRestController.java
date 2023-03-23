package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.model.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    @GetMapping("/categories")
    public Category [] findAll (){
        return Category.values();
    }
}
