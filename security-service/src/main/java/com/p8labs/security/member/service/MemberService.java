package com.p8labs.security.member.service;


import com.p8labs.common.enums.MemberException;
import com.p8labs.common.exception.GlobalBusinessException;
import com.p8labs.security.member.repository.MemberProfileRepository;
import com.p8labs.security.member.domain.MemberAuthEntity;
import com.p8labs.security.member.domain.MemberEntity;
import com.p8labs.security.member.domain.MemberProfileEntity;
import com.p8labs.security.member.dto.MemberRegisterDto;
import com.p8labs.security.member.enums.AuthorityType;
import com.p8labs.security.member.repository.MemberRepository;
import com.p8labs.security.member.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberProfileRepository memberProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerMember(MemberRegisterDto dto) {
        checkExistId(dto.getMemberId());
        checkExistNickname(dto.getNickname());
        MemberEntity member = createRegisterMemberEntity(dto);
        memberRepository.save(member);
    }

    public String login(String memberId, String inputPassword) {
        MemberEntity memberEntity = findByMemberId(memberId);
        String foundUserPassword = memberEntity.getPassword();
        boolean isEqual = comparePassword(foundUserPassword, inputPassword);
        if (!isEqual) {
            throw new GlobalBusinessException(MemberException.Member.NOT_EQUAL_PASSWORD_EXCEPTION);
        }

        List<String> authorities = memberEntity.getAuthorities()
                .stream().map(i -> i.getAuthority().name())
                .collect(Collectors.toList());

        return JwtTokenProvider.generateToken(memberEntity.getId(), memberId, authorities);
    }

    public void withdrawMember(String memberId) {
        Optional<MemberEntity> foundMember = memberRepository.findByMemberId(memberId);
        foundMember.ifPresentOrElse(
                memberRepository::delete,
                () -> { throw new GlobalBusinessException(MemberException.Member.NOT_FOUND_EXCEPTION); }
        );
    }

    public MemberEntity createRegisterMemberEntity(MemberRegisterDto dto) {
        String memberId = dto.getMemberId();
        String password = dto.getPassword();
        String nickname = dto.getNickname();
        String email = dto.getEmail();

        MemberProfileEntity memberProfileEntity = new MemberProfileEntity(nickname, email);
        MemberAuthEntity memberAuthEntity = new MemberAuthEntity(AuthorityType.ROLE_USER);

        String encryptedPassword = passwordEncoder.encode(password);
        MemberEntity member = MemberEntity.createRegisterEntity(memberId,
                encryptedPassword,
                memberProfileEntity,
                memberAuthEntity);
        return member;
    }


    private MemberEntity findByMemberId(String memberId) {
        Optional<MemberEntity> foundMember = memberRepository.findByMemberId(memberId);
        return foundMember.orElseThrow(() -> new GlobalBusinessException(MemberException.Member.NOT_FOUND_EXCEPTION));
    }

    public boolean comparePassword(String foundUserPassword, String inputPassword) {
        return passwordEncoder.matches(inputPassword, foundUserPassword);
    }


    public void checkExistId(String memberId) {
        Optional<MemberEntity> foundMember = memberRepository.findByMemberId(memberId);
        if (foundMember.isPresent()) {
            throw new GlobalBusinessException(MemberException.Member.DUPLICATION_EXCEPTION);
        }
    }

    public void checkExistNickname(String nickname) {
        MemberProfileEntity oneByNickname = memberProfileRepository.findOneByNickname(nickname);
        if (oneByNickname != null) {
            throw new GlobalBusinessException(MemberException.Member.DUPLICATION_EXCEPTION);
        }
    }
}