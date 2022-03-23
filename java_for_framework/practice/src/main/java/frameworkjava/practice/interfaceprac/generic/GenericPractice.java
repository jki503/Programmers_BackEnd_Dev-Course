package frameworkjava.practice.interfaceprac.generic;

public class GenericPractice {

    public static void main(String[] args) {

        MySupplier<String> s = () -> "Hello";

        MyMapper<String, Integer> m = (str) -> str.length();
        MyMapper<Integer, Integer> m2 = i -> i * i;
        MyMapper<Integer, String> m3 = Integer::toBinaryString;

        MyConsumer<String> c = System.out::println; // 변경이 있는 경우

        MyRunnable r = () -> {
            c.consume(m3.map(m2.map(m.map(s.supply()))));
        };

        r.run();
    }


}


