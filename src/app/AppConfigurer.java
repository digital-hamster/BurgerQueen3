package app;

import app.discount.Discount;
import app.discount.discountCondition.CozDiscountCondition;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountPolicy.FixedAmountDiscountPolicy;
import app.discount.discountPolicy.FixedRateDiscountPolicy;
import app.product.Product;
import app.product.ProductRepository;

//프로그램 동작에 필요한 모든 객체를 생성하고, 의존 관계를 맺어주는 역할을 하는 클래스를 정의
public class AppConfigurer {

    private  Cart cart = new Cart(productRepository(), menu()); //여기서 불리는 2번의 cart가 같은 cart를 참조하도록

    public ProductRepository productRepository(){
        return new ProductRepository();
    }

    public Menu menu(){
        return  new Menu((productRepository().getAllProducts()));
    }

    public Cart cart(){

        //return new Cart(productRepository(), menu());
        //ㄴ> 새로운 cart를 만들면 여기서 불리면 2번의 cart가 서로 다른 주소를 참조하여 장바구니가 채워지지 않음
        return cart; //위에서 만든 객체로 쭉 돌릴 수 있도록,
        //ㄴ> 한 주문 프로그램이 돌 때, cart는 2개 이상이 절대 절대 !! 필요하지 않고 얘만 유일해야 하니까 ~
    }

    public Discount discount(){
        return new Discount(
                new DiscountCondition[]{
                        new CozDiscountCondition(new FixedRateDiscountPolicy(10)),
                        new KidDiscountCondition(new FixedAmountDiscountPolicy(500))
                }
        );
    }

    public Order order(){
        return new Order(cart(), discount());
    }
}
