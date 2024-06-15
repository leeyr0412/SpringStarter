package com.study.springStarter.member.controller;

import com.study.springStarter.member.controller.request.MemberRegisterRequest;
import com.study.springStarter.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(@RequestBody MemberRegisterRequest request) {
        return memberService.signUp(request);
    }
}
