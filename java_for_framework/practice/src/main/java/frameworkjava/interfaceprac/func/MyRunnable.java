package frameworkjava.interfaceprac.func;


@FunctionalInterface
public interface MyRunnable {
    void run(); // 추상 메서드가 하나밖에 없는 메소드 == 함수형 인터페이스
}

@FunctionalInterface
interface MyMap{

    void map(); // 추상 메서드가 하나밖에 없음

    default void sayHello(){
        System.out.println("Hello");
    }

    static void sayBye(){
        System.out.println("Bye");
    }
}