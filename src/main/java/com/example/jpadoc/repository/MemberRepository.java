package com.example.jpadoc.repository;

import com.example.jpadoc.entity.Member;
import com.example.jpadoc.entity.MemberStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    Page<Member> findAllByStatus(MemberStatus status, Pageable pageable);
}
