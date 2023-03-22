package mk.ukim.finki.emt.lab.emt.lab.service;

import mk.ukim.finki.emt.lab.emt.lab.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> listAll();

    Country findById(Long id);

    Country create(String name, String continent);

    Country update(Long id, String name, String continent);

    void delete(Long id);
}
