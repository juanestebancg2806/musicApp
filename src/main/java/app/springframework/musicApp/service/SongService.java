package app.springframework.musicApp.service;


import app.springframework.musicApp.domain.Author;
import app.springframework.musicApp.domain.Country;
import app.springframework.musicApp.domain.Genre;
import app.springframework.musicApp.domain.Song;
import app.springframework.musicApp.repositories.AuthorRepository;
import app.springframework.musicApp.repositories.GenreRepository;
import app.springframework.musicApp.repositories.SongRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public SongService(SongRepository songRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.songRepository = songRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    public List<Song> getSongs(){
        List<Song> songs = new ArrayList<>();
        this.songRepository.findAll().forEach(songs::add);
        return  songs;
    }

    public List <Map<String,String>> getSongsInfo(){
        List  <Map<String,String>> jsons = new ArrayList< Map<String,String> >();
        this.songRepository.findAll().forEach(s ->{
            Map<String,String> map = new HashMap<>();
            map.put("Title",s.getName());
            map.put("Duration",Integer.toString(s.getDuration()));
            map.put("Author",s.getAuthor() == null ? "Null author": s.getAuthor().getNames()+" "+s.getAuthor().getLastnames());
            map.put("Genre",s.getGenre() == null? "Null genre": s.getGenre().getName());
            jsons.add(map);

        });

        return jsons;
        //return  this.authorRepository.getAuthorsInfo().stream().map(a -> a.getNames()+"-"+a.getLastnames()+"-"+(a.getCountry().getName().length() > 0 ?a.getCountry().getName(): "null Country") ).collect(Collectors.toList());
    }

    public void add(String name,int duration,String authorName,String genreName){
        Song song = new Song(name,duration);
        List<Author> authors = this.authorRepository.findByName(authorName);
        if(authors.size() > 0){
            Author a = authors.get(0);
            song.setAuthor(a);
        }
        List<Genre> genres = this.genreRepository.findByName(genreName);
        if(genres.size() > 0){
            Genre g = genres.get(0);
            song.setGenre(g);
        }

        this.songRepository.save(song);
        System.out.println("Song added JSON body");

    }

    public void deleteByName(String name){
        List<Song> songs = this.songRepository.findByName(name);
        if(songs.size() > 0){
            Song s = songs.get(0);
            this.songRepository.deleteById(s.getId());
            System.out.println("Song deleted JSON body");
        }

    }

    public void updateByName(String name,String newName,int newDuration,String newAuthorName, String newGenreName){
        List<Song> songs = this.songRepository.findByName(name);
        if(songs.size() > 0){
            Song s = songs.get(0);
            s.setName(newName);
            s.setDuration(newDuration);
            List<Author> authors = this.authorRepository.findByName(newAuthorName);
            if(authors.size() > 0){
                Author a = authors.get(0);
                s.setAuthor(a);

            }
            List<Genre> genres = this.genreRepository.findByName(newGenreName);
            if(genres.size() > 0){
                Genre g = genres.get(0);
                s.setGenre(g);
            }

            this.songRepository.save(s);
            System.out.println("Song updated JSON body");
        }
    }




}
