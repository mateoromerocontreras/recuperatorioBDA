package ar.utn.edu.recuperatorio.repositories;

import ar.utn.edu.recuperatorio.entities.Artist;
import ar.utn.edu.recuperatorio.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByFirstName(String name);
}
