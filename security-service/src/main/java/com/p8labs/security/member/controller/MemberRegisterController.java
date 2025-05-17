package com.p8labs.security.member.controller;


import com.p8labs.common.dto.CommonDataResponse;
import com.p8labs.common.dto.CommonResponse;
import com.p8labs.security.member.dto.MemberDto;
import com.p8labs.security.member.dto.MemberLoginResponse;
import com.p8labs.security.member.dto.MemberRegisterDto;
import com.p8labs.security.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pub/v1/api/members/")
public class MemberRegisterController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> registerMember(
           @Valid @RequestBody MemberRegisterDto member) {
        memberService.registerMember(member);
        return ResponseEntity.ok(CommonResponse.createSuccess());
    }

    @PostMapping("/login")
    public ResponseEntity<CommonDataResponse<MemberLoginResponse>> loginMember(
            @Valid @RequestBody MemberDto member
    ) {
        String jwtToken = memberService.login(member.getMemberId(), member.getPassword());
        MemberLoginResponse loginResponse = new MemberLoginResponse(jwtToken);
        return ResponseEntity.ok(new CommonDataResponse<>(loginResponse));
    }

    @GetMapping("/checkers")
    public ResponseEntity<CommonResponse> checkDuplication(
            @RequestParam(required = false) String memberId,
            @RequestParam(required = false) String nickname
    ) {
        if (StringUtils.hasText(memberId)) {
            memberService.checkExistId(memberId);
        }
        if (StringUtils.hasText(nickname)) {
            memberService.checkExistNickname(nickname);
        }
        return ResponseEntity.ok(CommonResponse.createSuccess());
    }
}