package app.product.subproduct;

import app.product.Product;

public class Drink extends Product {
    private boolean hasStraw;

    public Drink(int id, String name, int price, int kcal, boolean hasStraw){
        super(id, name, price, kcal);
        this.hasStraw = hasStraw;
        //ㄴ> this.가 붙는 이유: 생성자의 매개변수로 넣은 매개변수가 우선순위로 선언되기 때문에, this.를 통해 해당 클래스의 필드를
        //지칭하고 있다고 알려야 함
    }

    //깊은 복사를 위한 생성자 오버로딩
    public Drink(Drink drink) {
        super(drink.getName(), drink.getPrice(), drink.getKcal());
        this.hasStraw = drink.hasStraw();
    }

    public boolean hasStraw() {
        return hasStraw;
    }

    public void setHasStraw(boolean hasStraw) {
        this.hasStraw = hasStraw;
    }
}
