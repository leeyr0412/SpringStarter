package com.study.springStarter.member.controller;

import com.study.springStarter.common.response.BaseResponse;
import com.study.springStarter.member.controller.request.LoginRequest;
import com.study.springStarter.member.controller.request.MemberRegisterRequest;
import com.study.springStarter.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public BaseResponse<?> signup(@RequestBody @Valid MemberRegisterRequest request) {
        return BaseResponse.ok(memberService.signUp(request));
    }

    @PostMapping("/login")
    public BaseResponse<?> login(@RequestBody @Valid LoginRequest request) {
        return BaseResponse.ok(memberService.login(request));
    }
}
