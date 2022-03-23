package frameworkjava.practice.interfaceprac.generic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class GenericPractice2 {

    public static void main(String[] args) throws IOException {

        // 구현을 호출하는 쪽에서 조정함으로 함수형 인터페이스가 유용하다.

        //new GenericPractice2().loop(10, System.out::println);
        new GenericPractice2().filteredNumbers(30,
                i -> i % 2 ==0,
                System.out::println);

    }

    void filteredNumbers(int max, Predicate<Integer> p, Consumer<Integer> c){

        for (int i = 0; i < max; i++) {
            if(p.test(i)) c.accept(i);
        }

    }

    void loop(int n, MyConsumer<Integer> consumer){
        for (int i = 0; i < n; i++) {
            // 뭔가를 해야죠
            // i를 주고 뭔가 해라!
            // 호스트쪽에서 이제 하는거다.
            consumer.consume(i);
        }
    }

}
