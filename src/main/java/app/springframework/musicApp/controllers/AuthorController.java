package app.springframework.musicApp.controllers;

import app.springframework.musicApp.domain.Author;
import app.springframework.musicApp.repositories.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController //Si cambia si funciona
public class AuthorController {

    private final AuthorRepository authorRepository;


    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @GetMapping("/authors")
    public List<Author> getAuthors(Model model){
        //model.addAttribute("authors",authorRepository.findAll());
        //return "authors/index";
        List<Author> authors = new ArrayList<>();//authorRepository.findAll();
        authorRepository.findAll().forEach(authors::add);
        return authors;

    }

}
