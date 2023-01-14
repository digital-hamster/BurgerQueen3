package app;

import app.discount.Discount;
import app.discount.discountCondition.CozDiscountCondition;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountPolicy.FixedAmountDiscountPolicy;
import app.discount.discountPolicy.FixedRateDiscountPolicy;

public class Order {

    //주문은 장바구니에 담긴 상품을 기반으로 이루지기 때문에 cart 객체를 통쨰로 필드로 정의
    private Cart cart;

    private Discount discount; //할인 조건이 모인 Discount선언
    public Order(Cart cart, Discount discount){
        this.cart = cart;
        this.discount = discount;
    }


    //discount 분리로 삭제
    //private DiscountCondition[] discountConditions;
    //ㄴ> 할인 조건은 여러개이기 때문에 배열로 필드 정의
    //ㄴ> 할인 조건을 담은 배열을 생성자로 외부에서 주입받을 예정
    //ㄴ> 할인과 관련한 기능은 Discount.java로 모두 옮겼음 -> 여기서 할인 조건을 받을 필요가 없으니 필드 삭제


    //discount 분리로 삭제
    //public Order(Cart cart, DiscountCondition[] discountConditions){
        //this.cart = cart;
        //this.discountConditions = discountConditions; //할인 조건을 담은 배열을 생성자로 받아서 필드로
    //}
    //s> 얘도 discount에 대한 모든 걸 분리했기 때문에 여기서 받는 생성자는 필요없어서 생성자까지 삭제

    //메서드 makeOrder(): 장바구니의 상품들을 주문하는 기능
    //ㄴ> 요구 사항에서 주문과 관련해서는 출력만 해주면 됨
    public void makeOrder(){

        //[할인 기능 적용하기](1) 메서드 checkDiscountCondition, applyDiscount 호출하기위한 객체 생성
        //CozDiscountCondition cozDiscountCondition = new CozDiscountCondition(new FixedRateDiscountPolicy(10)); //인터페이스를 정의하고 할인률 인자 (10) 추가
        //KidDiscountCondition kidDiscountCondition = new KidDiscountCondition(new FixedAmountDiscountPolicy(500));

        //[할인 기능 적용하기](2) 할인 대상자 여부 확인을 위한 메소드 호출
        //cozDiscountCondition.checkDiscountCondition();
        //kidDiscountCondition.checkDiscountCondition();
            //ㄴ> (인터페이스로 변경 전)
            //ㄴ> cozDiscountCondition와 kidDiscountCondition의 인스턴스를 생성하여 메소드를 호출함
            //ㄴ> 이걸 DiscountCondition[] 객체 배열을 순회하면서 checkDiscountCondition()를 호출하도록 변경할것임

        int totalPrice = cart.calculateTotalPrice(); //(2)의 총액합산을 위해서
        //int finalPrice = totalPrice; => discount 로의 분리를 위해 수정
        int finalPrice = discount.discount(totalPrice);
        //ㄴ> //discount를 통해 할인 조건을 검사하고, 할인을 적용

        //discount 분리로 인한 삭제
        //(인터페이스로 변경하기)
        //for(DiscountCondition discountCondition : discountConditions){
        //    discountCondition.checkDiscountCondition();
        //    if(discountCondition.isSatisfied()) finalPrice = discountCondition.applyDiscount(finalPrice);
        //}
        //ㄴ> 외부로부터의 객체 주입임






        System.out.println("[📣] 주문이 완료되었습니다. ");
        System.out.println("[📣] 주문 내역은 다음과 같습니다. ");
        System.out.println("-".repeat(60));

        //(1)상품 상세 내역 출력 -> cart 메소드 재활용
        cart.printCartItemDetails();

        System.out.println("-".repeat(60));
        //System.out.printf("금액 합계      : %d원\n", (2)금액 합계 계산); //(2): cart 메소드 재활용
        System.out.printf("금액 합계      : %d원\n", totalPrice);
    }


}
