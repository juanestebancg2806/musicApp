package app.springframework.musicApp.controllers;

import app.springframework.musicApp.repositories.SongRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SongController {
    private  final SongRepository songRepository;

    public SongController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @RequestMapping("/songs")
    public String getSongs(Model model){
        model.addAttribute("songs",songRepository.findAll());

        return "songs/index";
    }
}
