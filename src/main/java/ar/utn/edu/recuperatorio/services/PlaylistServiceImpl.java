package ar.utn.edu.recuperatorio.services;

import ar.utn.edu.recuperatorio.entities.Playlist;
import ar.utn.edu.recuperatorio.entities.dtos.PlaylistDto;
import ar.utn.edu.recuperatorio.repositories.ArtistRepository;
import ar.utn.edu.recuperatorio.repositories.AlbumRepository;
import ar.utn.edu.recuperatorio.repositories.PlaylistRepository;
import ar.utn.edu.recuperatorio.repositories.TrackRepository;
import ar.utn.edu.recuperatorio.services.mappers.PlaylistDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class PlaylistServiceImpl implements PlaylistService{
    @Autowired
    private final PlaylistRepository playlistRepository;
    @Autowired
    private final ArtistRepository artistRepository;
    @Autowired
    private final AlbumRepository genreRepository;
    @Autowired
    private final TrackRepository trackRepository;
    private final PlaylistDtoMapper playlistDtoMapper;
    //private final ConsignaDosDtoMapper consignaDosDtoMapper;


    public PlaylistServiceImpl(PlaylistRepository playlistRepository, ArtistRepository artistRepository, AlbumRepository genreRepository, TrackRepository trackRepository, PlaylistDtoMapper playlistDtoMapper) {
        //, ConsignaDosDtoMapper consignaDosDtoMapper
        this.playlistRepository = playlistRepository;
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
        this.trackRepository = trackRepository;
        this.playlistDtoMapper = playlistDtoMapper;
        //this.consignaDosDtoMapper = consignaDosDtoMapper;
    }

    @Override
    public PlaylistDto add(PlaylistDto entity) {
        Playlist p = new Playlist();
        p.setName(entity.getName());
        Playlist nuevo = playlistRepository.save(p);
        return playlistDtoMapper.apply(nuevo);
    }

    @Override
    public PlaylistDto update(Long id, PlaylistDto entity) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);

        if (optionalPlaylist.isEmpty()) {
            throw new NoSuchElementException("No se encontró esa playlist");
        }

        Playlist p = optionalPlaylist.get();
        p.setName(entity.getName());
        playlistRepository.save(p);

        return playlistDtoMapper.apply(p);
    }

    @Override
    public PlaylistDto delete(Long aLong) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(aLong);

        if (optionalPlaylist.isEmpty()) {
            throw new NoSuchElementException("No se encontró esa playlist");
        }

        Playlist p = optionalPlaylist.get();
        playlistRepository.delete(p);
        return playlistDtoMapper.apply(p);
    }

    @Override
    public PlaylistDto getById(Long aLong) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(aLong);

        if (optionalPlaylist.isEmpty()) {
            throw new NoSuchElementException("No se encontró esa playlist");
        }

        Playlist p = optionalPlaylist.get();
        return playlistDtoMapper.apply(p);
    }

    @Override
    public List<PlaylistDto> getAll() {
        List<Playlist> playlists = playlistRepository.findAll();
        return playlists
                .stream()
                .map(playlistDtoMapper)
                .toList();
    }

//    @Override
//    public ConsignaDosDto crear(ConsginaDosDto2 entity) {
//
//        Optional<Artist> optionalArtist = artistRepository.findById(entity.getIdArtist());
//
//        if (optionalArtist.isEmpty()) {
//            throw new NoSuchElementException("No se encontró el artista o género");
//        }
//
//        Artist artist = optionalArtist.get();
//
//        List<Track> tracks = trackRepository.findAll();
//        tracks = tracks.stream()
//                .filter(track -> track.getAlbum().getArtist() == artist && track.getGenre().getId() == entity.getIdGenre())
//                .sorted(Comparator.comparing(Track::getUnitPrice))
//                .collect(Collectors.toList());
//
//        List<Track> tracksParaPlaylist = new ArrayList<>();
//
//        long acumulador = 0;
//        for (Track t: tracks) {
//            acumulador += t.getMilliseconds();
//            if (acumulador <= (entity.getMinutos() * 60 * 1000)) {
//                tracksParaPlaylist.add(t);
//            }
//        }
//
//        Playlist nueva = new Playlist();
//        nueva.setTracks(tracksParaPlaylist);
//        nueva.setName(entity.getName());
//        Playlist p = playlistRepository.save(nueva);
//
//        if (tracksParaPlaylist.isEmpty()) {
//            throw new NullPointerException();
//        }
//
//        return consignaDosDtoMapper.apply(p);
//    }
}
