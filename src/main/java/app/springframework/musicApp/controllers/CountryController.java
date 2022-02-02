package app.springframework.musicApp.controllers;

import app.springframework.musicApp.repositories.CountryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CountryController {
    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @RequestMapping("/countries")
    public String getCountries(Model model){
        model.addAttribute("countries",this.countryRepository.findAll());
        return "countries/index";

    }
}
