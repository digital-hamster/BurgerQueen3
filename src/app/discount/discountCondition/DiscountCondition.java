package app.discount.discountCondition;

public interface DiscountCondition {
    //외부에서 사용하는 추상 메서드로 정의하기
    void checkDiscountCondition(); //공통적으로 적용되는 메소드를 추상 메소드로 정의함
    int applyDiscount(int price);
    boolean isSatisfied();
}
