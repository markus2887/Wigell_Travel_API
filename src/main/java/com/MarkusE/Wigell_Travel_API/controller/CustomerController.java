package com.MarkusE.Wigell_Travel_API.controller;

import com.MarkusE.Wigell_Travel_API.dto.CreateCustomerDto;
import com.MarkusE.Wigell_Travel_API.dto.CustomerResponseDto;
import com.MarkusE.Wigell_Travel_API.entity.Address;
import com.MarkusE.Wigell_Travel_API.entity.Customer;
import com.MarkusE.Wigell_Travel_API.mapper.CustomerMapper;
import com.MarkusE.Wigell_Travel_API.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@EnableMethodSecurity
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService service;
    private final CustomerMapper mapper;

    public CustomerController(CustomerService service, CustomerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<CustomerResponseDto> getAllCustomers() {

        return service.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CustomerResponseDto getById(@PathVariable Long id) {
        return mapper.toResponseDto(service.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerResponseDto> create(@RequestBody CreateCustomerDto dto) {

        Address address = service.getAddress(dto.addressId());
        Customer customer = mapper.toEntity(dto, address);

        Customer saved = service.save(customer);

        CustomerResponseDto responseDto = mapper.toResponseDto(saved);

        URI location = URI.create("/api/v1/customers/" + saved.getId());

        return ResponseEntity
                .created(location) // 👈 201 + Location header
                .body(responseDto);
    }

    /*@PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CustomerResponseDto create(@RequestBody CreateCustomerDto dto) {

        Address address = service.getAddress(dto.addressId());
        Customer customer = mapper.toEntity(dto, address);

        return mapper.toResponseDto(service.save(customer));
    }*/

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CustomerResponseDto update(@PathVariable Long id, @RequestBody CreateCustomerDto dto) {

        Customer existing = service.findById(id);
        Address address = service.getAddress(dto.addressId());
        mapper.updateCustomer(dto, existing, address);

        return mapper.toResponseDto(service.save(existing));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CustomerResponseDto patch(@PathVariable Long id, @RequestBody CreateCustomerDto dto) {

        Customer existing = service.findById(id);

        Address address = null;
        if (dto.addressId() != null) {
            address = service.getAddress(dto.addressId());
        }

        mapper.updateCustomer(dto, existing, address);

        return mapper.toResponseDto(service.save(existing));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}