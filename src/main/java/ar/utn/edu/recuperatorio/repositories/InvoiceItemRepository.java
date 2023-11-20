package ar.utn.edu.recuperatorio.repositories;

import ar.utn.edu.recuperatorio.entities.Genre;
import ar.utn.edu.recuperatorio.entities.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
