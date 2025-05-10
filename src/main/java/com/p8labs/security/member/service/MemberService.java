package com.p8labs.security.member.service;

import com.p8labs.security.common.dto.UserPasswordDto;
import com.p8labs.security.common.enums.CommonException;
import com.p8labs.security.common.exception.GlobalBusinessException;
import com.p8labs.security.common.utils.CryptoUtils;
import com.p8labs.security.member.domain.MemberAuthEntity;
import com.p8labs.security.member.domain.MemberEntity;
import com.p8labs.security.member.domain.MemberProfileEntity;
import com.p8labs.security.member.dto.MemberRegisterDto;
import com.p8labs.security.member.enums.AuthorityType;
import com.p8labs.security.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void registerMember(MemberRegisterDto dto) {
        MemberEntity member = createRegisterMemberEntity(dto);
        memberRepository.save(member);
    }

    public String login(String memberId, String inputPassword) {
        MemberEntity memberEntity = findByMemberId(memberId);
        String foundUserPassword = memberEntity.getPassword();
        boolean isEqual = comparePassword(foundUserPassword, inputPassword);
        if (!isEqual) {
            throw new GlobalBusinessException(CommonException.Member.NOT_EQUAL_PASSWORD_EXCEPTION);
        }
        // TODO JWT 토큰 발급
        return "test";
    }

    public void withdrawMember(String memberId) {
        Optional<MemberEntity> foundMember = memberRepository.findByMemberId(memberId);
        foundMember.ifPresentOrElse(
                memberRepository::delete,
                () -> { throw new GlobalBusinessException(CommonException.Member.NOT_FOUND_EXCEPTION); }
        );
    }

    public MemberEntity createRegisterMemberEntity(MemberRegisterDto dto) {
        String memberId = dto.getMemberId();
        String password = dto.getPassword();
        String nickname = dto.getNickname();
        String email = dto.getEmail();

        MemberProfileEntity memberProfileEntity = new MemberProfileEntity(nickname, email);
        MemberAuthEntity memberAuthEntity = new MemberAuthEntity(AuthorityType.ROLE_USER);

        String encryptedPassword = CryptoUtils.createHashByBCrypt(password);

        MemberEntity member = MemberEntity.createRegisterEntity(memberId,
                encryptedPassword,
                memberProfileEntity,
                memberAuthEntity);
        return member;
    }


    private MemberEntity findByMemberId(String memberId) {
        Optional<MemberEntity> foundMember = memberRepository.findByMemberId(memberId);
        return foundMember.orElseThrow(() -> new GlobalBusinessException(CommonException.Member.NOT_FOUND_EXCEPTION));
    }

    public boolean comparePassword(String foundUserPassword, String inputPassword) {
        UserPasswordDto passwordInfo = CryptoUtils.getPasswordInfo(foundUserPassword);
        String salt = passwordInfo.getSalt();
        String hashedInputPassword = CryptoUtils.createHashByBCryptWithSalt(inputPassword, salt);
        return hashedInputPassword.equals(inputPassword);
    }
}
