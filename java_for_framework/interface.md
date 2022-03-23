# Interface

</br>

## Interface 기능

</br>

- Interface에서 상속 받을때 Interface 함수 Override 구현 필수
- 다형성 -> Interface는 그대로 구현체를 바꿔 다형성 유지
- 다중 상속 가능 -> class 상속은 다중 상속 불가 X
- DIP -> 객체간의 관계를 interface를 거쳐 의존성이 약화

</br>

## Default Method

</br>

- Java 8 이상부터 기능 개선
- 인터페이스가 구현체를 가질 수 있게 되었다.
- 인터페이스 추가만으로 기능을 확장
- static 함수도 가능

</br>

```java
package java.util;

default V getOrDefault(Object key, V defaultValue) {
        V v;
        return (((v = get(key)) != null) || containsKey(key))
            ? v
            : defaultValue;
    }

```

</br>

> 대표적으로 Map의 getOrDefault 함수.  
> 구현체로부터 overriding하지 않아도 자체적으로 구현체를 가짐.

</br>

## Functional Interface

</br>

- 추상 메소드가 하나만 존재하는 인터페이스 : default static 있어도 하나만 있으면 된다.
- @FunctionalInterface

</br>

```java

@FunctionalInterface
interface MyMap{

    void map(); // 추상 메서드가 하나밖에 없어도 가능

    default void sayHello(){
        System.out.println("Hello");
    }

    static void sayBye(){
        System.out.println("Bye");
    }
}

```

</br>

## 인터페이스 임시 생성하기

</br>

- 익명 클래스를 사용해서 인터페이스의 인스턴스를 생성하고 구현을바로 정의하고 사용한다.

</br>

## Lambad 표현식

</br>

- 익명 메소드를 사용해서 간결한 인터페이스 인스턴스 생성 방법.
- `Functional Interface에서만 가능!`
- 간결한 표현에 용이

</br>

## 메소드 레퍼런스

</br>

- 람다 표현식에서 입력되는 값을 변경없이 바로 사용할 경우
- 최종으로 적용될 메소드의 레퍼런스를 지정해주는 방식
- `메소드 레퍼런스는 입력되는 값의 변경을 막아준다!` -> 입력값을 변경하지 말라는 표현

```java

@FunctionalInterface
public interface MyMapper {
    int map(String s);
}

MyMapper m2 = String::length; // method reference

m2.map("i am a boy");
// 10 출력
```
