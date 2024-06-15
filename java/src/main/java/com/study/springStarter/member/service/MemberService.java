package com.study.springStarter.member.service;

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
        Optional<Member> findMember = memberRepository.findByLoginId(request.getLoginId());
        if (findMember.isPresent()) {
            return "중복된 ID 입니다.";
        }

        Member member = Member.builder()
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .email(request.getEmail())
                .build();

        memberRepository.save(member);
        return "가입되었습니다.";
    }
}
