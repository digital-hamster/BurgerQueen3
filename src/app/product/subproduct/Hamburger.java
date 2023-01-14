package app.product.subproduct;

import app.product.Product;

public class Hamburger extends Product {
    private boolean isBurgerSet; //하위 클래스, 해당 객체가 지녀야 할 필드들을 선언
    private int burgerSetPrice;

    public Hamburger(int id, String name, int price, int kcal, boolean isBurgerSet, int burgerSetPrice){
        super(id, name, price, kcal);
        //ㄴ> 상위 클래스에서 상속받은 생성자의 필드는, 하위 클래스에서 바로 생성할 수 없고 상위 클래스를 호출해서 해당 클래스에서 받아온 필드로 선언해야하기 때문에 super

        this.isBurgerSet = isBurgerSet;
        this.burgerSetPrice = burgerSetPrice;
    }

    //깊은 복사를 위한 생성자 오버로딩
    public Hamburger(Hamburger hamburger){ //그니까 같은 상품을 다시 시키면, hamburger의 객체의 클래스에서 새로운 값을 넣은 객체가 반환될 수 있도록 하는듯
        super(hamburger.getName(), hamburger.getPrice(), hamburger.getKcal());
        this.isBurgerSet = hamburger.isBurgerSet();
        this.burgerSetPrice = getBurgerSetPrice();
    }

    public boolean isBurgerSet() {
        return isBurgerSet;
    }

    public void setIsBurgerSet(boolean burgerSet) {
        isBurgerSet = burgerSet;
    }

    public int getBurgerSetPrice() {
        return burgerSetPrice;
    }

    public void setBurgerSetPrice(int burgerSetPrice) {
        this.burgerSetPrice = burgerSetPrice;
    }
}
