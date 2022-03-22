package frameworkjava.practice.collcetions.list;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LinkedListPractice {

    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();

        Set<Integer> set = Collections.newSetFromMap(new ConcurrentHashMap<Integer,Boolean>());

    }

}
