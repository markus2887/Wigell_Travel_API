package com.MarkusE.Wigell_Travel_API.controller;

import com.MarkusE.Wigell_Travel_API.entity.Address;
import com.MarkusE.Wigell_Travel_API.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/addresses")
public class AddressController {

    private final MemberService service;

    public AddressController(MemberService service) {
        this.service = service;
    }

    @PostMapping
    public Address create(@RequestBody Address address) {
        return service.saveAddress(address);
    }
}