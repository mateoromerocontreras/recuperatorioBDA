package ar.utn.edu.recuperatorio.repositories;

import ar.utn.edu.recuperatorio.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
}

