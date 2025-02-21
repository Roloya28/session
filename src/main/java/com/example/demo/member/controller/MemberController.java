package com.example.demo.member.controller;

import com.example.demo.member.dto.*;
import com.example.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberSaveResponseDto> save(@RequestBody MemberSaveRequestDto dto) {
        return ResponseEntity.ok(memberService.save(dto));
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> getAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberResponseDto> getOne(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.findOne(memberId));
    }

    @PutMapping("/members/{memberId}")
    public void update(@PathVariable Long memberId, @RequestBody MemberUpdateRequestDto dto) {
        memberService.update(memberId, dto);
    }

    @DeleteMapping("/members/{memberId}")
    public void delete(@PathVariable Long memberId) {
        memberService.deleteById(memberId);
    }
}
