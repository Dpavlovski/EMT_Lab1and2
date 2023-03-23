package mk.ukim.finki.emt.lab.web.controllers;

import mk.ukim.finki.emt.lab.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    @GetMapping("/categories")
    public String showList (Model model) {
        model.addAttribute("categories", Category.values());
        model.addAttribute("bodyContent","listCategories");
        return "master-template";
    }
}
