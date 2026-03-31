package com.MarkusE.Wigell_Travel_API.controller;

import com.MarkusE.Wigell_Travel_API.dto.MemberDto;
import com.MarkusE.Wigell_Travel_API.dto.MemberResponseDto;
import com.MarkusE.Wigell_Travel_API.entity.Address;
import com.MarkusE.Wigell_Travel_API.entity.Member;
import com.MarkusE.Wigell_Travel_API.mapper.MemberMapper;
import com.MarkusE.Wigell_Travel_API.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/members")
public class AdminController {

    private final MemberService service;
    private final MemberMapper mapper;

    public AdminController(MemberService service, MemberMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<MemberResponseDto> getAll() {

        return service.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @GetMapping("/{id}")
    public MemberResponseDto getById(@PathVariable Long id) {
        return mapper.toResponseDto(service.findById(id));
    }

    @PostMapping
    public MemberResponseDto create(@RequestBody MemberDto dto) {

        Address address = service.getAddress(dto.addressId());
        Member member = mapper.toEntity(dto, address);

        return mapper.toResponseDto(service.save(member));
    }

    @PutMapping("/{id}")
    public MemberResponseDto update(@PathVariable Long id, @RequestBody MemberDto dto) {

        Member existing = service.findById(id);
        Address address = service.getAddress(dto.addressId());
        mapper.updateMember(dto, existing, address);

        return mapper.toResponseDto(service.save(existing));
    }

    @PatchMapping("/{id}")
    public MemberResponseDto patch(@PathVariable Long id, @RequestBody MemberDto dto) {

        Member existing = service.findById(id);

        Address address = null;
        if (dto.addressId() != null) {
            address = service.getAddress(dto.addressId());
        }

        mapper.updateMember(dto, existing, address);

        return mapper.toResponseDto(service.save(existing));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}