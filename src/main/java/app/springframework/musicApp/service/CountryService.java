package app.springframework.musicApp.service;

import app.springframework.musicApp.domain.Country;
import app.springframework.musicApp.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final  CountryRepository countryRepository;


    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<String> getNames(){
        return  this.countryRepository.getNames();

    }
    public List<Country> getCountries(){
        List<Country> countries = new ArrayList<>();
        this.countryRepository.findAll().forEach(countries::add);
        return  countries;

    }

    public Country add(Country country){
        return this.countryRepository.save(country);
    }



    public void updateByName(String name,String newName){
        List<Country> countries = this.countryRepository.findByName(name);
        if(countries.size() > 0){
            Country c = countries.get(0);
            c.setName(newName);
            this.countryRepository.save(c);
            System.out.println("Country updated JSON body");
        }

    }
    public void deleteByName(String name){
        List<Country> countries = this.countryRepository.findByName(name);
        if(countries.size() > 0){
            Country c = countries.get(0);
            this.countryRepository.deleteById(c.getId());
            System.out.println("Country deleted JSON body");
        }

    }

    public void deleteById(Long id){
        this.countryRepository.deleteById(id);
    }

    public Country updateById(Country country,Long id){
        Optional<Country> c = this.countryRepository.findById(id);
        Country oldCountry = null;
        if(!c.isEmpty()){
            oldCountry = c.get();
            oldCountry.setName(country.getName());
            oldCountry = this.countryRepository.save(oldCountry);
            System.out.println("Country updated PUT");

        }
        return oldCountry;
    }




}
