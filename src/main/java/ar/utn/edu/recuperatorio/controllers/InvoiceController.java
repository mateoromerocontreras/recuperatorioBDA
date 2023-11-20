package ar.utn.edu.recuperatorio.controllers;

import ar.utn.edu.recuperatorio.entities.dtos.InvoiceDto;
import ar.utn.edu.recuperatorio.entities.dtos.InvoiceItemDto;
import ar.utn.edu.recuperatorio.services.InvoiceItemService;
import ar.utn.edu.recuperatorio.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getAllInvoices() {
        List<InvoiceDto> invoices = invoiceService.getAll();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable Long id) {
        InvoiceDto invoice = invoiceService.getById(id);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InvoiceDto> addInvoice(@RequestBody InvoiceDto invoiceDto) {
        // TODO: Implement logic to add an invoice
        InvoiceDto addedInvoice = invoiceService.add(invoiceDto);
        return new ResponseEntity<>(addedInvoice, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDto> updateInvoice(@PathVariable Long id, @RequestBody InvoiceDto invoiceDto) {
        // TODO: Implement logic to update an invoice
        InvoiceDto updatedInvoice = invoiceService.update(id, invoiceDto);
        return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InvoiceDto> deleteInvoice(@PathVariable Long id) {
        // TODO: Implement logic to delete an invoice
        InvoiceDto deletedInvoice = invoiceService.delete(id);
        return new ResponseEntity<>(deletedInvoice, HttpStatus.OK);
    }
}
