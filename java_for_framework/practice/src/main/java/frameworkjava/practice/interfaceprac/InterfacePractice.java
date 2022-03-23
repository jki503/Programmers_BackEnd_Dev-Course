package frameworkjava.practice.interfaceprac;

// java.lang.Runnable

interface MyRunnable{


}


public class InterfacePractice implements Runnable, MyRunnable{

    public static void main(String[] args) {
        Runnable m = new InterfacePractice();
        m.run();

    }

    @Override
    public void run() {
        System.out.println("Hello world");
    }
}
