package com.p8labs.security.member.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false, length = 20)
    private String memberId;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @LastModifiedDate
    @Column(name = "mod_dttm")
    private Instant modDttm;

    @CreatedDate
    @Column(name = "reg_dttm", nullable = false)
    private Instant regDttm;
}