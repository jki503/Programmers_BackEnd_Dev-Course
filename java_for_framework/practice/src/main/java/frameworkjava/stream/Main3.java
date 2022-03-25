package frameworkjava.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;

public class Main3 {

    public static void main(String[] args) {

        /* 주사위를 100번 던져서 6이 나올 확률을 구하시오.
        Random r = new Random();
        double count = Stream.generate(() -> r.nextInt(6) + 1)
                .limit(100)
                .filter(num -> num == 6)
                .count();

        System.out.println(count / 100);

        */

        /* 1~9 사이의 값 중에서 겹치지 않게 3개를 출력하라
        Random r = new Random();
        int[] arr= Stream.generate(() -> r.nextInt(9) + 1)
                .distinct()
                .limit(3)
                .mapToInt(i -> i)  // primitice type으로 바뀜 IntStream임~
                .toArray(); // primitive는 타입 안정해줘도 나온다.

        System.out.println(Arrays.toString(arr));
        */

        // 0~200 사이 값 중에서 랜덤값 5개를 내림차순 순서대로 표시하시오.
        Random r = new Random();
        Stream.generate(() -> r.nextInt(199) + 1)
                .limit(5)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

    }

}
