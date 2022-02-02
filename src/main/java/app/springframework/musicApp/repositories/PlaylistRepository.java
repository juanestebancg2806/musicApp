package app.springframework.musicApp.repositories;

import app.springframework.musicApp.domain.Playlist;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlist,Long> {

}
