package ar.utn.edu.recuperatorio.services.mappers;

import ar.utn.edu.recuperatorio.entities.Genre;
import ar.utn.edu.recuperatorio.entities.dtos.GenreDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GenreDtoMapper implements Function<Genre, GenreDto> {
    @Override
    public GenreDto apply(Genre g) {
        try {
            return new GenreDto(
                    g.getId(),
                    g.getName()
            );
        } catch (NullPointerException e) {
            return null;
        }
    }
}
