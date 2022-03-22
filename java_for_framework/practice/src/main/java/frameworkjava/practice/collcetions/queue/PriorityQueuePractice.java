package frameworkjava.practice.collcetions.queue;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueuePractice {

    public static void main(String[] args) {

        // default 오름차순
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Collections 이용해서 내림차순
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());

        // lamda 이용해서 절댓값을 기준으로 오름차순, 같으면 양의 정수가 우선순위
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((o1, o2) -> {

            int abs1 = o1 < 0 ? -o1 : o1;
            int abs2 = o2 < 0 ? -o2 : o2;

            if(abs1 == abs2) return o1 < o2 ? 1 : -1;
            else
                return abs1-abs2;
        });

        // Comparator 이용해서 절댓값을 기준으로 오름차순, 같으면 양의 정수가 우선순위
        PriorityQueue<Integer> pq3 = new PriorityQueue<>(new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                int abs1 = o1 < 0 ? -o1 : o1;
                int abs2 = o2 < 0 ? -o2 : o2;

                if(abs1 == abs2) return o1 < o2 ? 1 : -1;
                else
                    return abs1-abs2;
            }
        });

        // Comparable interface 상속 받고 임금 높은 직원 순
        PriorityQueue<Employee> pq4 = new PriorityQueue<>();

        pq4.offer(new Employee("Jung",100000));
        pq4.offer(new Employee("Na",50000));

        pq4.iterator().forEachRemaining(E -> System.out.println(E));
    }

    private static class Employee implements Comparable<Employee>{

        private String name;
        private int wage;

        public Employee(String name, int wage) {
            this.name = name;
            this.wage = wage;
        }

        @Override
        public int compareTo(Employee o) {
            return o.wage-this.wage;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", wage=" + wage +
                    '}';
        }
    }
}
