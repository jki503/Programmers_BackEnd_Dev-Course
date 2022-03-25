package frameworkjava.optional;

import frameworkjava.collection.User;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        User user = null; // null이 될 수도 있고 안될 수도 있는 객체

        Optional<User> optionalUser = Optional.empty(); // 빈 값

        optionalUser = Optional.of(new User(1,"2"));

        optionalUser.isEmpty(); // 값이 없으면 true
        optionalUser.isPresent(); // 존재하면

        optionalUser.ifPresent( user1 -> {
             // do 1
        });


        optionalUser.ifPresentOrElse( user2 -> {
            // do 1
                }, () -> {
                    //do 2
                });

    }

}
