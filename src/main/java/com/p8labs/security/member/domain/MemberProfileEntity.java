package com.p8labs.security.member.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "member_profile")
public class MemberProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "member_id",  nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private MemberEntity member;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nickname", nullable = false, length = 10)
    private String nickname;

    @LastModifiedDate
    @Column(name = "mod_dttm")
    private Instant modDttm;

    @CreatedDate
    @Column(name = "reg_dttm", nullable = false)
    private Instant regDttm;

    public MemberProfileEntity(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public void updateMember(MemberEntity member) {
        this.member = member;
    }
}