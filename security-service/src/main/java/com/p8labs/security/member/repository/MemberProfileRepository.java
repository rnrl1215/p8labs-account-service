package com.p8labs.security.member.repository;

import com.p8labs.security.member.domain.MemberProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberProfileRepository extends JpaRepository<MemberProfileEntity, Long> {
    List<MemberProfileEntity> findByNickname(String nickname);
    MemberProfileEntity findOneByNickname(String nickname);
}