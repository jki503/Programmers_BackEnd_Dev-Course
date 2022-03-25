package frameworkjava.collection;

import lombok.Getter;

@Getter
public class User {

    public static User EMPTY = new User(0,"");

    private int age;
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean isOver19() {
        return age >= 19;
    }
}
