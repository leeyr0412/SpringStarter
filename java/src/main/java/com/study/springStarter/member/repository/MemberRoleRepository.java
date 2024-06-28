package com.study.springStarter.member.repository;

import com.study.springStarter.member.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {
}
