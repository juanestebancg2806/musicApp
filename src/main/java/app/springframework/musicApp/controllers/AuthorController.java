package app.springframework.musicApp.controllers;

import app.springframework.musicApp.domain.Author;
import app.springframework.musicApp.domain.Country;
import app.springframework.musicApp.repositories.AuthorRepository;
import app.springframework.musicApp.repositories.SongRepository;
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

    @PostMapping(value = "/authors",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Author createAuthor(@RequestBody Map<String,String> json){
        return this.authorService.add(json.get("names"),json.get("lastnames"),json.get("countryName"));
    }


    @GetMapping("/authors/general-information")
    public List <Map<String,String> > getAuthorsInfo(){
        return this.authorService.getAuthorsInfo();

    }

    @PatchMapping(value = "/authors/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Author updateAuthor(@RequestBody Map<String, String> json,@PathVariable Long id){
        return this.authorService.updateById(json.get("newNames"),json.get("newLastnames"),json.get("countryName"),id);

    }
    @DeleteMapping(value = "/authors/{id}")
    public void deleteAuthor(@PathVariable Long id){
        this.authorService.deleteById(id);
    }
    /*
    @PostMapping(value = "/authors/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateAuthor(@RequestBody Map<String,String> json){
        this.authorService.updateByName(json.get("names"),json.get("newNames"),json.get("newLastnames"),json.get("countryName"));
    }
    @PostMapping(value = "/authors/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAuthor(@RequestBody Map<String,String> json){
        this.authorService.deleteByName(json.get("names"));
    }
*/

}
