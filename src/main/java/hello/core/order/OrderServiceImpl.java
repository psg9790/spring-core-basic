package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // java 방식은 역할과 구현을 충실하게 분리했다. 다형성 활용, 객체지향 설계 준수. 하지만
    // DIP: DiscountPolicy 인터페이스에 의존하면서 DIP를 지킨것 같은데 아님. 구현 클래스에도 의존하고 있음.
    // FixDiscountPolicy()와 RateDiscountPolicy()에 의존
    // OCP: 위와 같은 의존 때문에 클라이언트 코드를 변경해야 하기 때문에 OCP 위반

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
