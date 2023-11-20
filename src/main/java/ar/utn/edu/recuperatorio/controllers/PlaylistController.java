package ar.utn.edu.recuperatorio.controllers;

import ar.utn.edu.recuperatorio.entities.dtos.PlaylistDto;
import ar.utn.edu.recuperatorio.services.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<List<PlaylistDto>> getAllPlaylists() {
        try {
            List<PlaylistDto> playlists = playlistService.getAll();
            return ResponseEntity.ok(playlists);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDto> getPlaylistById(@PathVariable Long id) {
        try {
            PlaylistDto playlist = playlistService.getById(id);
            return ResponseEntity.ok(playlist);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<PlaylistDto> createPlaylist(@RequestBody PlaylistDto playlistDto) {
        try {
            PlaylistDto createdPlaylist = playlistService.add(playlistDto);
            return new ResponseEntity<>(createdPlaylist, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaylistDto> updatePlaylist(@PathVariable Long id, @RequestBody PlaylistDto playlistDto) {
        try {
            PlaylistDto updatedPlaylist = playlistService.update(id, playlistDto);
            return ResponseEntity.ok(updatedPlaylist);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlaylistDto> deletePlaylist(@PathVariable Long id) {
        try {
            PlaylistDto deletedPlaylist = playlistService.delete(id);
            return ResponseEntity.ok(deletedPlaylist);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
