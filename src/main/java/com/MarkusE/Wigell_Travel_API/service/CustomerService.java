package com.MarkusE.Wigell_Travel_API.service;

import com.MarkusE.Wigell_Travel_API.dto.AddressDto;
import com.MarkusE.Wigell_Travel_API.entity.Address;
import com.MarkusE.Wigell_Travel_API.entity.Customer;
import com.MarkusE.Wigell_Travel_API.repo.AddressRepository;
import com.MarkusE.Wigell_Travel_API.repo.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepo;
    private final AddressRepository addressRepo;

    public CustomerService(CustomerRepository customerRepo, AddressRepository addressRepo) {
        this.customerRepo = customerRepo;
        this.addressRepo = addressRepo;
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public Customer findById(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id " + id));
    }

    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    public void delete(Long id) {
        Customer existing = findById(id); // säkerställ att medlem finns
        customerRepo.delete(existing);
    }

    public Address getAddress(Long id) {
        return addressRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id " + id));
    }

    public Address saveAddress(Long customerId, AddressDto dto) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow();

        Address address = new Address(
                dto.street(),
                dto.postalCode(),
                dto.city()
        );

        // Spara adress först
        Address savedAddress = addressRepo.save(address);

        // Koppla customer till adress
        customer.setAddress(savedAddress);

        // Spara customer
        customerRepo.save(customer);

        return savedAddress;
    }

    public void deleteAddress(Long customerId, Long addressId) {
        Address address = addressRepo.findById(addressId)
                .orElseThrow();

        addressRepo.delete(address);
    }
}
