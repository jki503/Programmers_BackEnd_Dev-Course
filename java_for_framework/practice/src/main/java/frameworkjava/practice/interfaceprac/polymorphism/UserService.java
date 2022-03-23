package frameworkjava.practice.interfaceprac.polymorphism;

public class UserService implements Login{

    // Login에 의존한다.
    private Login login;

    // 의존성을 외부에 맡긴다면, 의존도를 낮춘다.
    // 추상체와 결합을 하게 되면 결합도가 낮아진다.
    // 의존성을 외부로 전달 받으면 => DI
    // DIP : 의존관계 역전
    //
    public UserService(Login login) {
        this.login = login;
    }

    @Override
    public void login() {
            login.login();
    }
}
