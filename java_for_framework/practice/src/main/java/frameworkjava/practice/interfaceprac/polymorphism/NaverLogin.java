package frameworkjava.practice.interfaceprac.polymorphism;

public class NaverLogin implements Login{


    @Override
    public void login() {
        System.out.println("네이버로 로그인합니다.");
    }
}
