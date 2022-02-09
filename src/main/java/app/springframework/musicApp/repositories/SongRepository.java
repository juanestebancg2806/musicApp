package app.springframework.musicApp.repositories;

import app.springframework.musicApp.domain.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song,Long> {
    List<Song> findByName(String name);

}
