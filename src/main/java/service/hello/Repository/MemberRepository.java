package service.hello.Repository;

import service.hello.domain.Member;

import java.util.List;

public interface MemberRepository {

    public Member save(Member member);

    public Member findById(Long id);

    public List<Member> findAll();

    public void clearStore();

    public void updateMember(Long id, Member member);


}
