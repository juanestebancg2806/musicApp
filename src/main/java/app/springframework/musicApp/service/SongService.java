package app.springframework.musicApp.service;


import app.springframework.musicApp.domain.Author;
import app.springframework.musicApp.domain.Country;
import app.springframework.musicApp.domain.Genre;
import app.springframework.musicApp.domain.Song;
import app.springframework.musicApp.repositories.AuthorRepository;
import app.springframework.musicApp.repositories.GenreRepository;
import app.springframework.musicApp.repositories.SongRepository;
import org.springframework.stereotype.Service;

import java.util.*;
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

    public Song add(String name,int duration,String authorName,String genreName){
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



        return this.songRepository.save(song);

    }

    public void deleteByName(String name){//si intento borrar uno que este en USER_SONG lanza excepcion, revisar cascade types
        List<Song> songs = this.songRepository.findByName(name);
        if(songs.size() > 0){
            Song s = songs.get(0);
            s.getUsers().stream().forEach(u -> u.getSongs().remove(s)); //se debe borrar la cancion de los usuarios que la tenian
            this.songRepository.deleteById(s.getId());
            //System.out.println("Song deleted JSON body");
        }

    }

    public void deleteById(Long id){
        Optional<Song> optionalSong = this.songRepository.findById(id);
        if(optionalSong.isPresent()){
            Song song = optionalSong.get();
            song.getUsers().stream().forEach(u -> u.getSongs().remove(song));
            this.songRepository.deleteById(id);
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

    public Song updateById(Song song,Long id){
        Optional<Song> optionalSong = this.songRepository.findById(id);
        Song oldSong = null;
        if(optionalSong.isPresent()){
            oldSong = optionalSong.get();
            oldSong.setName(song.getName());
            oldSong.setDuration(song.getDuration());
            List<Author> authors = this.authorRepository.findByName(song.getAuthor().getNames());
            if(authors.size() > 0){
                Author a = authors.get(0);
                oldSong.setAuthor(a);

            }
            List<Genre> genres = this.genreRepository.findByName(song.getGenre().getName());
            if(genres.size() > 0){
                Genre g = genres.get(0);
                oldSong.setGenre(g);
            }

            oldSong = this.songRepository.save(oldSong);
        }
        return oldSong;

    }




}
