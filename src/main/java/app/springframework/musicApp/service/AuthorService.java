package app.springframework.musicApp.service;

import app.springframework.musicApp.domain.Author;
import app.springframework.musicApp.domain.Country;
import app.springframework.musicApp.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

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

    public void add(Author author){
        this.authorRepository.save(author);
    }
    public void deleteByName(String name){
        List<Author> authors = this.authorRepository.findByName(name);
        if(authors.size() > 0){
            Author a = authors.get(0);
            this.authorRepository.deleteById(a.getId());
            System.out.println("Author deleted JSON body");
        }

    }

    public void updateByName(Map<String,String> json){
        List<Author> authors = this.authorRepository.findByName(json.get("names"));
        if(authors.size() > 0){
            Author a = authors.get(0);
            a.setNames(json.get("newNames"));
            a.setLastnames(json.get("lastnames"));
            this.authorRepository.save(a);
            System.out.println("Author updated JSON body");
        }
    }

}
