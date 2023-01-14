package app;

import app.discount.Discount;
import app.discount.discountCondition.CozDiscountCondition;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountPolicy.FixedAmountDiscountPolicy;
import app.discount.discountPolicy.FixedRateDiscountPolicy;
import app.product.Product;

import app.product.ProductRepository;

import java.util.Scanner;


public class OrderApp {

    private ProductRepository productRepository;
    private Menu menu;
    private Cart cart;
    private Order order;

    public OrderApp(ProductRepository productRepository, Menu menu, Cart cart, Order order) {
        this.productRepository = productRepository;
        this.menu = menu;
        this.cart = cart;
        this.order = order;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);



        //외부로부터 객체를 주입받기 위해 Order생성
        //Order order = new Order(cart, new DiscountCondition[]{
        //   new CozDiscountCondition(new FixedRateDiscountPolicy(10)),
        //   new KidDiscountCondition(new FixedAmountDiscountPolicy(500))
        //});
        //ㄴ> discount 분리로 인한 생성자 수정

        Order order = new Order(cart, new Discount(
                new DiscountCondition[]{
                new CozDiscountCondition(new FixedRateDiscountPolicy(10)),
                new KidDiscountCondition(new FixedAmountDiscountPolicy(500))
                }
        ));

        System.out.println("🍔 BurgerQueen app.Order Service");


        //[1].사용자로부터 메뉴를 입력받기 위해, 전체 메뉴를 조회하는 게 제일 처음으로 돌아야 함
        //ProductRepository productRepository = new ProductRepository(); //AppConfigurer로 인한 삭제
        //ㄴ>(1-1) 각 상품의 상게 정보가 들은 productRepo 선언
        // 상세 메뉴를 보기 위한 인스턴스 생성 (상세 상품의 설계도)

       // Product[] products = productRepository.getAllProducts();
        //ㄴ>(1-2) productRepo에 담겨있는 필드를 전부 조회하기 위해 productRepo의 getter로 모든 상품 정보의 값을 리턴받고,
        //ㄴ> 이를 Product[] 타입의(상위 클래스 타입에 다른 타입이 들어가야 하니까) / products 변수에 할당
        //ㄴ> 상세 상품의 정보를 설계도로만 작성했다면 이걸 실체로 만들어서 배열에 담아준 것임

        //Menu menu = new Menu(products);
        //ㄴ>(1-3) Menu의 생성자로 Product[] 타입의 객체들의 배열이 들어가는데,
        //ㄴ> 각 Drink, hamburger, side의 필드의 생성자가 app.Menu 클래스에 존재하고 !!!!
        //ㄴ> menu 객체를 생성하면서, app.Menu 클래스 안의 필드의 생성자가 호출, 이가 menu에 담김
        //ㄴ> 상세 상품의 실체들이 app.Menu 객체에 담길 수 있도록





        //[2]. 장바구니 기능을 위한 객체 추가
        //Cart cart = new Cart(productRepository, menu);
        // ㄴ> AppConfigurer로 인한 삭제

    while(true){
    //[2] 메뉴 출력
        menu.printMenu();
        //사용자로부터 메뉴를 입력받음
        String input = scanner.nextLine();
        
        if(input.equals("+")){ //사용자가 입력한 id값이 양수라면 (메뉴는 전부 양수의 id로 할당)
            order.makeOrder();//주문 내역 출력
            break;
        }
        else{
            int menuNumber = Integer.parseInt(input); //사용자의 입력(Scanner)는 string으로 받아지기 때문에, 형변환(int)
            
            if(menuNumber==0) cart.printCart();//더이상 입력한 숫자가 없다면 카트 출력
            else if (1 <= menuNumber && menuNumber <= productRepository.getAllProducts().length) {
                cart.addToCart(menuNumber);
            }
        }


        }
    }
}
