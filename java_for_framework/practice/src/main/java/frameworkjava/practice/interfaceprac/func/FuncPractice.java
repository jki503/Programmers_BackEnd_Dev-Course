package frameworkjava.practice.interfaceprac.func;

class Greeting implements MySupply{


    @Override
    public String supply() {
        return "hello";
    }
}

class SayHello implements MyRunnable{

    @Override
    public void run() {
        System.out.println(new Greeting().supply());
    }
}


public class FuncPractice {

    public static void main(String[] args) {

        new SayHello().run();
        // 익명 클래스
        new MySupply(){
            @Override
            public String supply(){
                return "hello";
            }
        }.supply();

        new MyRunnable() {

            @Override
            public void run() {

            }
        }.run();


    }
}
