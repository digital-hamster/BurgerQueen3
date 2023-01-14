package app.product.subproduct;

import app.product.Product;

public class Side extends Product {
    private int ketchup;

    public Side(int id, String name, int price, int kcal, int ketchup){
        super(id, name, price, kcal);
        this.ketchup = ketchup;
    }

    //깊은 복사를 위한 생성자 오버로딩
    public Side(Side side){
        super(side.getName(), side.getPrice(), side.getKetchup());
        this.ketchup = side.getKetchup();
    }

    public int getKetchup() {
        return ketchup;
    }

    public void setKetchup(int ketchup) {
        this.ketchup = ketchup;
    }
}
