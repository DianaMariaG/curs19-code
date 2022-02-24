package ro.fasttrackit.curs19.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.fasttrackit.curs19.model.Country;

import java.util.List;

@Component
@ConditionalOnProperty(name = "source", havingValue = "memory")
public class MemoryCountryProvider implements CountryProvider {
    @Override
    public List<Country> getCountries() {
        return List.of(
                new Country(1, "Albania", "Tirana", 2886026, 28748, "Europe", List.of("MNE", "GRC", "MKD", "KOS")),
                new Country(2, "Burundi", "Bujumbura", 10114505, 27834, "Africa", List.of("COD", "RWA", "TZA")),
                new Country(3, "Albania2", "Tirana", 2886026, 28748, "Europe", List.of("MNE", "GRC", "MKD", "KOS")),
                new Country(4, "Albania3", "Tirana", 2886026, 28748, "Europe", List.of("MNE", "GRC", "MKD", "KOS"))
                );
    }

}
