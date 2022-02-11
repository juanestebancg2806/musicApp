package app.springframework.musicApp.controllers;

import app.springframework.musicApp.domain.Country;
import app.springframework.musicApp.repositories.CountryRepository;
import app.springframework.musicApp.service.CountryService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public List<Country> getCountries(){

        return  this.countryService.getCountries();
    }

    @GetMapping("/countries/names")
    public List<String> getCountriesNames(){
        //model.addAttribute("countries",this.countryRepository.findAll());
        return  this.countryService.getNames();

    }

    @PostMapping(value = "/countries",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Country createCountry(@RequestBody Country country){
        return this.countryService.add(country);
    }



    @PutMapping(value="/countries/{id}",consumes = MediaType.APPLICATION_JSON_VALUE) //Falla con request param, el requestParam me pide mandar por url la info
    public Country updateCountry(@RequestBody Country country,@PathVariable Long id){//json that contains 2 strings
        //this.countryService.updateByName(json.get("name"),json.get("newName"));
        return this.countryService.updateById(country,id);
    }

    @DeleteMapping(value = "/countries/{id}")
    public void deleteCountry(@PathVariable Long id){
        this.countryService.deleteById(id);
    }
/*
    @PutMapping(value="/countries/update2/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCountryPUT(@RequestBody Country country, @PathVariable Long id){ //PathVariable is a Spring annotation which indicates that a method parameter should be bound to a URI template variable
        this.countryService.updateById(country,id);

    }
*/

}
