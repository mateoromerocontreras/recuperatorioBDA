package ar.utn.edu.recuperatorio.controllers;

import ar.utn.edu.recuperatorio.entities.dtos.TrackDto;
import ar.utn.edu.recuperatorio.services.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public ResponseEntity<List<TrackDto>> getAllTracks() {
        try {
            List<TrackDto> tracks = trackService.getAll();
            return ResponseEntity.ok(tracks);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackDto> getTrackById(@PathVariable Long id) {
        try {
            TrackDto track = trackService.getById(id);
            return ResponseEntity.ok(track);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<TrackDto> createTrack(@RequestBody TrackDto trackDto) {
        try {
            TrackDto createdTrack = trackService.add(trackDto);
            return new ResponseEntity<>(createdTrack, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackDto> updateTrack(@PathVariable Long id, @RequestBody TrackDto trackDto) {
        try {
            TrackDto updatedTrack = trackService.update(id, trackDto);
            return ResponseEntity.ok(updatedTrack);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TrackDto> deleteTrack(@PathVariable Long id) {
        try {
            TrackDto deletedTrack = trackService.delete(id);
            return ResponseEntity.ok(deletedTrack);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
