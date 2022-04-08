package org.zerock.realboard.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.realboard.entity.Member;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder()
                    .email("user"+i+"@aaa.com")
                    .password("1111")
                    .name("User"+i)
                    .build();
            memberRepository.save(member);

        });

    }


}
