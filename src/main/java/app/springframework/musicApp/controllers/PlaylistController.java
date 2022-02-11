package app.springframework.musicApp.controllers;

import app.springframework.musicApp.domain.User;
import app.springframework.musicApp.service.PlaylistService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/playlists")
    public List <Map<String,String>> getPlaylists() {
        return this.playlistService.getPlaylists();

    }
    @PostMapping(value = "/playlists/{idUser}-{idSong}")
    public void addSong(@PathVariable Long idUser,@PathVariable long idSong) {
        this.playlistService.addSongByIds(idUser,idSong);


    }

    @DeleteMapping(value = "/playlists/{idUser}-{idSong}")
    public void deleteSong(@PathVariable Long idUser,@PathVariable long idSong){
            this.playlistService.deleteSongByIds(idUser,idSong);
    }



/*
    @PostMapping(value = "/playlists/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSong(@RequestBody Map<String,String> json) {
        this.playlistService.deleteSong(json.get("userNames"),json.get("songName"));

    }*/

}
