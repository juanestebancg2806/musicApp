package app.springframework.musicApp.controllers;

import app.springframework.musicApp.domain.Country;
import app.springframework.musicApp.domain.Song;
import app.springframework.musicApp.repositories.SongRepository;
import app.springframework.musicApp.service.SongService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SongController {
    private  final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/songs")
    public List<Song> getSongs(){
        //model.addAttribute("songs",songRepository.findAll());
        return this.songService.getSongs();
    }

    @GetMapping("/songs/info")
    public List <Map<String,String>> getSongsInfo(){
        return this.songService.getSongsInfo();
        //return this.authorService.getAuthorsInfo();

    }

    @PostMapping(value = "/songs/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createSong(@RequestBody Map<String,String> json){
        this.songService.add(json.get("name"),Integer.parseInt(json.get("duration")),json.get("authorName"),json.get("genreName"));
    }


    @PostMapping(value = "/songs/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSong(@RequestBody Map<String,String> json){
        this.songService.deleteByName(json.get("name"));
    }

    @PostMapping(value="/songs/update",consumes = MediaType.APPLICATION_JSON_VALUE) //Falla con request param, el requestParam me pide mandar por url la info
    public void updateSong(@RequestBody Map<String,String> json){
        this.songService.updateByName(json.get("name"),json.get("newName"),Integer.parseInt(json.get("newDuration")),json.get("newAuthorName"),json.get("newGenreName"));
    }

}
