package ar.utn.edu.recuperatorio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoice_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoicelineid")
    private long id;

    @ManyToOne
    @JoinColumn(name = "invoiceid")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "trackid")
    private Track track;

    @Column(name = "unitprice")
    private Long unitPrice;

    @Column(name = "quantity")
    private Long quantity;
}
