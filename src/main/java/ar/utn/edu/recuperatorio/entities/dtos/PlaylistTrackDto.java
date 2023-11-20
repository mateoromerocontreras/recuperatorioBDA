package ar.utn.edu.recuperatorio.entities.dtos;

import ar.utn.edu.recuperatorio.entities.Track;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistTrackDto {
    private PlaylistDto playlist;
    private TrackDto track;
}
