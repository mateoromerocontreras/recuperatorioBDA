package ar.utn.edu.recuperatorio.services;

import ar.utn.edu.recuperatorio.entities.Album;
import ar.utn.edu.recuperatorio.entities.Artist;
import ar.utn.edu.recuperatorio.entities.dtos.AlbumDto;
import ar.utn.edu.recuperatorio.repositories.AlbumRepository;
import ar.utn.edu.recuperatorio.repositories.ArtistRepository;
import ar.utn.edu.recuperatorio.services.mappers.AlbumDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService{
    @Autowired
    private final AlbumRepository albumRepository;
    @Autowired
    private final ArtistRepository artistRepository;

    private final AlbumDtoMapper mapper;

    public AlbumServiceImpl(AlbumRepository albumRepository, ArtistRepository artistRepository, AlbumDtoMapper mapper) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.mapper = mapper;
    }

    @Override
    public AlbumDto add(AlbumDto entity) {
        Album a = new Album();
        a.setTitle(entity.getTitle());
        Optional<Artist> optionalArtist = artistRepository.findById(entity.getArtist().getId());
        if (optionalArtist.isEmpty()) {
            throw new NoSuchElementException("No se encontró el artista");
        }

        Album nuevo = albumRepository.save(a);

        return mapper.apply(nuevo);
    }

    @Override
    public AlbumDto update(Long id, AlbumDto entity) {
        Optional<Album> optionalAlbum = albumRepository.findById(id);

        if (optionalAlbum.isEmpty()) {
            throw new NoSuchElementException("No se encontró el album");
        }

        Album album = optionalAlbum.get();
        album.setTitle(entity.getTitle());
        Optional<Artist> optionalArtist = artistRepository.findById(entity.getArtist().getId());
        optionalArtist.ifPresent(album::setArtist);

        Album a = albumRepository.save(album);
        return mapper.apply(a);
    }

    @Override
    public AlbumDto delete(Long aLong) {
        Optional<Album> optionalAlbum = albumRepository.findById(aLong);

        if (optionalAlbum.isEmpty()) {
            throw new NoSuchElementException("No se encontró el album");
        }

        Album album = optionalAlbum.get();

        albumRepository.delete(album);

        return mapper.apply(album);
    }

    @Override
    public AlbumDto getById(Long aLong) {
        Optional<Album> optionalAlbum = albumRepository.findById(aLong);

        if (optionalAlbum.isEmpty()) {
            throw new NoSuchElementException("No se encontró el album");
        }

        Album album = optionalAlbum.get();
        return mapper.apply(album);
    }

    @Override
    public List<AlbumDto> getAll() {
        List<Album> albums = albumRepository.findAll();

        return albums
                .stream()
                .map(mapper)
                .toList();
    }
}
