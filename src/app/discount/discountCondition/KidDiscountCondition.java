package app.discount.discountCondition;

import app.discount.discountPolicy.DiscountPolicy;
import app.discount.discountPolicy.FixedAmountDiscountPolicy;

import java.util.Scanner;

//할인 조건(2): 청소년 할인
//청소년 할인은 고정 금약 할인 정책을 적용 -> fixedAmountDiscountPolicy 필드로 정의해야 함
public class KidDiscountCondition implements DiscountCondition {

    private  boolean isSatisfied; //조건이 충족하는지?

    //private FixedAmountDiscountPolicy fixedAmountDiscountPolicy = new FixedAmountDiscountPolicy(500);
    ////ㄴ> 인터페이스 의존을 위해 삭제

    public boolean isSatisfied(){ return isSatisfied; }

    private void setSatisfied(boolean satisfied){ isSatisfied = satisfied; }


    private DiscountPolicy discountPolicy;
    public KidDiscountCondition(DiscountPolicy discountPolicy){
        this.discountPolicy =discountPolicy;
    }

    //캡슐화를 위한 setter의 접근제한자
    public void checkDiscountCondition(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("나이 알랴조.");
        int input = Integer.parseInt(scanner.nextLine());

        setSatisfied(input < 20); //20살 미만이면 할인금액 적용 대상자 체크
    }

    //할인 금액 적용하기
    public int applyDiscount(int price){
        //return fixedAmountDiscountPolicy.calculateDiscountedPrice(price);
        return discountPolicy.calculateDiscountedPrice(price);
    }



}
