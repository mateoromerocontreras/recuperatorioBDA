package ar.utn.edu.recuperatorio.services;

import ar.utn.edu.recuperatorio.entities.Customer;
import ar.utn.edu.recuperatorio.entities.Invoice;
import ar.utn.edu.recuperatorio.entities.dtos.InvoiceDto;
import ar.utn.edu.recuperatorio.repositories.CustomerRepository;
import ar.utn.edu.recuperatorio.repositories.InvoiceRepository;
import ar.utn.edu.recuperatorio.services.mappers.InvoiceDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
            ;
    private final InvoiceDtoMapper invoiceDtoMapper;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, CustomerRepository customerRepository, InvoiceDtoMapper invoiceDtoMapper) {
        this.invoiceRepository = invoiceRepository;
        this.customerRepository = customerRepository;
        this.invoiceDtoMapper = invoiceDtoMapper;
    }

    @Override
    public InvoiceDto add(InvoiceDto entity) {
        // Implement your logic to add an invoice
        Invoice i = new Invoice();
        Optional<Customer> optionalCustomer = customerRepository.findById(entity.getCustomer().getCustomerId());
        optionalCustomer.ifPresent(i::setCustomer);
        i.setInvoiceDate(entity.getInvoiceDate());
        i.setBillingAddress(entity.getBillingAddress());
        i.setBillingCity(entity.getBillingCity());
        i.setBillingCountry(entity.getBillingCountry());
        i.setBillingPostalCode(entity.getBillingPostalCode());
        i.setTotal(entity.getTotal());
        Invoice nuevo = invoiceRepository.save(i);
        return invoiceDtoMapper.apply(nuevo);
    }

    @Override
    public InvoiceDto update(Long id, InvoiceDto entity) {
        // Implement your logic to update an invoice
        return null;
    }

    @Override
    public InvoiceDto delete(Long id) {
        // Implement your logic to delete an invoice
        return null;
    }

    @Override
    public InvoiceDto getById(Long id) {
        try {
            Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

            if (optionalInvoice.isEmpty()) {
                throw new NoSuchElementException("No se encontró la factura");
            }

            Invoice invoice = optionalInvoice.get();
            return invoiceDtoMapper.apply(invoice);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<InvoiceDto> getAll() {
        List<Invoice> invoices = invoiceRepository.findAll();

        return invoices
                .stream()
                .map(invoiceDtoMapper)
                .collect(Collectors.toList());
    }
}
