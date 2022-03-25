package frameworkjava.interfaceprac.lambda;

public class lambdaPrac {

    public static void main(String[] args) {

        MySupplier s = () -> "Hello";

        MyMapper m = (str) -> str.length();
        MyMapper m2 = String::length; // method reference

        MyConsumer c = System.out::println; // 변경이 없는 경우
        MyConsumer c2 = (i) -> System.out.println(i*10); // 변경이 있는 경우

        MyRunnable r = () -> {
            c.consume(m.map(s.supply()));
        };

        r.run();

    }


}


