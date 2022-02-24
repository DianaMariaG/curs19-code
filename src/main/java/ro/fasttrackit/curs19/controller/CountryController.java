package ro.fasttrackit.curs19.controller;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.curs19.exceptions.ResourceNotFoundException;
import ro.fasttrackit.curs19.model.Country;
import ro.fasttrackit.curs19.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("countries")
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }
    @GetMapping
    List<Country> test() {
        return service.getAll();
    }

    @PostMapping
    Country addCountry(@RequestBody Country country) { //acest obiect trb citit din corpul requestului http
        return service.add(country);
    }

    @PutMapping("{id}")
    Country replaceCountry(@PathVariable int id, @RequestBody Country country) {
        return service.replace(id, country)
                .orElseThrow(()-> new ResourceNotFoundException("Can't find country"));
    }

    @DeleteMapping("{id}")
    Country deleteCountry(@PathVariable int id) {
        return service.delete(id)
                .orElse(null);
    }
}
//...........controller - interfata cu HTTP
//...........service - application logic (business logic)
//...........repository - data storage
//...........model - data objects
