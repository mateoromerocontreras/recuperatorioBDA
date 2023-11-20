package ar.utn.edu.recuperatorio.repositories;


import ar.utn.edu.recuperatorio.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceReposiroty extends JpaRepository<Invoice, Long> {

}
