package frameworkjava.collection;

import java.util.Arrays;

public class Main2 {

    public static void main(String[] args) {

        /* 19세 이상 유저
        그러나 user의 프로퍼티를 꺼내오는 방식은 조금...
        new MyCollection<User>(
                Arrays.asList(
                        new User(15,"AAA"),
                        new User(16,"BBB"),
                        new User(17,"CCC"),
                        new User(18,"DDD"),
                        new User(19,"EEE"),
                        new User(20,"FFF"),
                        new User(21,"GGG"),
                        new User(22,"HHH"),
                        new User(23,"III")
                ))
                .filter(u -> u.getAge() >= 19)
                .foreach(System.out::println);

         */

        new MyCollection<User>(
                Arrays.asList(
                        new User(15,"AAA"),
                        new User(16,"BBB"),
                        new User(17,"CCC"),
                        new User(18,"DDD"),
                        new User(19,"EEE"),
                        new User(20,"FFF"),
                        new User(21,"GGG"),
                        new User(22,"HHH"),
                        new User(23,"III")
                ))
                .filter(u -> u.isOver19())
                .foreach(System.out::println);


    }
}
