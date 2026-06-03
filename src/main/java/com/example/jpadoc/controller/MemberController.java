package com.example.jpadoc.controller;

import com.example.jpadoc.common.response.ApiResponse;
import com.example.jpadoc.dto.member.MemberCreateRequest;
import com.example.jpadoc.dto.member.MemberResponse;
import com.example.jpadoc.dto.member.MemberUpdateRequest;
import com.example.jpadoc.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ApiResponse<Page<MemberResponse>> getAll(Pageable pageable) {
        return ApiResponse.ok(memberService.findAll(pageable));
    }

    @GetMapping("/active")
    public ApiResponse<Page<MemberResponse>> getAllActive(Pageable pageable) {
        return ApiResponse.ok(memberService.findAllActive(pageable));
    }

    @GetMapping("/{id}")
    public ApiResponse<MemberResponse> getById(@PathVariable Long id) {
        return ApiResponse.ok(memberService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MemberResponse> create(@RequestBody @Valid MemberCreateRequest request) {
        return ApiResponse.ok(memberService.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<MemberResponse> update(@PathVariable Long id,
                                               @RequestBody @Valid MemberUpdateRequest request) {
        return ApiResponse.ok(memberService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        memberService.delete(id);
        return ApiResponse.ok();
    }
}
