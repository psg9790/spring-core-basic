package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        basePackages = "hello.core",
//        basePackageClasses = AutoAppConfig.class,
        //default: 현재 클래스의 패키지부터 탐색 (package hello.core;)
        //따라서 패키지 위치를 따로 지정하지 않고 설정 정보 클래스 위치를 프로젝트 최상단에 두자

        //AppConfig.class 참조 제외
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    //테스트 fault 안남, 수동 빈 등록이 우선권을 가진다
    //하지만 SpringBootApplication 실행시에는 fault를 발생시킴
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
