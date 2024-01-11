package service.hello.Repository;

import org.springframework.stereotype.Repository;
import service.hello.domain.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore() {
        store.clear();
    }

    @Override
    public void updateMember(Long id, Member member) {
        Member findMember = findById(id);
        findMember.setName(member.getName());
        findMember.setAge(member.getAge());
        findMember.setAddress(member.getAddress());
    }

}
