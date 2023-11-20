package ar.utn.edu.recuperatorio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoiceid")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerid")
    private Customer customer;

    @Column(name = "invoicedate")
    private LocalDateTime invoiceDate;

    @Column(name = "billingaddress")
    private String billingAddress;

    @Column(name = "billingcity")
    private String billingCity;

    @Column(name = "billingcountry")
    private String billingCountry;

    @Column(name = "billingpostalcode")
    private String billingPostalCode;

    @Column(name = "total")
    private Long total;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<InvoiceItem> invoiceItems;
}
