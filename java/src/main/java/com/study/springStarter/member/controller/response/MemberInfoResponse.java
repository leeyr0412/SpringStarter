package com.study.springStarter.member.controller.response;

import com.study.springStarter.member.entity.Member;
import lombok.Data;

@Data
public class MemberInfoResponse {
    private String loginId;
    private String name;
    private String email;

    public MemberInfoResponse(Member member) {
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
