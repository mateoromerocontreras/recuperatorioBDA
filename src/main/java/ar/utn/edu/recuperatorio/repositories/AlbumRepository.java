package ar.utn.edu.recuperatorio.repositories;

import ar.utn.edu.recuperatorio.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    //@Query("SELECT a FROM Album a WHERE a.artist = :a")
    //List<Album> findByArtist(Artist a);
    List<Album> findByTitle(String title);
}
