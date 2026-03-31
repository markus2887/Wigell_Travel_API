package com.MarkusE.Wigell_Travel_API.controller;

import com.MarkusE.Wigell_Travel_API.dto.CustomerResponseDto;
import com.MarkusE.Wigell_Travel_API.mapper.CustomerMapper;
import com.MarkusE.Wigell_Travel_API.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerControllerGAMMAL {

    private final CustomerService customerService;
    private final CustomerMapper mapper;

    public CustomerControllerGAMMAL(CustomerService customerService, CustomerMapper mapper) {
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<CustomerResponseDto> getCustomers() {
        return customerService.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}
