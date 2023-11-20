package ar.utn.edu.recuperatorio.controllers;

import ar.utn.edu.recuperatorio.entities.dtos.ArtistDto;
import ar.utn.edu.recuperatorio.services.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<List<ArtistDto>> getAllArtists() {
        try {
            List<ArtistDto> artists = artistService.getAll();
            return ResponseEntity.ok(artists);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDto> getArtistById(@PathVariable Long id) {
        try {
            ArtistDto artist = artistService.getById(id);
            return ResponseEntity.ok(artist);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<ArtistDto> createArtist(@RequestBody ArtistDto artistDto) {
        try {
            ArtistDto createdArtist = artistService.add(artistDto);
            return new ResponseEntity<>(createdArtist, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistDto> updateArtist(@PathVariable Long id, @RequestBody ArtistDto artistDto) {
        try {
            ArtistDto updatedArtist = artistService.update(id, artistDto);
            return ResponseEntity.ok(updatedArtist);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArtistDto> deleteArtist(@PathVariable Long id) {
        try {
            ArtistDto deletedArtist = artistService.delete(id);
            return ResponseEntity.ok(deletedArtist);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
