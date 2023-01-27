package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
@Configuration // 싱글톤 보장
public class AppConfig {
    // AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
    // AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결) 해준다.

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberService");
        return new MemoryMemberRepository();
    }

    // 할인 정책 구성
    // @Bean : 스프링 컨테이너에 등록
    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("call AppConfig.discountPolicy");
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

}
