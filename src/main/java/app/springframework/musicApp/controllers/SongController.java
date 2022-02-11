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

        return this.songService.getSongs();
    }

    @GetMapping("/songs/info")
    public List <Map<String,String>> getSongsInfo(){
        return this.songService.getSongsInfo();


    }

    @PostMapping(value = "/songs",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Song createSong(@RequestBody Map<String,String> json){
        return this.songService.add(json.get("name"),Integer.parseInt(json.get("duration")),json.get("authorName"),json.get("genreName"));
    }

    @PutMapping(value="/songs/{id}",consumes = MediaType.APPLICATION_JSON_VALUE) //Falla con request param, el requestParam me pide mandar por url la info
    public Song updateSong(@RequestBody Song song,@PathVariable Long id){

        return this.songService.updateById(song,id);
    }
    @DeleteMapping(value = "/songs/{id}")
    public void deleteSong(@PathVariable Long id){

        this.songService.deleteById(id);
    }



}
