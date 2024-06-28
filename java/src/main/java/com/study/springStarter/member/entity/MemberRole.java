package com.study.springStarter.member.entity;

import com.study.springStarter.common.status.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "id"))
    private Member member;

    @Builder
    private MemberRole(Long id, Role role, Member member) {
        this.id = id;
        this.role = role;
        this.member = member;
    }
}
