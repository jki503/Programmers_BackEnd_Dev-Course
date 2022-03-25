package frameworkjava.interfaceprac.polymorphism;

public class LoginPractice {

    public static void main(String[] args) {

        // 설정파일, config
        new LoginPractice().run(LoginType.Naver); //호스트 코드
        UserService userService = new UserService(new KakaoLogin());

        userService.login();

        Login user1 = new KakaoLogin();
        Login user2 = new NaverLogin();

        user1.login();;
        user2.login();

//        Login user3 = getLogin(LoginType.Naver);
//        user3.login();
    }

    void run(LoginType loginType){
        Login user = getLogin(loginType);
        user.login();
    }

    // factory 패턴
    private Login getLogin(LoginType type) {
        if(type == LoginType.Kakao)
            return new KakaoLogin();
        return new NaverLogin();
    }
}
