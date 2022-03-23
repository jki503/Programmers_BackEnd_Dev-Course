package frameworkjava.practice.interfaceprac.def1;

interface MyInterface{
    // 추상 메소드로만 이뤄진 클래스 == 인터페이스
    void method1(); // 구현 X 추상 메소
    default void sayHello(){System.out.println("hi");} //구현 O
}


public class DefaultPractice implements MyInterface{

    public static void main(String[] args) {
        new DefaultPractice().method1();
    }

    @Override
    public void sayHello() {
        System.out.println("bye");
    }

    @Override
    public void method1() {
        throw new RuntimeException();
    }
}
