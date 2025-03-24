package hello.core.discount;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy;


    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        discountPolicy = appConfig.discountPolicy();
    }
    @Test
    @DisplayName("VIP는 10% 할인이 적용되거나 1000원 할인되어야 한다.")
    void test_yes_VIP() {
        //given
        Member member = new Member(1L,"memberVIP", Grade.VIP);
        //when
        int price = discountPolicy.discount(member, 10000);
        //then
        Assertions.assertThat(price).isEqualTo(1000);
    }
}
