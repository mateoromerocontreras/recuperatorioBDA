package ar.utn.edu.recuperatorio.services;

import ar.utn.edu.recuperatorio.entities.Album;
import ar.utn.edu.recuperatorio.entities.Genre;
import ar.utn.edu.recuperatorio.entities.Track;
import ar.utn.edu.recuperatorio.entities.dtos.TrackDto;
import ar.utn.edu.recuperatorio.repositories.AlbumRepository;
import ar.utn.edu.recuperatorio.repositories.ArtistRepository;
import ar.utn.edu.recuperatorio.repositories.GenreRepository;
import ar.utn.edu.recuperatorio.repositories.TrackRepository;
import ar.utn.edu.recuperatorio.services.mappers.TrackDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class TrackServiceImpl implements TrackService{
    @Autowired
    private final TrackRepository trackRepository;
    @Autowired
    private final AlbumRepository albumRepository;
    @Autowired
    private final GenreRepository genreRepository;
    //@Autowired
    //private final ArtistRepository artistRepository;
    private final TrackDtoMapper trackDtoMapper;
    //private final ConsginaUnoDtoMapper consginaUnoDtoMapper;


    public TrackServiceImpl(TrackRepository trackRepository, AlbumRepository albumRepository,
                            GenreRepository genreRepository, TrackDtoMapper trackDtoMapper) {
        //ConsginaUnoDtoMapper consginaUnoDtoMapper
        //ArtistRepository artistRepository,
        this.trackRepository = trackRepository;
        this.albumRepository = albumRepository;
        this.genreRepository = genreRepository;
        //this.artistRepository = artistRepository;
        this.trackDtoMapper = trackDtoMapper;
        //this.consginaUnoDtoMapper = consginaUnoDtoMapper;
    }

    @Override
    public TrackDto add(TrackDto entity) {
        Track t = new Track();
        t.setName(entity.getName());
        Optional<Album> optionalAlbum = albumRepository.findById(entity.getAlbum().getId());
        if (optionalAlbum.isEmpty()) {
            throw new NoSuchElementException("Ingrese un Album válido");
        }
        optionalAlbum.ifPresent(t::setAlbum);

        Optional<Genre> optionalGenre = genreRepository.findById(entity.getGenre().getId());
        if (optionalGenre.isEmpty()) {
            throw new NoSuchElementException("Ingrese un género válido");
        }
        optionalGenre.ifPresent(t::setGenre);
        t.setComposer(entity.getComposer());
        t.setMediaTypeId(entity.getMediaType());
        t.setMilliseconds(entity.getMilliseconds());
        t.setBytes(entity.getBytes());
        t.setUnitPrice(entity.getUnitPrice());

        Track nuevo = trackRepository.save(t);

        return trackDtoMapper.apply(nuevo);
    }

    @Override
    public TrackDto update(Long id, TrackDto entity) {
        Optional<Track> optionalTrack = trackRepository.findById(id);

        if (optionalTrack.isEmpty()) {
            throw new NoSuchElementException("No se encontró ese track");
        }

        Track t = optionalTrack.get();
        t.setName(entity.getName());
        Optional<Album> optionalAlbum = albumRepository.findById(entity.getAlbum().getId());
        optionalAlbum.ifPresent(t::setAlbum);
        Optional<Genre> optionalGenre = genreRepository.findById(entity.getGenre().getId());
        optionalGenre.ifPresent(t::setGenre);
        t.setComposer(entity.getComposer());
        t.setMilliseconds(entity.getMilliseconds());
        t.setBytes(entity.getBytes());
        t.setUnitPrice(entity.getUnitPrice());

        trackRepository.save(t);

        return trackDtoMapper.apply(t);
    }

    @Override
    public TrackDto delete(Long aLong) {
        Optional<Track> optionalTrack = trackRepository.findById(aLong);

        if (optionalTrack.isEmpty()) {
            throw new NoSuchElementException("No se encontró ese track");
        }

        Track t = optionalTrack.get();
        trackRepository.delete(t);
        return trackDtoMapper.apply(t);
    }

    @Override
    public TrackDto getById(Long aLong) {
        Optional<Track> optionalTrack = trackRepository.findById(aLong);

        if (optionalTrack.isEmpty()) {
            throw new NoSuchElementException("No se encontró ese track");
        }

        Track t = optionalTrack.get();
        return trackDtoMapper.apply(t);
    }

    @Override
    public List<TrackDto> getAll() {
        List<Track> tracks = trackRepository.findAll();

        return tracks
                .stream()
                .map(trackDtoMapper)
                .toList();
    }

//    @Override
//    public List<ConsignaUnoDto> getAllFiltrado(Long idArtist, Long idGenre) {
//        List<Track> tracks = trackRepository.findAll();
//        Optional<Artist> optionalArtist = artistRepository.findById(idArtist);
//
//        if (optionalArtist.isEmpty()) {
//            throw new NoSuchElementException();
//        }
//
//        // Filtrar por artista
//        tracks = tracks.stream()
//                .filter(t -> t.getAlbum().getArtist().getId() == idArtist)
//                .collect(Collectors.toList());
//
//        if (tracks.isEmpty()) {
//            throw new NullPointerException();
//        }
//
//        // Filtrar por género
//        if (idGenre != null) {
//            tracks = tracks.stream()
//                    .filter(t -> t.getGenre().getId() == idGenre)
//                    .collect(Collectors.toList());
//        }
//
//        return tracks
//                .stream()
//                .map(consginaUnoDtoMapper)
//                .toList();
//    }
}
