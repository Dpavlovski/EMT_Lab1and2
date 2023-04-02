package mk.ukim.finki.emt.lab.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.lab.model.Country;

@Data
@NoArgsConstructor
public class AuthorDto {
    private String name;
    private String surname;
    private Long country;

    public AuthorDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
