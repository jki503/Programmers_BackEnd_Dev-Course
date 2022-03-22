package frameworkjava.practice.collcetions.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListPractice {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Iterator<Integer> iter = list.iterator();
        iter.forEachRemaining((i) -> System.out.println(i));

    }
}
