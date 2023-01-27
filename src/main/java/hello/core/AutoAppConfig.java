package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

import static org.springframework.context.annotation.ComponentScan.*;

import org.springframework.context.annotation.Bean;

@Configuration
@ComponentScan(basePackages = "hello.core.member", basePackageClasses = AutoAppConfig.class, excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
// basePackages : 탐색할 패키지 지정
// basePackageClasses : 탐색할 클래스의 패키지 지정, default : @ComponentScan이 붙은 설정 정보 클래스의
// 패키지가 시작 위치가 된다.
// 관례 : 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 둔다.
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
