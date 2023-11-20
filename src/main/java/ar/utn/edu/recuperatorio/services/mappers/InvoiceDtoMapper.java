package ar.utn.edu.recuperatorio.services.mappers;

import ar.utn.edu.recuperatorio.entities.Invoice;
import ar.utn.edu.recuperatorio.entities.dtos.InvoiceDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InvoiceDtoMapper implements Function<Invoice, InvoiceDto> {

    private final CustomerDtoMapper customerDtoMapper;

    public InvoiceDtoMapper(CustomerDtoMapper customerDtoMapper){
        this.customerDtoMapper = customerDtoMapper;
    }

    @Override
    public InvoiceDto apply(Invoice i) {
        try {
            return new InvoiceDto(
                    i.getId(),
                    customerDtoMapper.apply(i.getCustomer()),
                    i.getInvoiceDate(),
                    i.getBillingAddress(),
                    i.getBillingCity(),
                    i.getBillingCountry(),
                    i.getBillingPostalCode(),
                    i.getTotal()
            );
        } catch(NullPointerException e) {
            return null;
        }
    }
}
