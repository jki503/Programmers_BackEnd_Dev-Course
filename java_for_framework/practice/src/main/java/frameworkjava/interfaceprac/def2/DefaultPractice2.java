package frameworkjava.interfaceprac.def2;

public class DefaultPractice2{

    public static void main(String[] args) {
    }
}

class Service extends MyInterfaceAdapter{


    // 어댑터로 필요한 함수만 오버라이드가 가능하다.
    @Override
    public void method2() {
        super.method2();
    }
}
