package org.zerock.realboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.realboard.entity.Member;

public interface MemberRepository extends JpaRepository<Member,String> {
}
