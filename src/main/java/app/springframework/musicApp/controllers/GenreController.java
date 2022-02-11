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
    @PostMapping(value = "/genres",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Genre createGenre(@RequestBody Genre genre){
        //System.out.println(country.getName()+" ----name");
        return this.genreService.add(genre);
    }


    @PutMapping(value="/genres/{id}",consumes = MediaType.APPLICATION_JSON_VALUE) //Falla con request param, el requestParam me pide mandar por url la info
    public Genre updateGenre(@RequestBody Genre genre,@PathVariable Long id){//json that contains 2 strings
        return this.genreService.updateById(genre,id);
    }


    @DeleteMapping(value = "/genres/{id}")
    public void deleteGenre(@PathVariable Long id){
        this.genreService.deleteById(id);
    }

}
