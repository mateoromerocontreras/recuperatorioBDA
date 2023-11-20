package ar.utn.edu.recuperatorio.repositories;


import ar.utn.edu.recuperatorio.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
