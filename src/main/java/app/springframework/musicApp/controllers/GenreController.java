package app.springframework.musicApp.controllers;


import app.springframework.musicApp.domain.Country;
import app.springframework.musicApp.domain.Genre;
import app.springframework.musicApp.repositories.GenreRepository;
import app.springframework.musicApp.service.GenreService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
    @GetMapping("/genres")
    public List<Genre> getGenres(){
        //model.addAttribute("genres",this.genreRepository.findAll());
        return this.genreService.getGenres();

    }
    @GetMapping("/genres/names")
    public List<String> getGenresNames(){
        //model.addAttribute("countries",this.countryRepository.findAll());
        return  this.genreService.getNames();

    }
    @PostMapping(value = "/genres/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGenre(@RequestBody Genre genre){
        //System.out.println(country.getName()+" ----name");
        this.genreService.add(genre);
    }
    @PostMapping(value="/genres/update",consumes = MediaType.APPLICATION_JSON_VALUE) //Falla con request param, el requestParam me pide mandar por url la info
    public void updateGenreJson(@RequestBody Map<String,String> json){//json that contains 2 strings
        this.genreService.updateByName(json.get("name"),json.get("newName"));
    }

    @PostMapping(value = "/genres/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteGenre(@RequestBody Map<String,String> json){
        this.genreService.deleteByName(json.get("name"));
    }

}
