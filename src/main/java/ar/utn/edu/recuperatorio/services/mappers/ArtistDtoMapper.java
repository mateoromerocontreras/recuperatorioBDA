package ar.utn.edu.recuperatorio.services.mappers;

import ar.utn.edu.recuperatorio.entities.Artist;
import ar.utn.edu.recuperatorio.entities.dtos.ArtistDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ArtistDtoMapper implements Function<Artist, ArtistDto> {
    @Override
    public ArtistDto apply(Artist a) {
        try {
            return new ArtistDto(
                    a.getId(),
                    a.getName());
        } catch (NullPointerException e) {
            return null;
        }
    }
}