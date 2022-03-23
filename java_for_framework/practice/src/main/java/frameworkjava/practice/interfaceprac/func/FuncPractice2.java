package frameworkjava.practice.interfaceprac.func;

public class FuncPractice2 {

    public static void main(String[] args) {


        // 익명 메소드를 사용해서 표현하는 방법 : 람다 표현식
        MyRunnable r1 = () -> System.out.println("Hello");

        MyRunnable r2 = new MyRunnable() {

            @Override
            public void run() {
                System.out.println("Hello");
            }
        };
        r2.run();

        MySupply s1 = () -> "hello";

        MyRunnable r3 = () -> {
            MySupply s2  = () -> "Hello Hello Hello";
            System.out.println(s2.supply());
        };
        r3.run();
    }
}
