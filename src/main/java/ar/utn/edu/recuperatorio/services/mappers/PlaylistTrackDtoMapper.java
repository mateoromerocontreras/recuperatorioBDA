package ar.utn.edu.recuperatorio.services.mappers;

import ar.utn.edu.recuperatorio.entities.PlaylistTrack;
import ar.utn.edu.recuperatorio.entities.dtos.*;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PlaylistTrackDtoMapper implements Function<PlaylistTrack, PlaylistTrackDto> {
    private final PlaylistDtoMapper playlistDtoMapper;
    private final TrackDtoMapper trackDtoMapper;


    public PlaylistTrackDtoMapper(PlaylistDtoMapper playlistDtoMapper, TrackDtoMapper trackDtoMapper) {
        this.trackDtoMapper = trackDtoMapper;
        this.playlistDtoMapper = playlistDtoMapper;
    }

    @Override
    public PlaylistTrackDto apply(PlaylistTrack p) {
        try {
            return new PlaylistTrackDto(
                    playlistDtoMapper.apply(p.getPlaylist()),
                    trackDtoMapper.apply(p.getTrack())
            );
        } catch (NullPointerException e) {
            return null;
        }
    }
}