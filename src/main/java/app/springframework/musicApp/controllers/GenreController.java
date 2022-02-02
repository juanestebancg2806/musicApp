package app.springframework.musicApp.controllers;


import app.springframework.musicApp.repositories.GenreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GenreController {
    private final GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    @RequestMapping("/genres")
    public String getGenres(Model model){
        model.addAttribute("genres",this.genreRepository.findAll());
        return  "genres/index";

    }
}
