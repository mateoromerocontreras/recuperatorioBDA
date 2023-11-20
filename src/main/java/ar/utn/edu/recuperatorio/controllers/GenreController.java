package ar.utn.edu.recuperatorio.controllers;

import ar.utn.edu.recuperatorio.entities.dtos.GenreDto;
import ar.utn.edu.recuperatorio.services.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAllGenres() {
        try {
            List<GenreDto> genres = genreService.getAll();
            return ResponseEntity.ok(genres);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Long id) {
        try {
            GenreDto genre = genreService.getById(id);
            return ResponseEntity.ok(genre);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<GenreDto> createGenre(@RequestBody GenreDto genreDto) {
        try {
            GenreDto createdGenre = genreService.add(genreDto);
            return new ResponseEntity<>(createdGenre, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable Long id, @RequestBody GenreDto genreDto) {
        try {
            GenreDto updatedGenre = genreService.update(id, genreDto);
            return ResponseEntity.ok(updatedGenre);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenreDto> deleteGenre(@PathVariable Long id) {
        try {
            GenreDto deletedGenre = genreService.delete(id);
            return ResponseEntity.ok(deletedGenre);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
