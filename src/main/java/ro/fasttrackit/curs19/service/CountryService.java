package ro.fasttrackit.curs19.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs19.model.Country;


import org.springframework.stereotype.Service;
import ro.fasttrackit.curs19.model.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final List<Country> countries;

    public CountryService(
            /* @Qualifier("emptyCountryProvider")*/ CountryProvider memoryCountryProvider) {
        this.countries = new ArrayList<>(memoryCountryProvider.getCountries());
    }

    public List<Country> getAll() {
        return countries;
    }

    public Country add(Country country) {
        Country newCountry = cloneWithId(this.countries.size(), country);
        this.countries.add(newCountry);
        return newCountry;
    }

    public Optional<Country> replace(int id, Country country) {
        return findById(id)
                .map(existing -> replaceExistingCountry(id, existing, country));
    }

    private Country replaceExistingCountry(int id, Country existing, Country country) {
        this.countries.remove(existing);
        Country newCountry = cloneWithId(id, country);
        this.countries.add(id,newCountry);
        return newCountry;
    }

    public Optional<Country> findById(int id) {
        return this.countries.stream()
                .filter(country -> country.id() == id)
                .findFirst();
    }

    private Country cloneWithId(int id, Country country) {
        return new Country(
                id,
                country.name(),
                country.capital(),
                country.population(),
                country.area(),
                country.continent(),
                country.neighbours()
        );
    }

    public Optional<Country> delete(int id) {
        Optional<Country> countryToDelete = findById(id);
        countryToDelete.ifPresent(o -> this.countries.remove(o));
        return countryToDelete;

    }
}