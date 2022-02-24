package ro.fasttrackit.curs19.service;

import ro.fasttrackit.curs19.model.Country;

import java.util.List;

public interface CountryProvider {
    List<Country> getCountries();
}
