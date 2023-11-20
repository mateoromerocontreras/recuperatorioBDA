package ar.utn.edu.recuperatorio.services;

import ar.utn.edu.recuperatorio.entities.InvoiceItem;
import ar.utn.edu.recuperatorio.entities.dtos.InvoiceItemDto;
import ar.utn.edu.recuperatorio.repositories.InvoiceItemRepository;
import ar.utn.edu.recuperatorio.services.mappers.InvoiceItemDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService {

    private final InvoiceItemRepository invoiceItemRepository;
    private final InvoiceItemDtoMapper invoiceItemDtoMapper;

    @Autowired
    public InvoiceItemServiceImpl(InvoiceItemRepository invoiceItemRepository, InvoiceItemDtoMapper invoiceItemDtoMapper) {
        this.invoiceItemRepository = invoiceItemRepository;
        this.invoiceItemDtoMapper = invoiceItemDtoMapper;
    }

    @Override
    public InvoiceItemDto add(InvoiceItemDto entity) {
        // Implement your logic to add an invoice item
        return null;
    }

    @Override
    public InvoiceItemDto update(Long id, InvoiceItemDto entity) {
        // Implement your logic to update an invoice item
        return null;
    }

    @Override
    public InvoiceItemDto delete(Long id) {
        // Implement your logic to delete an invoice item
        return null;
    }

    @Override
    public InvoiceItemDto getById(Long id) {
        try {
            Optional<InvoiceItem> optionalInvoiceItem = invoiceItemRepository.findById(id);

            if (optionalInvoiceItem.isEmpty()) {
                throw new NoSuchElementException("No se encontró el ítem de factura");
            }

            InvoiceItem invoiceItem = optionalInvoiceItem.get();
            return invoiceItemDtoMapper.apply(invoiceItem);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<InvoiceItemDto> getAll() {
        List<InvoiceItem> invoiceItems = invoiceItemRepository.findAll();

        return invoiceItems
                .stream()
                .map(invoiceItemDtoMapper)
                .collect(Collectors.toList());
    }
}
