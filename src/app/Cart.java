package app;

import app.product.Product;
import app.product.ProductRepository;
import app.product.subproduct.BurgerSet;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

import java.util.Scanner;

public class Cart {

    //1. 메뉴들의 객체에서 특정 상품을 뽑아 장바구니로 담기 위한 Product[]타입 배열 선언
    private Product[] items = new Product[0]; //사용자가 채워야 하니까 초기값은 0
    private Scanner scanner = new Scanner(System.in); //사용자에게 입력받기 위한 scanner 객체 선언


private ProductRepository productRepository; //[1], (4-1)
public Cart(ProductRepository productRepository){ this.productRepository = productRepository; }
//ㄴ> productRepo.id <<(사용자가 장바구니에서 상품을 고를 때, 어떤 상품을 골랐는지 값을 얻기 위한 productRepo 생성자 추가)


private Menu menu; //[1], (4-3)
public Cart(ProductRepository productRepository, Menu menu){
    this.productRepository = productRepository;
    this.menu = menu;
}
//ㄴ> Cart의 composeSet()에서 부분적으로 사이드와 음료 메뉴를 봐야하기 때문에, app.Menu 인스턴스를 필드로 정의해야 함
//ㄴ> productRepo에서, 사용자가 입력하여 app.Menu 클래스에 담은 인스턴스의 정보
    //ㄴ> 즉, 사용자가 골라서 메뉴로 담은 정보와, 해당 상품의 상세 정보를 위해 productRepo와 menu를 들고옴




//============================================================
    //[1] 장바구니: 사용자가 장바구니에 최종적으로 담은 상품들을 요약하여 출력하는 넘버

    //[1], 메소드(1): 장바구니 담기 기능의 안내사항 !! (거의 .. 꾸미기)
    public void printCart(){
        System.out.println("🧺 장바구니");
        System.out.println("-".repeat(60));
        printCartItemDetails();
        System.out.println("-".repeat(60));
        //System.out.printf("합계 : %d원\n", 금액 합계);

        System.out.println("이전으로 돌아가려면 엔터를 누르세요. ");
        scanner.nextLine();
    }





 //===========================================================
    //[1], 메소드(2): 사용자가 장바구니에 어떤 상품을 담았는지 최종적으로 요약해주는 메소드
    protected void printCartItemDetails(){

        for(Product product: items){ //product 변수가 사용자가 선택한 items의 배열을 동다가
            if(product instanceof BurgerSet){ //특정 인덱스의 데이터가 BurgerSet의 다운캐스팅이 가능한 객체라면), 그니까 버거세트라면 !!
                BurgerSet burgerSet = (BurgerSet) product;
                System.out.printf(
                        "   %s %6d원 (%s(케첩 %d개), %s(빨대 %s))\n",
                        product.getName(),
                        product.getPrice(),
                        burgerSet.getSide().getName(),
                        burgerSet.getSide().getKetchup(),
                        burgerSet.getDrink().getName(),
                        burgerSet.getDrink().hasStraw() ? "있음" : "없음"
                );
            } else if (product instanceof Hamburger) {
                System.out.printf(
                        "   %-8s %6d원 (단품)\n",
                        product.getName(),
                        product.getPrice()
                );
            } else if (product instanceof Side) {
                System.out.printf(
                        "  %-8s %6d원 (케첩 %d개)\n",
                        product.getName(),
                        product.getPrice(),
                        ((Side) product).getKetchup()  //다운캐스팅
                );
            }
            else if (product instanceof Drink) {
                System.out.printf(
                        "  %-8s %6d원 (빨대 %s)\n",
                        product.getName(),
                        product.getPrice(),
                        ((Drink) product).hasStraw() ? "있음" : "없음"  //다운캐스팅
                );
            }
            }
        //다운캐스팅 이유: 각각의 상품 객체들은 Product 타입으로 업캐스팅 되어있는 상태
        //ㄴ> 업캐스팅 되어있는 상태에서는 상위 클래스의 필드와 메서드로만 접근이 가능함
        //ㄴ> 하위 클래스의 필드와 메서드로 접근하기위해 다운 캐스팅 적용
        //다운캐스팅 조건: 업캐스팅을 진행한 하위 클래스는(상위클래스타입이 된) 다시 하위클래스로 다운 캐스팅 가능
        }




 //===========================================================
        //[1], 메소드(4). /사용자가 상품을 선택하고 나서,! 실행되는 메소드
        // chooseOption(), composeSet()을 내부에 선언해주어야 함 (addToCart())
        public void addToCart(int productId){ //productId: 사용자가 입력해서 장바구니에 담겠다고 선언한 변수

    //------------------    //------------------    //------------------
        //[1], (4-1).productId를 통해 productId를 id로 가지는 상품 찾기
        Product product = productRepository.findById(productId); //(*)
        //ㄴ> 장바구니에서 고른 상품의 정보를 담을 Product 타입의 변수 선언 (어떤 걸 고를지 몰라서 상위클래스 타입)

        //for(Product element: productRepository.getAllProducts()){ //productRepo의 getter를 통해 전체 상품의 객체가 돌고돌고
        //    if(element.getId() == productId) product = element; //돌다가 사용자가 입력해서 넘긴 productId를 만나면, 해당 객체를 product에 담기
        //}
        // ㄴ>위 3줄의 코드를 ProductRepo의 findById로 대체



    //------------------    //------------------    //------------------
        //[1], (4-2). 상품 옵션 설정 [chooseOption() 내부호출]
        chooseOption(product);
    //------------------    //------------------    //------------------
        //[1], (4-3). 사용자가 고른 옵션이 (product가 Hamburger의 인스턴스이고, isBurgerSet == true)aus, , composeSet() 내부호출
            if (product instanceof Hamburger) {
                Hamburger hamburger = (Hamburger) product;
                if (hamburger.isBurgerSet()) product = composeSet(hamburger);
            }


            //(*) 해결: 깊은 복사를 위한 객체 추가
            Product newProduct;
            if (product instanceof Hamburger) newProduct = new Hamburger((Hamburger) product);
            else if (product instanceof Side) newProduct = new Side((Side) product);
            else if (product instanceof Drink) newProduct = new Drink((Drink) product);
                //else newProduct = product;
            else newProduct = new BurgerSet((BurgerSet) product);
    //------------------    //------------------    //------------------
        //[1], (4-4).  items(사용자가 담은 장바구니에) product(순회를 돌려 조건에 걸린 상품)을 추가
            Product[] newItems = new Product[items.length + 1];
            System.arraycopy(items, 0, newItems, 0, items.length);
            newItems[newItems.length - 1] = newProduct;
            items = newItems;


            System.out.printf("[📣] %s를(을) 장바구니에 담았습니다.\n", product.getName());
        }


//(*): 해당 코드는 얕은 복사를 하기 때문에, 같은 상품을 두 번 주문하는데 옵션이 다르면 곤란함
    //ㄴ> 얕은 복사는 한 객체의 참조값만 복사하기 때문에, 결론적으로 다른 옵션의 같은 메뉴를 하나 더 고르면 !! -> 한 객체의 변동으로 여겨짐
    //ㄴ> 우리가 원하는 건 같은 메뉴를 2개 시키면, 다른 참조값의 새로운 객체(동일객체)가 생기는 것



//===========================================================

    //내부 호출 함수: (4-2) 상품 옵션 설정
    private void chooseOption(Product product){
    String input;

    if(product instanceof Hamburger){ //product가 hamburger의 인스턴스라면
        System.out.printf(
                "단품으로 주문하시겠어요? (1)_단품(%d원) (2)_세트(%d원)\n",
                product.getPrice(),
                ((Hamburger) product).getBurgerSetPrice() //다운캐스팅한 가격 출력 (현재는 Product타입이기에)
        );
        input = scanner.nextLine();
        if(input.equals("2")) ((Hamburger) product).setIsBurgerSet(true);
    }
    else if (product instanceof Side) {
        System.out.println("케첩은 몇개가 필요하신가요?");
        input = scanner.nextLine();
        ((Side) product).setKetchup(Integer.parseInt(input));
    }
    else if (product instanceof Drink) {
        System.out.println("빨대가 필요하신가요? (1)_예 (2)_아니오");
        input = scanner.nextLine();
        if (input.equals("2")) ((Drink) product).setHasStraw(false);
    }
    }



    //내부 호출 함수: (4-3). 세트 구성, 햄버거 세트를 구성해주는 역할
    //1) 각각의 메뉴(사이드와 음료수만)를 보여주고, 메뉴를 입력받음 > 사이드 메뉴를 부분적으로 보여주어야
    //2) 사용자가 입력한 메뉴의 옵션을 보여주고, 옵션을 입력받음
    //3) 사용자가 선택한 대로 세트를 구성하여 햄버거 세트를 반환
    private BurgerSet composeSet(Hamburger hamburger){

    System.out.println("사이드를 골라골라");
        menu.printSides(false); //사이드 메뉴들을 출력
        String sideId = scanner.nextLine(); //사용자의 사이드 주문 입력 받기


        Side side = (Side) productRepository.findById(Integer.parseInt(sideId));
        //ㄴ> 사용자가 입력한 상품을 검색

        //얕은 복사를 하고나서의 Side 인스턴스 생성)
        //chooseOption(side); //사용자가 고른 사이드의 옵션 선택

        //사이드, 음료 분리를 위해 깊은 복사를 한 후 깊은복사를 통한 인스턴스 생성)
        Side newSide = new Side(side);
        chooseOption(newSide);



    System.out.println("음료를 골라골라");
        menu.printDrinks(false); //printPrice인자 추가, boolean
        String drinkId = scanner.nextLine(); //사용자의 입력을 String으로 받았기때문에, findById에서 int 타입으로 형변환

        Drink drink = (Drink)productRepository.findById(Integer.parseInt(drinkId));
        //ㄴ> productRepo를 에서 가져온 음료 객체를 다운캐스팅 하여 원래 클래스대로 상품 정보에 담기

        //얕은 복사를 하고나서의 Drink 인스턴스 생성)
        //chooseOption(drink);

        //사이드, 음료 분리를 위해 깊은 복사를 한 후 깊은복사를 통한 인스턴스 생성)
        Drink newDrink = new Drink(drink);
        chooseOption(newDrink);

    //세트구성
    String name = hamburger.getName() + "세트";
    int price = hamburger.getBurgerSetPrice();
    int kcal = hamburger.getKcal() + side.getKcal() + drink.getKcal();

    return new BurgerSet(name, price, kcal, hamburger, newSide, newDrink);
    }



    //외부 호출함수(금액 합계 계산). 배열을 순회하면서 금액의 합계를 구하는 메소드 //얘도 내부 ..호출 함수 아니니?
    protected int calculateTotalPrice(){
        int totalPrice = 0;
        for(Product product : items){
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }


}