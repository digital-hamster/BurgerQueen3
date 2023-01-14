package app.discount.discountCondition;

import app.discount.discountPolicy.DiscountPolicy;
import app.discount.discountPolicy.FixedRateDiscountPolicy;

import java.util.Scanner;

//할인조건(1) 코드스테이츠 수강생 할인
public class CozDiscountCondition implements DiscountCondition {

    private boolean isSatisfied; //코드스테이츠 수강상 조건이 만족하는지 boolean
    public boolean isSatisfied(){ return isSatisfied; }

    //private FixedRateDiscountPolicy fixedRateDiscountPolicy = new FixedRateDiscountPolicy(10);
    //ㄴ> 할인 정책은 할인 조건에 종속되어야 함 (코드스테이츠 수강생: 고정 비율 할인)
    //ㄴ> 할인 조건을 구현하는 클래스에 할인 정책을 필드로 정의해야 하기 때문에, 10퍼센트 할인을 인자로 넘겨서 인스턴스화하여 할당
//ㄴ> 인터페이스 DiscountPolicy를 활용하여 해당 인터페이스에 읜존하도록 해야함

    private DiscountPolicy discountPolicy; //인터페이스
    public CozDiscountCondition(DiscountPolicy discountPolicy){ this.discountPolicy = discountPolicy; }


    private void setSatisfied(boolean satisfied){
        isSatisfied = satisfied;
        //ㄴ> satisfied가 true로 들어오면, true의 조건을 isSatisfied의 필드에 true로 넣어줌
    }

    public void checkDiscountCondition(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("코드스테이츠 수강생이세뇨? (1)_예 (2)_아뇨?");
        String input = scanner.nextLine(); //사용자의 입력을 다음 콘솔로 받음

        //사용자의 입력에 따라
        if(input.equals("1")) setSatisfied(true);
        else if (input.equals("2")) {
            setSatisfied(false);
        }
    }

    //할인을 적용하는 메소드 => Fixed에서 정의한 calDisPrice의 결과값을 그대로 리턴해주기
    public int applyDiscount(int price){
        //return fixedRateDiscountPolicy.calculateDiscountedPrice(price);
        //ㄴ> 인터페이스에 의존하도록 해야해서 삭제

        return  discountPolicy.calculateDiscountedPrice(price);
    }
}
