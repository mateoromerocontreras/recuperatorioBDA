package ar.utn.edu.recuperatorio.repositories;

import ar.utn.edu.recuperatorio.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
