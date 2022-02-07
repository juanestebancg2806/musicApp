package app.springframework.musicApp.controllers;

import app.springframework.musicApp.domain.Author;
import app.springframework.musicApp.domain.Country;
import app.springframework.musicApp.repositories.AuthorRepository;
import app.springframework.musicApp.service.AuthorService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController //Si cambia si funciona
public class AuthorController {

    private final AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping("/authors")
    public List<Author> getAuthors(){
        return this.authorService.getAuthors();

    }
    @GetMapping("/authors/info")
    public List <Map<String,String> > getAuthorsInfo(){
        return this.authorService.getAuthorsInfo();

    }

    @PostMapping(value = "/authors/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCountry(@RequestBody Author author){
        this.authorService.add(author);
    }
    @PostMapping(value = "/authors/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCountry(@RequestBody Map<String,String> json){
        this.authorService.deleteByName(json.get("names"));
    }

}
