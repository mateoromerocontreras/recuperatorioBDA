package ar.utn.edu.recuperatorio.entities.dtos;

import ar.utn.edu.recuperatorio.entities.Invoice;
import ar.utn.edu.recuperatorio.entities.Track;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemDto {
    private long id;
    private InvoiceDto invoice;
    private TrackDto track;
    private Long unitPrice;
    private Long quantity;
}
