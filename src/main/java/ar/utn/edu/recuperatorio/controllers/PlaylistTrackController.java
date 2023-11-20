package ar.utn.edu.recuperatorio.controllers;

import ar.utn.edu.recuperatorio.entities.dtos.PlaylistTrackDto;
import ar.utn.edu.recuperatorio.services.PlaylistTrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/playlist-tracks")
public class PlaylistTrackController {

    private final PlaylistTrackService playlistTrackService;

    public PlaylistTrackController(PlaylistTrackService playlistTrackService) {
        this.playlistTrackService = playlistTrackService;
    }

    @GetMapping
    public ResponseEntity<List<PlaylistTrackDto>> getAllPlaylistTracks() {
        try {
            List<PlaylistTrackDto> playlistTracks = playlistTrackService.getAll();
            return ResponseEntity.ok(playlistTracks);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistTrackDto> getPlaylistTrackById(@PathVariable Long id) {
        try {
            PlaylistTrackDto playlistTrack = playlistTrackService.getById(id);
            return ResponseEntity.ok(playlistTrack);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<PlaylistTrackDto> createPlaylistTrack(@RequestBody PlaylistTrackDto playlistTrackDto) {
        try {
            PlaylistTrackDto createdPlaylistTrack = playlistTrackService.add(playlistTrackDto);
            return new ResponseEntity<>(createdPlaylistTrack, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlaylistTrackDto> deletePlaylistTrack(@PathVariable Long id) {
        try {
            PlaylistTrackDto deletedPlaylistTrack = playlistTrackService.delete(id);
            return ResponseEntity.ok(deletedPlaylistTrack);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/playlist/{id}")
    public ResponseEntity<List<PlaylistTrackDto>> getAllPlaylistTracksByPlaylist(@PathVariable Long id) {
        try {
            List<PlaylistTrackDto> playlistTracks = playlistTrackService.obtenerPorPlaylistId(id);
            return ResponseEntity.ok(playlistTracks);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
