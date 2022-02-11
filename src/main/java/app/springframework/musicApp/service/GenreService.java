package app.springframework.musicApp.service;

import app.springframework.musicApp.domain.Country;
import app.springframework.musicApp.domain.Genre;
import app.springframework.musicApp.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getGenres(){
        List<Genre> genres = new ArrayList<>();
        this.genreRepository.findAll().forEach(genres::add);
        return  genres;
    }

    public List<String> getNames(){
        return  this.genreRepository.getNames();

    }

    public Genre add(Genre genre){
        return this.genreRepository.save(genre);
    }

    public void updateByName(String name,String newName){

        List<Genre> genres = this.genreRepository.findByName(name);
        if(genres.size() > 0){
            Genre g = genres.get(0);
            g.setName(newName);
            this.genreRepository.save(g);
            //System.out.println("Genre updated JSON body");
        }

    }

    public  Genre updateById(Genre genre,Long id){
        Optional<Genre> optionalGenre = this.genreRepository.findById(id);
        Genre oldGenre = null;
        if(optionalGenre.isPresent()){
            oldGenre = optionalGenre.get();
            oldGenre.setName(genre.getName());
            oldGenre = this.genreRepository.save(oldGenre);
            //System.out.println("Genre updated PUT");
        }

        return oldGenre;
    }
    public void deleteByName(String name){
        List<Genre> genres = this.genreRepository.findByName(name);
        if(genres.size() > 0){
            Genre g = genres.get(0);
            this.genreRepository.deleteById(g.getId());
            //System.out.println("Genre deleted JSON body");
        }

    }

    public void deleteById(Long id){
        this.genreRepository.deleteById(id);
    }


}
