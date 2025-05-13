package com.p8labs.security.member.domain;

import com.p8labs.security.member.enums.AuthorityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member_auth")
public class MemberAuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "member_id",  nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity member;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    private AuthorityType authority;

    public MemberAuthEntity(AuthorityType authority) {
        this.authority = authority;
    }

    public void updateMember(MemberEntity member) {
        this.member = member;
    }
}