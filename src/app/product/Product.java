package app.product;

public class Product {
    //Product는 하위 상품의 상위 클래스로, 상품을 하위로 분류했을 때 공통적으로 들어가는 공통점들을 클래스로 만든거임
        private int id;
        private String name;
        private int price;
        private int kcal;

        public Product(int id, String name, int price, int kcal){
            this.id = id;
            this.name = name;
            this.price = price;
            this.kcal = kcal;
        }
        //ㄴ> 해당 생성자는 하위 클래스들이 필수적으로 가져야 할 요소들이기 때문에, 생성자를 만들 때 매개변수의 값으로 필드 값을 넣어서
        //하위 클래스가 해당 필드들을 갖도록 강제하는 것


        //생성자2) burgerSet productRepo에 미리 등록된 상품이 아니라,
        //각 상품을 메소드를 돌려 구현하는 것이기에 생성자에 id가 필요 없음
        public Product(String name, int price, int kcal){
            this.name = name;
            this.price = price;
            this.kcal = kcal;
        }


    //private로 선언한 필드들(캡슐화를 위해)에 접근하기 위해 getter(값을 리턴), setter(값을 변경) 할 수 있도록 하는 것
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    public int getKcal() { return kcal; }

    public void setKcal(int kcal) { this.kcal = kcal; }
}
