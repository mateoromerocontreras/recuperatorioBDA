package ar.utn.edu.recuperatorio.services.mappers;

import ar.utn.edu.recuperatorio.entities.Artist;
import ar.utn.edu.recuperatorio.entities.Customer;
import ar.utn.edu.recuperatorio.entities.dtos.ArtistDto;
import ar.utn.edu.recuperatorio.entities.dtos.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerDtoMapper implements Function<Customer, CustomerDto> {
    @Override
    public CustomerDto apply(Customer c)
    {
        try {
            return new CustomerDto(
                    c.getCustomerId(),
                    c.getFirstName(),
                    c.getLastName(),
                    c.getCompany(),
                    c.getAddress(),
                    c.getCity(),
                    c.getState(),
                    c.getCountry(),
                    c.getPostalCode(),
                    c.getPhone(),
                    c.getFax(),
                    c.getEmail(),
                    c.getSupportRep()

            );

        } catch (NullPointerException e) {
            return null;
        }

    }
}
