package com.MarkusE.Wigell_Travel_API.dto;

import java.time.LocalDate;

public record MemberResponseDto(
        Long id,
        String firstName,
        String lastName,
        AddressDto address,
        String email,
        String phone,
        LocalDate dateOfBirth
) {}
