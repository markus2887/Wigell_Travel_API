package com.MarkusE.Wigell_Travel_API.controller;

import com.MarkusE.Wigell_Travel_API.dto.MemberPublicDto;
import com.MarkusE.Wigell_Travel_API.mapper.MemberMapper;
import com.MarkusE.Wigell_Travel_API.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mypages/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<MemberPublicDto> getPublicMembers() {
        return memberService.findAll()
                .stream()
                .map(mapper::toPublicDto)
                .toList();
    }
}
