package com.study.springStarter.member.service;

import com.study.springStarter.common.exception.InvalidInputException;
import com.study.springStarter.member.controller.request.MemberRegisterRequest;
import com.study.springStarter.member.entity.Member;
import com.study.springStarter.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    public String signUp(MemberRegisterRequest request) {
        memberRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new InvalidInputException("loginId", "중복된 ID 입니다."));

        Member member = request.toEntity();

        memberRepository.save(member);

        return "가입되었습니다.";
    }
}
