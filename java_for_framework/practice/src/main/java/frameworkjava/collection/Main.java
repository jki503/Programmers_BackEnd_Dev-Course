package frameworkjava.collection;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        /*
        new MyCollection<>(Arrays.asList(1,2,3,4,5))
                .foreach(e -> System.out.println(e));
        */

        /*
        new MyCollection<>(Arrays.asList(1,2,3,4,5))
                .foreach(System.out::println);
        */

        /*
        MyCollection<String> c1 = new MyCollection<>(Arrays.asList("A","BA","CCC","DDDD","EEEEE"));

        MyCollection<Integer> c2 = c1.map(String::length);

        c1.foreach(e -> System.out.println(e));
        */

        /*위의 세 개를 체이닝으로

        new MyCollection<>(Arrays.asList("A","BA","CCC","DDDD","EEEEE"))
                .map(String::length)
                .foreach(System.out::println);
        */

        /* map과 filtering
        new MyCollection<>(Arrays.asList("A","BA","CCC","DDDD","EEEEE"))
                .map(String::length)
                .filter(i -> i % 2 == 0)
                .foreach(System.out::println);

        */

        int s = new MyCollection<>(Arrays.asList("A","BA","CCC","DDDD","EEEEE"))
                .map(String::length)
                .filter(i -> i % 2 == 0)
                .size();

        System.out.println(s);

    }
}
