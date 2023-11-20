package ar.utn.edu.recuperatorio.services.mappers;

import ar.utn.edu.recuperatorio.entities.Playlist;
import ar.utn.edu.recuperatorio.entities.dtos.PlaylistDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PlaylistDtoMapper implements Function<Playlist, PlaylistDto> {

    @Override
    public PlaylistDto apply(Playlist p) {
        try {
            return new PlaylistDto(
                    p.getId(),
                    p.getName()
            );

        } catch (NullPointerException e) {
            return null;
        }
    }
}