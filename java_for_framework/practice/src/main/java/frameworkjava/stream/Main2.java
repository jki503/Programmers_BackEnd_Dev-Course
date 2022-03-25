package frameworkjava.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main2 {

    public static void main(String[] args) {

        // list.stream
        Stream<Integer> stream = Arrays.asList(1,2,3).stream();

        // primitive int -> IntStream
        IntStream stream2 = Arrays.stream(new int[]{1,2,3})
                .map(Integer::valueOf);

        // boxed 사용해서 primitive wrapper로 감싼 후 스트림
        Stream<Integer> stream3 = Arrays.stream(new int[]{1,2,3})
                .boxed()
                .map(Integer::valueOf);

        // boxed 후 collection으로
        List<Integer> list = Arrays.stream(new int[]{1, 2, 3}).boxed().collect(Collectors.toList());

        // toArray 안해주면 Object[] 타입으로 반환
        Integer[] integers = Arrays.stream(new int[]{1, 2, 3}).boxed().toArray(Integer[]::new);

        // 계속 1을 발생시킴
        Stream.generate(() -> 1)
                .forEach(System.out::println);


        // 10개만 만들고 싶다.
        Stream.generate(() -> 1)
                .limit(10)
                .forEach(System.out::println);


        // seed 값을 가지고 다음 값을 어떻게 전달할지
        Stream.iterate(0,(i) -> i+2)
                .limit(10)
                .forEach(System.out::println);

    }
}
