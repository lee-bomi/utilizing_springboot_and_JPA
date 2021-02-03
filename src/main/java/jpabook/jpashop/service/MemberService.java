package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)    //조회만 할때는 readonly옵션을 주면 성능최적화된다.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;    //Autowired를 생성자injection에 붙이고, @RequiredArgsConstructor때문에 생성자를 생략

    //회원가입
    @Transactional          //readonly = false가 기본값! / 따로 어노테이션을 써줬으니, 회원가입메서드만 readonly = false
    public Long join(Member member) {
        validateDuplicateMember(member);    //중복회원 검증 메서드
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미존재하는 회원입니다");
        }
    }


    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
