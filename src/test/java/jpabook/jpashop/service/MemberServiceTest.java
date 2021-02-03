package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)    //junit실행시 스프링과 엮어서 실행하겠다~!
@SpringBootTest                 //스프링컨테이너안에서 테스트를 하기위함/ 안쓰면 @Autowired사용 불가
@Transactional                  //test코드에 transactional이 있으면 롤백(생성된값이 테스트가끝나면 사라지는것)이 기본값!  // test가아닌 service/repository계층에서 쓸경우엔 rollback되지않음..
public class MemberServiceTest {          //요구사항 : 회원가입이되어야하고, 중복자가 있다면 예외발생

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em ;

    @Test
//    @Rollback(value = false)   // rollback true가 기본값! 트랜젝션이 있으면 true가 기본값(생략가능)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("lee");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)  //발생한 예외가 IllegalStateException이면 성공!
    public void 중복회원예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("lee");

        Member member2 = new Member();
        member2.setName("lee");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생해야 합니다.");
    }


}