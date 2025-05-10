package com.p8labs.security.member.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false, length = 20)
    private String memberId;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "member")
    private MemberProfileEntity memberProfile;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MemberAuthEntity> authorities = new ArrayList<>();

    @LastModifiedDate
    @Column(name = "mod_dttm")
    private Instant modDttm;

    @CreatedDate
    @Column(name = "reg_dttm", nullable = false)
    private Instant regDttm;

    private MemberEntity(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }


    public static MemberEntity createRegisterEntity(String memberId,
                                                    String password,
                                                    MemberProfileEntity memberProfile,
                                                    MemberAuthEntity authorities) {
        MemberEntity memberEntity = new MemberEntity(memberId, password);
        memberEntity.updateProfile(memberProfile);
        memberEntity.addAuthority(authorities);
        return memberEntity;
    }

    public void updateProfile(MemberProfileEntity memberProfile) {
        memberProfile.updateMember(this);
        this.memberProfile = memberProfile;
    }

    public void addAuthority(MemberAuthEntity authority) {
        authority.updateMember(this);
        this.authorities.add(authority);
    }

    public void addAuthorities(List<MemberAuthEntity> authorities) {
        for (MemberAuthEntity authority : authorities) {
            authority.updateMember(this);
        }
        this.authorities.addAll(authorities);
    }
}