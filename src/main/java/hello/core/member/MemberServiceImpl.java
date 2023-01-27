package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    // MemberServiceImpl 은 MemoryMemberRepository를 의존하지 않음
    // MemberServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지 알 수 없음
    // MemberServiceImpl 의 생성자를 통해 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정
    // MemberServiceImpl 은 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 됨

    @Autowired // 의존관계를 자동으로 주입(=ac.getBean(MemberRepository.class))
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
