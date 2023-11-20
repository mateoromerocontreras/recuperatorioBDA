package ar.utn.edu.recuperatorio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tracks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trackid")
    private Long trackId;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "albumid")
    private Long albumId;

    @Column(name = "mediatypeid", nullable = false)
    private Long mediaTypeId;

    @Column(name = "genreid")
    private Long genreId;

    @Column(name = "composer", length = 220)
    private String composer;

    @Column(name = "milliseconds", nullable = false)
    private Long milliseconds;

    @Column(name = "bytes")
    private Long bytes;

    @Column(name = "unitprice")
    private Long unitPrice;

    @ManyToOne
    @JoinColumn(name = "albumid", insertable = false, updatable = false)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "genreid", insertable = false, updatable = false)
    private Genre genre;

    @OneToMany(mappedBy = "track")
    private List<InvoiceItem> invoiceItem;

    @OneToMany(mappedBy = "track")
    private List<PlaylistTrack> playlistTracks;

}
