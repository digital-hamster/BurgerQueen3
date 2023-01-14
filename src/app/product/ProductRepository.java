package app.product;

import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

public class ProductRepository {

    //배열은 동일한 타입의 데이터만 담을 수 있는데, 배열에 넣어야 할 타입이 다르다면?
    //ㄴ> 다형성을 활용함 / 상위 클래스 타입의 참조 변수로 하위 클래스 타입의 객체를 참조하는 것

    //각각의 하위 클래스들은 상속받은 상위 클래스 타입의 변수에 할당할 수 있음
    //ㄴ> 하위 클래스를 상위 클래스 타입으로 생성자를 호출하여 객체를 만드는 것: 업캐스팅

    //업캐스팅을 이용해 각각의 하위 클래스를 <상위 클래스 타입>의 객체로 만들어 같은 배열에 담을 것임

    Product[] products = {
            new Hamburger(1, "새우버거", 3500, 500, false, 4500),
            new Hamburger(2, "치킨버거", 4000, 600, false, 5000),
            new Side(3, "감자튀김", 1000, 300, 1),
            new Side(4, "어니언링", 1000, 300, 1),
            new Drink(5, "코카콜라", 1000, 200, true),
            new Drink(6, "제로콜라", 1000, 0, true),
    };

    //상품 정보에 접근할 때 products에 접근할 수 있는 메소드를 해당 클래스에 만듦
    //해당 클래스의 메소드를 활용하여 products 필드에 접근


    public Product findById(int productId){
        for(Product product : products){
            if(product.getId() == productId){ return product; }
        }
        return null;
    }
    //ㄴ> 사용자가 넘긴 productId를 받아와서,
    //ㄴ> 전체 productRepo의 상품들을 돌다가, 해당 아이디가 맞으면 그 상품을 리턴





    //getter1) 사용자가 전체 상품을 조회해서 선택할 수 있도록 getter
    public Product[] getAllProducts(){ return products; }

}
