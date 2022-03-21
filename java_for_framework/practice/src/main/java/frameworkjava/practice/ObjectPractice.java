package frameworkjava.practice;

public class ObjectPractice {

    public static void main(String[] args) {

        Unit unit = new Unit(1L,20L,"monster", 500L);
        Unit unit2 = new Unit(1L,20L,"monster", 500L);

        System.out.println(unit); // toString이 정의 되지 않으면 instance 반환

        System.out.println(unit.equals(unit2)); // 객체 비교

    }
}
