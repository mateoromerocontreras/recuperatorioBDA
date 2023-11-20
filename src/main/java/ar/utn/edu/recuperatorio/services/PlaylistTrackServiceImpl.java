package ar.utn.edu.recuperatorio.services;

import ar.utn.edu.recuperatorio.entities.Playlist;
import ar.utn.edu.recuperatorio.entities.PlaylistTrack;
import ar.utn.edu.recuperatorio.entities.Track;
import ar.utn.edu.recuperatorio.entities.dtos.PlaylistTrackDto;
import ar.utn.edu.recuperatorio.repositories.PlaylistRepository;
import ar.utn.edu.recuperatorio.repositories.PlaylistTrackRepository;
import ar.utn.edu.recuperatorio.repositories.TrackRepository;
import ar.utn.edu.recuperatorio.services.mappers.PlaylistTrackDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service

public class PlaylistTrackServiceImpl implements PlaylistTrackService{
    @Autowired
    private final PlaylistRepository playlistRepository;
    @Autowired
    private final TrackRepository trackRepository;
    @Autowired
    private final PlaylistTrackRepository playlistTrackRepository;
    private final PlaylistTrackDtoMapper playlistTrackDtoMapper;

    public PlaylistTrackServiceImpl(PlaylistRepository playlistRepository, TrackRepository trackRepository, PlaylistTrackRepository playlistTrackRepository, PlaylistTrackDtoMapper playlistTrackDtoMapper) {
        this.playlistRepository = playlistRepository;
        this.trackRepository = trackRepository;
        this.playlistTrackRepository = playlistTrackRepository;
        this.playlistTrackDtoMapper = playlistTrackDtoMapper;
    }


    @Override
    public PlaylistTrackDto add(PlaylistTrackDto entity) {
        PlaylistTrack p = new PlaylistTrack();
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(entity.getPlaylist().getId());
        Optional<Track> optionalTrack = trackRepository.findById(entity.getTrack().getId());

        if (optionalTrack.isEmpty() || optionalPlaylist.isEmpty()) {
            throw new NoSuchElementException("No se encontró la playlist / track");
        }

        p.setPlaylist(optionalPlaylist.get());
        p.setTrack(optionalTrack.get());

        PlaylistTrack nuevo = playlistTrackRepository.save(p);

        return playlistTrackDtoMapper.apply(nuevo);

    }

    @Override
    public PlaylistTrackDto update(Long id, PlaylistTrackDto entity) {

        return null;
    }

    @Override
    public PlaylistTrackDto delete(Long aLong) {
        Optional<PlaylistTrack> p = playlistTrackRepository.findById(aLong);

        if (p.isEmpty()) {
            throw new NoSuchElementException("No existe");
        }
        PlaylistTrack playlistTrack = p.get();
        playlistTrackRepository.delete(playlistTrack);
        return playlistTrackDtoMapper.apply(playlistTrack);
    }

    @Override
    public PlaylistTrackDto getById(Long aLong) {
        Optional<PlaylistTrack> p = playlistTrackRepository.findById(aLong);

        if (p.isEmpty()) {
            throw new NoSuchElementException("No existe");
        }
        return playlistTrackDtoMapper.apply(p.get());
    }

    @Override
    public List<PlaylistTrackDto> getAll() {
        List<PlaylistTrack> playlistTracks = playlistTrackRepository.findAll();
        return playlistTracks
                .stream()
                .map(playlistTrackDtoMapper)
                .toList();
    }

    @Override
    public List<PlaylistTrackDto> obtenerPorPlaylistId(Long playlistId) {
        List<PlaylistTrack> playlistTracks = playlistTrackRepository
                .findPlaylistTracksByPlaylistId(playlistId);

        if (playlistTracks.isEmpty()) {
            throw new NoSuchElementException("No existe esta id");
        }

        return playlistTracks
                .stream()
                .map(playlistTrackDtoMapper)
                .toList();
    }
}
