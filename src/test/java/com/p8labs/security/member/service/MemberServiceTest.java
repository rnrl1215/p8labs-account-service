package com.p8labs.security.member.service;

import com.p8labs.security.member.domain.MemberAuthEntity;
import com.p8labs.security.member.domain.MemberEntity;
import com.p8labs.security.member.domain.MemberProfileEntity;
import com.p8labs.security.member.dto.MemberRegisterDto;
import com.p8labs.security.member.enums.AuthorityType;
import com.p8labs.security.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void 회원가입_멤버엔티티_생성_테스트() {
        MemberRegisterDto dto = new MemberRegisterDto("test",
                "password",
                "nickname",
                "email@email.com");

        MemberEntity registerMemberEntity = memberService.createRegisterMemberEntity(dto);

        String memberId = registerMemberEntity.getMemberId();
        int lengthOfPassword = registerMemberEntity.getPassword().length();
        Assertions.assertThat(memberId).isEqualTo("test");
        Assertions.assertThat(lengthOfPassword).isEqualTo(60);


        MemberProfileEntity memberProfile = registerMemberEntity.getMemberProfile();
        MemberEntity memberOfProfile = memberProfile.getMember();
        String nickname = memberProfile.getNickname();
        String email = memberProfile.getEmail();

        Assertions.assertThat(memberOfProfile).isEqualTo(registerMemberEntity);
        Assertions.assertThat(nickname).isEqualTo("nickname");
        Assertions.assertThat(email).isEqualTo("email@email.com");

        List<MemberAuthEntity> authorities = registerMemberEntity.getAuthorities();
        MemberAuthEntity memberAuthEntity = authorities.getFirst();
        MemberEntity memberOfAuth = memberAuthEntity.getMember();
        AuthorityType authority = memberAuthEntity.getAuthority();

        Assertions.assertThat(memberOfAuth).isEqualTo(registerMemberEntity);
        Assertions.assertThat(authority).isEqualTo(AuthorityType.ROLE_USER);
    }

    @Test
    void 패스워드_일치_테스트() {
        String password = "$2a$10$iO9AQGnszxRwWcWb/GOkseCfm6R0sWWdOSUbR8rXxXdI91JRBbjEi";
        String inputPassword = "password";
        memberService.comparePassword(password, inputPassword);
    }
}