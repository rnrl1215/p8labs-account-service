package com.p8labs.security.member.controller;

import com.p8labs.security.common.dto.CommonDataResponse;
import com.p8labs.security.common.dto.CommonResponse;
import com.p8labs.security.member.dto.MemberDto;
import com.p8labs.security.member.dto.MemberLoginResponse;
import com.p8labs.security.member.dto.MemberRegisterDto;
import com.p8labs.security.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.source.internal.hbm.XmlElementMetadata;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> registerMember(
           @Valid @RequestBody MemberRegisterDto member) {
        memberService.registerMember(member);
        return ResponseEntity.ok(CommonResponse.createSuccess());
    }

    @PostMapping("/login")
    public ResponseEntity<CommonDataResponse<MemberLoginResponse>> loginMember(
            @Valid MemberDto member
    ) {
        String jwtToken = memberService.login(member.getMemberId(), member.getPassword());
        MemberLoginResponse loginResponse = new MemberLoginResponse(jwtToken);
        return ResponseEntity.ok(new CommonDataResponse<>(loginResponse));
    }

    @DeleteMapping
    public ResponseEntity<CommonResponse> deleteMember(
            @Valid MemberDto member
    ) {
        memberService.withdrawMember(member.getMemberId());
        return ResponseEntity.ok(CommonResponse.createSuccess());
    }
}