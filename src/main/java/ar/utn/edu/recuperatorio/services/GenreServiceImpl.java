package ar.utn.edu.recuperatorio.services;



import ar.utn.edu.recuperatorio.entities.Genre;
import ar.utn.edu.recuperatorio.entities.dtos.GenreDto;
import ar.utn.edu.recuperatorio.repositories.AlbumRepository;
import ar.utn.edu.recuperatorio.repositories.GenreRepository;
import ar.utn.edu.recuperatorio.services.mappers.GenreDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service

public class GenreServiceImpl implements GenreService{
    @Autowired
    private final GenreRepository genreRepository;


    private final GenreDtoMapper mapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreDtoMapper mapper) {
        this.genreRepository = genreRepository;
        this.mapper = mapper;
    }

    @Override
    public GenreDto add(GenreDto entity) {
        Genre g = new Genre();
        g.setName(entity.getName());

        Genre nuevo = genreRepository.save(g);

        return mapper.apply(nuevo);
    }

    @Override
    public GenreDto update(Long id, GenreDto entity) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);

        if (optionalGenre.isEmpty()) {
            throw new NoSuchElementException("No se encontró el genre");
        }

        Genre genre = optionalGenre.get();
        genre.setName(entity.getName());


        Genre g = genreRepository.save(genre);
        return mapper.apply(g);
    }

    @Override
    public GenreDto delete(Long aLong) {
        Optional<Genre> optionalGenre = genreRepository.findById(aLong);

        if (optionalGenre.isEmpty()) {
            throw new NoSuchElementException("No se encontró el genre");
        }

        Genre genre = optionalGenre.get();

        genreRepository.delete(genre);

        return mapper.apply(genre);
    }

    @Override
    public GenreDto getById(Long aLong) {
        Optional<Genre> optionalGenre = genreRepository.findById(aLong);

        if (optionalGenre.isEmpty()) {
            throw new NoSuchElementException("No se encontró el genre");
        }

        Genre genre = optionalGenre.get();
        return mapper.apply(genre);
    }

    @Override
    public List<GenreDto> getAll() {
        List<Genre> genres = genreRepository.findAll();

        return genres
                .stream()
                .map(mapper)
                .toList();
    }
}
