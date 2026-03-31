package com.MarkusE.Wigell_Travel_API.dto;

public record MemberPublicDto(
        String firstName,
        String lastName,
        AddressDto address,
        String email,
        String phone) {}
