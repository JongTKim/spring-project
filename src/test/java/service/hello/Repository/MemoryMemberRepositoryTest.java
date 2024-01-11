package service.hello.Repository;

import org.apache.catalina.core.ApplicationContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.hello.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member("KIM", 21, "SEOUL");
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId());
        assertThat(member.getId()).isEqualTo(findMember.getId());
    }

    @Test
    void findAll() {
        Member member1 = new Member("Member1", 20, "SEOUL");
        Member member2 = new Member("Member2", 20, "SEOUL");
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> allMembers = memberRepository.findAll();
        assertThat(allMembers).contains(member1,member2);
    }

    @Test
    void updateMember() {
        Member member = new Member("Member1", 20, "SEOUL");
        memberRepository.save(member);
        Member update = new Member("Member2", 22, "BUSAN");
        memberRepository.updateMember(member.getId(),update);
        assertThat(member.getName()).isEqualTo(update.getName());
        assertThat(member.getAge()).isEqualTo(update.getAge());
        assertThat(member.getAddress()).isEqualTo(update.getAddress());


    }
}