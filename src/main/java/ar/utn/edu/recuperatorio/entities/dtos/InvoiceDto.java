package ar.utn.edu.recuperatorio.entities.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private Long id;
    private CustomerDto customer;
    private LocalDateTime invoiceDate;
    private String billingAddress;
    private String billingCity;
    private String billingCountry;
    private String billingPostalCode;
    private Long total;
}
