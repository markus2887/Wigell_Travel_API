package com.MarkusE.Wigell_Travel_API.mapper;

import com.MarkusE.Wigell_Travel_API.dto.AddressDto;
import com.MarkusE.Wigell_Travel_API.dto.MemberDto;
import com.MarkusE.Wigell_Travel_API.dto.MemberPublicDto;
import com.MarkusE.Wigell_Travel_API.dto.MemberResponseDto;
import com.MarkusE.Wigell_Travel_API.entity.Address;
import com.MarkusE.Wigell_Travel_API.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberResponseDto toResponseDto(Member member) {

        Address address = member.getAddress();

        AddressDto addressDto = new AddressDto(
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );

        return new MemberResponseDto(
                member.getId(),
                member.getFirstName(),
                member.getLastName(),
                addressDto,
                member.getEmail(),
                member.getPhone(),
                member.getDateOfBirth()
        );
    }

    public MemberPublicDto toPublicDto(Member member) {

        Address address = member.getAddress();

        AddressDto addressDto = new AddressDto(
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );

        return new MemberPublicDto(
                member.getFirstName(),
                member.getLastName(),
                addressDto,
                member.getPhone(),
                member.getEmail()
        );
    }

    public Member toEntity(MemberDto dto, Address address) {
        return new Member(
                dto.firstName(),
                dto.lastName(),
                address,
                dto.email(),
                dto.phone(),
                dto.dateOfBirth()
        );
    }

    public void updateMember(MemberDto dto, Member member, Address address) {
        if (dto.firstName() != null) member.setFirstName(dto.firstName());
        if (dto.lastName() != null) member.setLastName(dto.lastName());
        if (dto.email() != null) member.setEmail(dto.email());
        if (dto.phone() != null) member.setPhone(dto.phone());
        if (dto.dateOfBirth() != null) member.setDateOfBirth(dto.dateOfBirth());
        if (address != null) member.setAddress(address);
    }
}