package frameworkjava.practice.Collcetions.List;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ListPractice {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        list.add(1);

        System.out.println(list.contains(1));

        // add, remove, contains,size,clear

        Iterator<Integer> iter = list.iterator();

        while(iter.hasNext()){
            System.out.println(iter.next());
        }

        list.removeAll(list);
        System.out.println(list.size());

    }
}
