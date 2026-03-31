package com.MarkusE.Wigell_Travel_API.service;

import com.MarkusE.Wigell_Travel_API.entity.Address;
import com.MarkusE.Wigell_Travel_API.entity.Member;
import com.MarkusE.Wigell_Travel_API.repo.AddressRepository;
import com.MarkusE.Wigell_Travel_API.repo.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepo;
    private final AddressRepository addressRepo;

    public MemberService(MemberRepository memberRepo, AddressRepository addressRepo) {
        this.memberRepo = memberRepo;
        this.addressRepo = addressRepo;
    }

    public List<Member> findAll() {
        return memberRepo.findAll();
    }

    public Member findById(Long id) {
        return memberRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id " + id));
    }

    public Member save(Member member) {
        return memberRepo.save(member);
    }

    public void delete(Long id) {
        Member existing = findById(id); // säkerställ att medlem finns
        memberRepo.delete(existing);
    }

    public Address getAddress(Long id) {
        return addressRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id " + id));
    }

    public Address saveAddress(Address address) {
        return addressRepo.save(address);
    }
}
