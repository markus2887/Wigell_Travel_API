package com.MarkusE.Wigell_Travel_API.controller;

import com.MarkusE.Wigell_Travel_API.dto.AddressDto;
import com.MarkusE.Wigell_Travel_API.entity.Address;
import com.MarkusE.Wigell_Travel_API.service.CustomerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers/{customerId}/addresses")
public class AddressController {

    private final CustomerService service;

    public AddressController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Address create(@PathVariable Long customerId, @RequestBody AddressDto dto) {
        return service.saveAddress(customerId, dto);
    }

    @DeleteMapping("/{addressId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void removeAddress(@PathVariable Long customerId, @PathVariable Long addressId) {
        service.removeAddressFromCustomer(customerId, addressId);
    }
}