package ar.utn.edu.recuperatorio.services.mappers;

import ar.utn.edu.recuperatorio.entities.*;

import ar.utn.edu.recuperatorio.entities.dtos.TrackDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TrackDtoMapper implements Function<Track, TrackDto> {
    private final AlbumDtoMapper albumDtoMapper;
    private final GenreDtoMapper genreDtoMapper;

    public TrackDtoMapper(AlbumDtoMapper albumDtoMapper, GenreDtoMapper genreDtoMapper) {
        this.albumDtoMapper = albumDtoMapper;
        this.genreDtoMapper = genreDtoMapper;
    }

    @Override
    public TrackDto apply(Track t) {
        try {
            return new TrackDto(
                    t.getTrackId(),
                    t.getName(),
                    albumDtoMapper.apply(t.getAlbum()),
                    t.getMediaTypeId(),
                    genreDtoMapper.apply(t.getGenre()),
                    t.getComposer(),
                    t.getMilliseconds(),
                    t.getBytes(),
                    t.getUnitPrice()
            );
        } catch (NullPointerException e) {
            return null;
        }
    }
}
