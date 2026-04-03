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

        // 🔍 Kolla om adressen redan finns
        Address address = addressRepo
                .findByStreetAndPostalCodeAndCity(
                        dto.street(),
                        dto.postalCode(),
                        dto.city()
                )
                .orElseGet(() -> {
                    // ➕ Skapa ny om den inte finns
                    Address newAddress = new Address(
                            dto.street(),
                            dto.postalCode(),
                            dto.city()
                    );
                    return addressRepo.save(newAddress);
                });

        // 🔗 Koppla customer till adress
        customer.setAddress(address);
        customerRepo.save(customer);

        return address;
    }

    public void removeAddressFromCustomer(Long customerId, Long addressId) {

        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Address address = customer.getAddress();

        if (address == null) {
            throw new RuntimeException("Customer has no address");
        }

        // säkerställ rätt adress
        if (!address.getAddressId().equals(addressId)) {
            throw new RuntimeException("Wrong address");
        }

        // 1. ta bort koppling
        Address emptyAddress = new Address("", "", "");
        addressRepo.save(emptyAddress);
        customer.setAddress(emptyAddress);
        customerRepo.save(customer);

        // 2. kolla om någon annan använder adressen
        long count = customerRepo.countByAddress_AddressId(addressId);

        // 3. om ingen använder → radera
        if (count == 0) {
            addressRepo.deleteById(addressId);
        }
    }
}
