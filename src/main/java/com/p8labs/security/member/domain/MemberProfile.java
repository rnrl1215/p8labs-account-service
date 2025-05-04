package com.p8labs.security.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "member_profile")
public class MemberProfile {
    @Id
    @Column(name = "member_id", nullable = false)
    private Long id;

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

}