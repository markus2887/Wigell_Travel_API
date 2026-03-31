package com.MarkusE.Wigell_Travel_API.dto;

import java.time.LocalDate;

public record MemberDto(
        String firstName,
        String lastName,
        Long addressId,
        String email,
        String phone,
        LocalDate dateOfBirth

) {}
