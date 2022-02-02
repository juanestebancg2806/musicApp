package app.springframework.musicApp.repositories;

import app.springframework.musicApp.domain.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song,Long> {
}
