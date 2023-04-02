package mk.ukim.finki.emt.lab.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Category;

@Data
@NoArgsConstructor
public class BookDto {
    private String name;
    private Category category;
    private Long author;
    private Integer availableCopies;
    public BookDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
