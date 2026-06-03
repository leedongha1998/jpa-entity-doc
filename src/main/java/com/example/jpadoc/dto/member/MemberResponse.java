package com.example.jpadoc.dto.member;

import com.example.jpadoc.entity.Member;
import com.example.jpadoc.entity.MemberStatus;
import com.example.jpadoc.entity.Role;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemberResponse {

    private Long id;
    private String email;
    private String name;
    private String phone;
    private Role role;
    private MemberStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .phone(member.getPhone())
                .role(member.getRole())
                .status(member.getStatus())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }
}
