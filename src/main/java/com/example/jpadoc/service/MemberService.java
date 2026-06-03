package com.example.jpadoc.service;

import com.example.jpadoc.common.exception.BusinessException;
import com.example.jpadoc.common.exception.ErrorCode;
import com.example.jpadoc.dto.member.MemberCreateRequest;
import com.example.jpadoc.dto.member.MemberResponse;
import com.example.jpadoc.dto.member.MemberUpdateRequest;
import com.example.jpadoc.entity.Member;
import com.example.jpadoc.entity.MemberStatus;
import com.example.jpadoc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public Page<MemberResponse> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable).map(MemberResponse::from);
    }

    public Page<MemberResponse> findAllActive(Pageable pageable) {
        return memberRepository.findAllByStatus(MemberStatus.ACTIVE, pageable).map(MemberResponse::from);
    }

    public MemberResponse findById(Long id) {
        return MemberResponse.from(getMemberOrThrow(id));
    }

    @Transactional
    public MemberResponse create(MemberCreateRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }
        Member member = Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .name(request.getName())
                .phone(request.getPhone())
                .build();
        return MemberResponse.from(memberRepository.save(member));
    }

    @Transactional
    public MemberResponse update(Long id, MemberUpdateRequest request) {
        Member member = getMemberOrThrow(id);
        member.update(request.getName(), request.getPhone());
        return MemberResponse.from(member);
    }

    @Transactional
    public void delete(Long id) {
        getMemberOrThrow(id).deactivate();
    }

    private Member getMemberOrThrow(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
