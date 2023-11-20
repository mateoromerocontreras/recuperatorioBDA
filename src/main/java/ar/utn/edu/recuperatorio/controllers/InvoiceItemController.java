package ar.utn.edu.recuperatorio.controllers;

import ar.utn.edu.recuperatorio.entities.dtos.InvoiceItemDto;
import ar.utn.edu.recuperatorio.services.InvoiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice-items")
public class InvoiceItemController {

    private final InvoiceItemService invoiceItemService;

    @Autowired
    public InvoiceItemController(InvoiceItemService invoiceItemService) {
        this.invoiceItemService = invoiceItemService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceItemDto>> getAllInvoiceItems() {
        List<InvoiceItemDto> invoiceItems = invoiceItemService.getAll();
        return new ResponseEntity<>(invoiceItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceItemDto> getInvoiceItemById(@PathVariable Long id) {
        InvoiceItemDto invoiceItem = invoiceItemService.getById(id);
        return new ResponseEntity<>(invoiceItem, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InvoiceItemDto> addInvoiceItem(@RequestBody InvoiceItemDto invoiceItemDto) {
        // TODO: Implement logic to add an invoice item
        InvoiceItemDto addedInvoiceItem = invoiceItemService.add(invoiceItemDto);
        return new ResponseEntity<>(addedInvoiceItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceItemDto> updateInvoiceItem(@PathVariable Long id, @RequestBody InvoiceItemDto invoiceItemDto) {
        // TODO: Implement logic to update an invoice item
        InvoiceItemDto updatedInvoiceItem = invoiceItemService.update(id, invoiceItemDto);
        return new ResponseEntity<>(updatedInvoiceItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InvoiceItemDto> deleteInvoiceItem(@PathVariable Long id) {
        // TODO: Implement logic to delete an invoice item
        InvoiceItemDto deletedInvoiceItem = invoiceItemService.delete(id);
        return new ResponseEntity<>(deletedInvoiceItem, HttpStatus.OK);
    }
}
