# StringBuilder와 StringBuffer

</br>

|             |    String     | StringBuffer | StringBuilder |
| :---------: | :-----------: | :----------: | :-----------: |
|   Storage   | Constant pool |     Heap     |     Heap      |
|  수정 가능  |   immutable   |   mutable    |    mutable    |
| thread-safe |       O       |      O       |       X       |
| performance |       3       |      2       |       1       |

> `String` : 불변 객체로 수정이 발생 할 경우 constant pool에 새로운 객체를 생성하는 연산이 실행 되어 느리다.  
> `StringBuilder` : 수정 가능한 객체로 수정시 연산이 가장 빠르지만 thread-safe 하지 않아 멀티 쓰레딩 환경에서 적합하지 않다.  
> `StringBuffer` : 수정 가능한 객체로 Stringbuilder 보다는 수행 속도가 느리지만 멀티쓰레딩 환경에서도 사용할 수 있다는 장점이 있다.

## StringBuilder, StringBuffer 주요 API

|          API          |   descirption    |
| :-------------------: | :--------------: |
|   setLenth(int Len)   |    길이 설정     |
| deleteCharAt(int idx) | 위치의 문자 삭제 |
| insert(int idx, type) | 위치의 원소 추가 |
|      substring()      |  문자열 자르기   |

</br>

```java

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


```
