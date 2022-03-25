package frameworkjava.interfaceprac.def3;

public interface Ability {
    static void sayHello(){
        System.out.println("hello world");
    }
}

interface Flyable {
    default void fly() {
        System.out.println("Fly");
    }

}

interface Swimmable {

    default void swim() {
        System.out.println("Swim");
    }

    ;
}

interface Walkable {

    default void walk() {
        System.out.println("Walk");
    }

    ;

}