package app.discount;

import app.discount.discountCondition.DiscountCondition;

//Order 클래스의 makeOrder()메소드에 주문만 관련한 코드만 있어야 하는데 할인과 관련된 로직이 있어서 이를 분리하기위한 클래스
public class Discount {

    private DiscountCondition[] discountConditions; //할인 조건을 배열로 받음

    public Discount(DiscountCondition[] discountConditions){
        this.discountConditions = discountConditions;
    }

    //makeOrder에 존재하던 메소드를 분리하는게 목적이기 때문에, Order에 존재하는 메서드를 참고하여 Discount의 메소드로 정의
    public int discount(int price){
        int discountPrice = price;

        for(DiscountCondition discountCondition : discountConditions){ //할인조건 배열에 할인조건이 돎
                discountCondition.checkDiscountCondition(); //할인조건에 부합하는지 체크
                if(discountCondition.isSatisfied()) discountPrice = discountCondition.applyDiscount(discountPrice);
                //ㄴ> 조건에 부합하면, discountCondition의 정책의 할인 금액을 적용
        }
        return discountPrice; //최종 할인금액을 메소드의 리턴값으로 넘김
    }
}
