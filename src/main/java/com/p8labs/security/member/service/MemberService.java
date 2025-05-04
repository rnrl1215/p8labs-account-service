package com.p8labs.security.member.service;

import com.p8labs.security.common.dto.UserPasswordDto;
import com.p8labs.security.common.enums.CommonException;
import com.p8labs.security.common.exception.GlobalBusinessException;
import com.p8labs.security.member.domain.Member;
import com.p8labs.security.member.dto.MemberRegisterDto;
import com.p8labs.security.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberRegisterDto dto) {

    }

    public void login(String memberId, String inputPassword) {
        checkPassword(memberId, inputPassword);
        // TODO JWT 토큰 발급
    }

    public void checkPassword(String memberId, String inputPassword) {
        Optional<Member> foundMember = memberRepository.findByMemberId(memberId);
        Member member = foundMember.orElseThrow(() -> new GlobalBusinessException(CommonException.Member.NOT_FOUND_EXCEPTION));
        String foundUserPassword = member.getPassword();
        boolean isEqual = comparePassword(foundUserPassword, inputPassword);
        if (!isEqual) {
            throw new GlobalBusinessException(CommonException.Member.NOT_EQUAL_PASSWORD_EXCEPTION);
        }
    }

    private static boolean comparePassword(String foundUserPassword, String inputPassword) {
        UserPasswordDto passwordInfo = CryptoService.getPasswordInfo(foundUserPassword);
        String salt = passwordInfo.getSalt();
        String hashedInputPassword = CryptoService.createHashByBCryptWithSalt(inputPassword, salt);
        return hashedInputPassword.equals(inputPassword);
    }
}
