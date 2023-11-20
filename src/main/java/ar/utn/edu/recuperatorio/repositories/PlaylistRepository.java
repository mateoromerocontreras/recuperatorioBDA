package ar.utn.edu.recuperatorio.repositories;

import ar.utn.edu.recuperatorio.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
