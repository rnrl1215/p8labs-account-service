package com.p8labs.security.member.controller;


import com.p8labs.common.dto.CommonResponse;
import com.p8labs.security.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/members")
public class MemberCredintialController {
    private final MemberService memberService;

    @DeleteMapping("/{memberId}")
    public ResponseEntity<CommonResponse> deleteMember(
            @PathVariable String memberId
    ) {
        memberService.withdrawMember(memberId);
        return ResponseEntity.ok(CommonResponse.createSuccess());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{memberId}")
    public ResponseEntity<CommonResponse> getMember(
            Authentication authentication,
            @PathVariable String memberId
    ) {
        String name = authentication.getName();
        memberService.withdrawMember(memberId);
        return ResponseEntity.ok(CommonResponse.createSuccess());
    }
}