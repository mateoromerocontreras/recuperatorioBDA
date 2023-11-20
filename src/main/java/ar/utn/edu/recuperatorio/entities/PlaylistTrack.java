package ar.utn.edu.recuperatorio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "playlist_track")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlisttrackid")
    private Long playlisttrackid;

    @ManyToOne
    @JoinColumn(name = "playlistid")
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "trackid")
    private Track track;
}
