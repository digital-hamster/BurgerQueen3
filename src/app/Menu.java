package app;

import app.product.Product;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

public class Menu {

    //#1
    //메뉴를 출력하기 위해서 필요한 것: 각 메뉴의 가격, 칼로리 등을 담은 상세 product[] 배열
    //ㄴ> productRepo에서 해당 상세정보를 기술했음
    //ㄴ> productRepo에 담긴 상세정보에 대한 필드는, 각 하위 객체(drink, hamburger, side)의 클래스에서 호출



    private Product[] products;//#1

    public Menu(Product[] products){ this.products = products; }




    //#2 메뉴를 출력하기 위해 .. app.Menu 클래스에 printMenu 메소드를 추가할 것임 ⇒ 객체가 수행해야 하는 메소드를 해당 주체의 클래스에 담으니까
    public void printMenu(){
        System.out.println("[🔻] 메뉴");
        System.out.println("-".repeat(60));

        printHamburgers(true); //printPrice여부, boolean
        printSides(true);
        printDrinks(true);

        System.out.println();
        System.out.println("🧺 (0) 장바구니");
        System.out.println("📦 (+) 주문하기");
        System.out.println("-".repeat(60));
        System.out.print("[📣] 메뉴를 선택해주세요 : ");
    }

    protected void printDrinks(boolean printPrice) { //분기를 위해 printPrice를 추가 (매개변수)
        System.out.println("🥤 음료");
        for(Product product : products){
            if(product instanceof Drink) {
                printEachMenu(product, printPrice); //분기를 위한 인자(printPrice)ccnrk
            }
        }
        System.out.println();
    }

    protected void printSides(boolean printPrice) {
        System.out.println("🍟 사이드");
        for(Product product : products){
            if(product instanceof Side) {
                printEachMenu(product, printPrice);
            }
        }
        System.out.println();
    }

    private void printHamburgers(boolean printPrice) {
        System.out.println("🍔 햄버거");
        for(Product product : products){
            if(product instanceof Hamburger){
                printEachMenu(product, printPrice);
            }
        }
        System.out.println();
    }

    private static void printEachMenu(Product product, boolean printPrice) {
        //(1) printSides와 printDrinks 조건분기 전
        //System.out.printf(
        //        "   (%d) %s %5dKcal %5d원\n",
        //        product.getId(), product.getName(), product.getKcal(), product.getPrice()
        //);

        //(2) printSides와 printDrinks 조건분기
        if(printPrice) System.out.printf("  (%d) %s %5sKcal %5d원\n", product.getId(), product.getName(), product.getKcal(), product.getPrice());
        //ㄴ> printPrice가 true면 가격을 포함하여 메뉴 출력 !!, 포함하지 않는다면 가격을 표기하지 않도록
        else System.out.printf("  (%d) %s %5sKcal %5d원\n", product.getId(), product.getName(), product.getKcal(), product.getPrice());

    }

}
