package app.springframework.musicApp.controllers;

import app.springframework.musicApp.domain.User;
import app.springframework.musicApp.service.PlaylistService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping(value = "/playlists/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addSong(@RequestBody Map<String,String> json) {
        this.playlistService.addSong(json.get("userNames"),json.get("songName"));
    }

    @PostMapping(value = "/playlists/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSong(@RequestBody Map<String,String> json) {
        this.playlistService.deleteSong(json.get("userNames"),json.get("songName"));
    }
}
