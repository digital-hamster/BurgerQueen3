package app;

public class Main {
    public static void main(String[] args) {

        //4. OrderApp: 주문하기 프로그램의 로직을 담아놓은 클래스
        //OrderApp orderApp = new OrderApp();

        //orderApp- 프로그램 시작시에 돌 메소드
        //orderApp.start();

        AppConfigurer appConfigurer = new AppConfigurer();

        OrderApp orderApp = new OrderApp(
                appConfigurer.productRepository(),
                appConfigurer.menu(),
                appConfigurer.cart(),
                appConfigurer.order()
        );
        orderApp.start();
    }
}