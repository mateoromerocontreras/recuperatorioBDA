package ar.utn.edu.recuperatorio.services;

import ar.utn.edu.recuperatorio.entities.dtos.ArtistDto;
import ar.utn.edu.recuperatorio.entities.dtos.PlaylistTrackDto;

import java.util.List;

public interface PlaylistTrackService extends Service<PlaylistTrackDto, Long>{
    public List<PlaylistTrackDto> obtenerPorPlaylistId(Long playlistId);
}
