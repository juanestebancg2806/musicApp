package app.springframework.musicApp.service;

import app.springframework.musicApp.domain.Song;
import app.springframework.musicApp.domain.User;
import app.springframework.musicApp.repositories.SongRepository;
import app.springframework.musicApp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlaylistService {
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public PlaylistService(UserRepository userRepository, SongRepository songRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public  List <Map<String,String>> getPlaylists(){
        List  <Map<String,String>> jsons = new ArrayList< Map<String,String> >();
        this.userRepository.findAll().forEach(u ->{
            Map<String,String> map = new HashMap<>();
            map.put("Email",u.getEmail());
            map.put("Songs",u.getSongs().stream().map(s -> s.getName()).collect(Collectors.toList()).toString());
            if(map.get("Songs").length() > 0) //if user have at least one song
                jsons.add(map);

        });
        return jsons;
    }

    public void addSong(String userNames,String songName){
        List<User> users = this.userRepository.findByNames(userNames);
        List<Song> songs = this.songRepository.findByName(songName);
        if(users.size() > 0 && songs.size() > 0){
            User u = users.get(0);
            Song s = songs.get(0);
            u.getSongs().add(s);
            s.getUsers().add(u);
            this.userRepository.save(u);
            this.songRepository.save(s);
            //System.out.println("Added song JSON body");
        }
    }

    public void addSongByIds(Long idUser,Long idSong){
        Optional<User> userOptional = this.userRepository.findById(idUser);
        Optional<Song> songOptional = this.songRepository.findById(idSong);
        if(userOptional.isPresent() && songOptional.isPresent()){
            User user = userOptional.get();
            Song song = songOptional.get();
            user.getSongs().add(song);
            song.getUsers().add(user);
            this.userRepository.save(user);
            this.songRepository.save(song);
            //System.out.println("Added song PUT");
        }

    }

    public void deleteSong(String userNames,String songName){
        List<User> users = this.userRepository.findByNames(userNames);
        List<Song> songs = this.songRepository.findByName(songName);
        if(users.size() > 0 && songs.size() > 0){
            User u = users.get(0);
            Song s = songs.get(0);
            u.getSongs().remove(s);
            s.getUsers().remove(u);
            this.userRepository.save(u);
            this.songRepository.save(s);
            //System.out.println("Deleted song JSON body");
        }

    }

    public void deleteSongByIds(Long idUser,Long idSong){
        Optional<User> userOptional = this.userRepository.findById(idUser);
        Optional<Song> songOptional = this.songRepository.findById(idSong);
        if(userOptional.isPresent() && songOptional.isPresent()){
            User user = userOptional.get();
            Song song = songOptional.get();
            user.getSongs().remove(song);
            song.getUsers().remove(user);
            this.userRepository.save(user);
            this.songRepository.save(song);

        }

    }
}
