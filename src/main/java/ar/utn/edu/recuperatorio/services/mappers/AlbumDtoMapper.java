package ar.utn.edu.recuperatorio.services.mappers;

import ar.utn.edu.recuperatorio.entities.Album;
import ar.utn.edu.recuperatorio.entities.dtos.AlbumDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AlbumDtoMapper implements Function<Album, AlbumDto> {
    private final ArtistDtoMapper artistDtoMapper;


    public AlbumDtoMapper(ArtistDtoMapper artistDtoMapper) {

        this.artistDtoMapper = artistDtoMapper;
    }


    @Override
    public AlbumDto apply(Album a) {
        try {
            return new AlbumDto(
                    a.getId(),
                    a.getTitle(),
                    artistDtoMapper.apply(a.getArtist()));
        } catch (NullPointerException e) {
            return null;
        }
    }
}