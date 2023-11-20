package ar.utn.edu.recuperatorio.services;

import ar.utn.edu.recuperatorio.entities.Artist;
import ar.utn.edu.recuperatorio.entities.Customer;
import ar.utn.edu.recuperatorio.entities.dtos.CustomerDto;
import ar.utn.edu.recuperatorio.repositories.CustomerRepository;
import ar.utn.edu.recuperatorio.services.mappers.CustomerDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;

    private final CustomerDtoMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerDtoMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public CustomerDto add(CustomerDto entity) {
        Customer c = new Customer();
        c.setFirstName(entity.getFirstName());
        c.setLastName(entity.getLastName());
        c.setCompany(entity.getCompany());
        c.setAddress(entity.getAddress());
        c.setCity(entity.getCity());
        c.setState(entity.getState());
        c.setCountry(entity.getCountry());
        c.setPostalCode(entity.getPostalCode());
        c.setPhone(entity.getPhone());
        c.setFax(entity.getFax());
        c.setEmail(entity.getEmail());
        c.setSupportRep(entity.getSupportRep());

        Customer nuevo = customerRepository.save(c);

        return mapper.apply(nuevo);
    }

    @Override
    public CustomerDto update(Long id, CustomerDto entity) {
        Optional<Customer> optionalCustomer = customerRepository.findById(entity.getCustomerId());
        if (optionalCustomer.isEmpty()){
            throw new NoSuchElementException("No se encontró el customer");
        }

        Customer c = optionalCustomer.get();
        c.setFirstName(entity.getFirstName());
        c.setLastName(entity.getLastName());
        c.setCompany(entity.getCompany());
        c.setAddress(entity.getAddress());
        c.setCity(entity.getCity());
        c.setState(entity.getState());
        c.setCountry(entity.getCountry());
        c.setPostalCode(entity.getPostalCode());
        c.setPhone(entity.getPhone());
        c.setFax(entity.getFax());
        c.setEmail(entity.getEmail());
        c.setSupportRep(entity.getSupportRep());

        customerRepository.save(c);

        return mapper.apply(c);
    }

    @Override
    public CustomerDto delete(Long aLong) {
        Optional<Customer> optionalCustomer = customerRepository.findById(aLong);
        if (optionalCustomer.isEmpty()){
            throw new NoSuchElementException("No se encontró el customer");
        }

        Customer c = optionalCustomer.get();
        customerRepository.delete(c);
        return mapper.apply(c);
    }

    @Override
    public CustomerDto getById(Long aLong) {
        Optional<Customer> optionalCustomer = customerRepository.findById(aLong);
        if (optionalCustomer.isEmpty()){
            throw new NoSuchElementException("No se encontró el customer");
        }
        Customer c = optionalCustomer.get();
        return mapper.apply(c);
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> customers = customerRepository.findAll();

        return customers
                .stream()
                .map(mapper)
                .toList();
    }
}
