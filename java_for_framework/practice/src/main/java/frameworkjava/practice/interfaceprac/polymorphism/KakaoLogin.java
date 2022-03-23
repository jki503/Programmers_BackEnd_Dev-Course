package frameworkjava.practice.interfaceprac.polymorphism;

public class KakaoLogin implements Login{


    @Override
    public void login() {
        System.out.println("카카오로 로그인 합니다");
    }
}
