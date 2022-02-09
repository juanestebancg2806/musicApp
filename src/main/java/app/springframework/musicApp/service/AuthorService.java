package app.springframework.musicApp.service;

import app.springframework.musicApp.domain.Author;
import app.springframework.musicApp.domain.Country;
import app.springframework.musicApp.repositories.AuthorRepository;
import app.springframework.musicApp.repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository; //new

    public AuthorService(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }
    //public AuthorService(AuthorRepository authorRepository) {
   //     this.authorRepository = authorRepository;
    //}

    public List<Author> getAuthors(){
        List<Author> authors = new ArrayList<>();
        this.authorRepository.findAll().forEach(authors::add);
        return  authors;
    }
    public List <Map<String,String> > getAuthorsInfo(){
        List  <Map<String,String>> jsons = new ArrayList< Map<String,String> >();

        this.authorRepository.getAuthorsInfo().stream().forEach(a ->{
            Map<String,String> map = new HashMap<>();
            map.put("Names",a.getNames());
            map.put("Lastnames",a.getLastnames());
            map.put("Country",a.getCountry() == null ? "null country": (a.getCountry().getName().length() > 0 ?a.getCountry().getName(): "null Country"));
            map.put("Songs",a.getSongs().stream().map(s -> s.getName()).collect(Collectors.toList()).toString());
            jsons.add(map);
        });

        return jsons;
        //return  this.authorRepository.getAuthorsInfo().stream().map(a -> a.getNames()+"-"+a.getLastnames()+"-"+(a.getCountry().getName().length() > 0 ?a.getCountry().getName(): "null Country") ).collect(Collectors.toList());
    }

    public void add(String names,String lastnames,String countryName){
        Author author = new Author(names,lastnames);
        List<Country> countries = this.countryRepository.findByName(countryName);
        if(countries.size() > 0){
            Country c = countries.get(0);
            author.setCountry(c);

        }
        this.authorRepository.save(author);
        System.out.println("Author added JSON body");

    }
    public void deleteByName(String name){
        List<Author> authors = this.authorRepository.findByName(name);
        if(authors.size() > 0){
            Author a = authors.get(0);
            this.authorRepository.deleteById(a.getId());
            System.out.println("Author deleted JSON body");
        }

    }


    public void updateByName(String names,String newNames,String newLastnames,String countryName){
        List<Author> authors = this.authorRepository.findByName(names);
        if(authors.size() > 0){
            Author a = authors.get(0);
            a.setNames(newNames);
            a.setLastnames(newLastnames);
            List<Country> countries = this.countryRepository.findByName(countryName);
            if(countries.size() > 0){
                Country c = countries.get(0);
                a.setCountry(c);

            }
            //a.setCountry();
            this.authorRepository.save(a);
            System.out.println("Author updated JSON body");
        }
    }

}
