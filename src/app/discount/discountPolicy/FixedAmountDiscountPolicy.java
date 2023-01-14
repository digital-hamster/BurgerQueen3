package app.discount.discountPolicy;

//고정 금액 할인 클래스
public class FixedAmountDiscountPolicy implements DiscountPolicy {

    //할인금액
    private int discountAmount;

    //생성자
    public FixedAmountDiscountPolicy(int discountAmount){
        this.discountAmount = discountAmount;
    }

    //할인 적용 금액을 계산하는 메소드
    public int calculateDiscountedPrice(int price){ return price - discountAmount; }
}
