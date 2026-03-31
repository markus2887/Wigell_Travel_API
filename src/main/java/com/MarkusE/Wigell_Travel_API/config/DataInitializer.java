package com.MarkusE.Wigell_Travel_API.config;

import com.MarkusE.Wigell_Travel_API.entity.Address;
import com.MarkusE.Wigell_Travel_API.entity.Member;
import com.MarkusE.Wigell_Travel_API.repo.AddressRepository;
import com.MarkusE.Wigell_Travel_API.repo.AppUserRepository;
import com.MarkusE.Wigell_Travel_API.repo.MemberRepository;
import com.MarkusE.Wigell_Travel_API.security.AppUser;
import com.MarkusE.Wigell_Travel_API.security.Role;
import com.MarkusE.Wigell_Travel_API.service.MemberService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(MemberRepository memberRepository, MemberService memberService, AddressRepository addressRepository, AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {


        Address address = addressRepository.save(new Address("Granvägen 28", "432 33", "Varberg"));
        Address addressTwo = addressRepository.save(new Address("Stengatan 10", "432 45", "Göteborg"));

        memberRepository.save(new Member("Markus", "Emanuelsson", address, "markus.emanuelsson@gmail.com", "0768444036", LocalDate.of(1985, 9, 11)));
        memberRepository.save(new Member("Adam", "Olsson", address, "adam.olsson@gmail.com", "076345323", LocalDate.of(1988, 5, 22)));
        memberRepository.save(new Member("Kalle", "Andersson", addressTwo, "kalle.andersson@gmail.com", "073543534", LocalDate.of(1999, 12, 4)));
        memberRepository.save(new Member("Sara", "Eliasson", addressTwo, "sara.eliasson@gmail.com", "0705345344", LocalDate.of(1987, 2, 16)));
        memberRepository.save(new Member("Thomas", "Nilsson", addressTwo, "thomas.nilsson@gmail.com", "0734534541", LocalDate.of(1983, 6, 15)));

    }

    @PostConstruct
    public void init() {
        AppUser admin = new AppUser(
                "admin",
                passwordEncoder.encode("admin"),
                Role.ADMIN
        );

        AppUser user = new AppUser(
                "user",
                passwordEncoder.encode("user"),
                Role.USER
        );

        appUserRepository.save(admin);
        appUserRepository.save(user);
    }
}