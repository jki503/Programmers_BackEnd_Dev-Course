package frameworkjava.practice;

public class StringBufferPractice {

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();

        sb.append("abc");
        sb.setLength(2);
        System.out.println(sb.toString()); // ab 출력

        sb.insert(1,"c");
        System.out.println(sb.toString()); // acb 출력
        System.out.println(sb.length()); // 3 출력

        sb.deleteCharAt(1);

        System.out.println(sb.toString()); // ab 출력
        System.out.println(sb.substring(0,2)); // ab
        System.out.println(sb.substring(0)); // ab
        System.out.println(sb.compareTo(new StringBuffer("ab"))); // 0

    }
}
