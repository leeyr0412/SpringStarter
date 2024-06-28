package com.study.springStarter.member.service;

import com.study.springStarter.common.authority.JwtTokenProvider;
import com.study.springStarter.common.authority.TokenInfo;
import com.study.springStarter.common.exception.InvalidInputException;
import com.study.springStarter.common.status.Role;
import com.study.springStarter.member.controller.request.LoginRequest;
import com.study.springStarter.member.controller.request.MemberRegisterRequest;
import com.study.springStarter.member.entity.Member;
import com.study.springStarter.member.entity.MemberRole;
import com.study.springStarter.member.repository.MemberRepository;
import com.study.springStarter.member.repository.MemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 회원 가입
     */
    public String signUp(MemberRegisterRequest request) {
        if (memberRepository.findByLoginId(request.getLoginId()).isPresent()) {
            throw new InvalidInputException("loginId", "중복된 ID 입니다.");
        }

        Member member = request.toEntity(passwordEncoder.encode(request.getPassword()));

        memberRepository.save(member);
        MemberRole memberRole = MemberRole.builder()
                .role(Role.MEMBER)
                .member(member)
                .build();
        memberRoleRepository.save(memberRole);

        return "가입되었습니다.";
    }

    /**
     * 로그인
     */
    public TokenInfo login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtTokenProvider.createToken(authentication);
    }
}
