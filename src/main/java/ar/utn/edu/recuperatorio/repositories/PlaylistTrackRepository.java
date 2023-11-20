package ar.utn.edu.recuperatorio.repositories;

import ar.utn.edu.recuperatorio.entities.PlaylistTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrack, Long> {
    List<PlaylistTrack> findPlaylistTracksByPlaylistId(Long id);
}
