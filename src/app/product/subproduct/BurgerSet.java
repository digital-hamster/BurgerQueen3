package app.product.subproduct;

import app.product.Product;

public class BurgerSet extends Product {
    private Hamburger hamburger;
    private Side side;
    private Drink drink;
    //ㄴ> 상위 클래스인 Product의 하위 인스턴스인 hamburger, side, drink이기 때문에 필드로 호출이 가능한 것임

    public BurgerSet( String name, int price, int kcal, Hamburger hamburger, Side side, Drink drink){
        super( name, price, kcal);
        this.hamburger = hamburger;
        this.side = side;
        this.drink = drink;
    }

    public BurgerSet(BurgerSet burgerSet) {
        super(burgerSet.getName(), burgerSet.getPrice(), burgerSet.getKcal());
        this.hamburger = new Hamburger(burgerSet.hamburger);
        this.side = new Side(burgerSet.side);
        this.drink = new Drink(burgerSet.drink);
    }

    public Hamburger getHamburger() {
        return hamburger;
    }

    public Side getSide() {
        return side;
    }

    public Drink getDrink() {
        return drink;
    }

    //setter를 만들지 않는 이유: 생성자를 통해 세트를 구성하기 때문에
    //ㄴ> 상위 클래스에서 호출한 인스턴스로 product.subproduct.BurgerSet 인스턴스를 채우기 때문에, ...
    //ㄴ> 애초에 상위 인스턴스에서 가져온 객체(hamburger, side, drink)의 필드는
    //ㄴ> 각자의 클래스에 존재하기 때문에, 해당 값이 바뀌어도 해당 클래스가 불리어서 그 안의 setter 메소드에서 바뀌지
    //ㄴ> 굳이 여기서 setter로 상위 클래스의 필드까지 바꿀 필요는 없음
    //ㄴ> 그래서 getter (해당 객체를 부르기만) 하는것임 1!
}
