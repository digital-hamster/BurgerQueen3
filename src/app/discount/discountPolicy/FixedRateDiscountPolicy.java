package app.discount.discountPolicy;

//고정 비율 할인 클래스
public class FixedRateDiscountPolicy implements DiscountPolicy {

    //할인비율
    private int discountRate;

    //생성자
    public FixedRateDiscountPolicy(int discountRate){
        this.discountRate = discountRate;
    }

    //할인 적용 금액을 계산하는 메소드
    public int calculateDiscountedPrice(int price){
        return price - (price*discountRate/100);
    }
}
