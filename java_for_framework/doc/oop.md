# 객체 지향 프로그래밍

> 객체 지향 프로그래밍을 잘 하는 법은 일을 잘게 쪼개서 객체에게 위임하고, 서로 협력하게 만드는 것

</br>

- class 만들기 : 어떤 책임과 어떤 기능을 갖는지

```java

package com.programmers;

import java.util.*;

class MyObject extends Object implments Runnable{

  //필드 영역
  private int a = 0;

  //메소드 영역
  public void run(){

      a+=1;
  }


}

```

MyObject obj = new MyObject();

## 객체지향 특성

- 캡슐화

> 캡슐처럼 완성도가 있다.  
> 객체자가 전체 기능을 수행하는 일부  
> 그 기능을 수행하는 단위로써 완전함을 갖는다.  
> 객체 스스로 그 기능을 수행할 수 있는 단위.
>
> 정보가 은닉되어 있다.
> 내가 가지고 있는 정보를 밖으로 꺼내거나 밖에서 내 정보를 살펴보는 것을 허용하지 않는다.
> 객체의 정보가 밖으로 전달되거나 밖에서 객체 내의 정보에 접근하지 못하게 한다.
>
> `객체는 스스로 동작할 수 있는 환경을 갖고 있어야 한다. 외부에 의존하거나, 외부의 침략을 제한하여야 한다.`

</br>

```java

class Human{

  private Heart heart; // private으로 외부 차단
  private Blood blood;
  protected Gene gene;

  Blood donation(){

  }

}

Human human = new Human();

human.heart.stop(); // 이 객체 입장에서 외부에 접근 당하는 것

human.donation(); // 외부 제한!

class Child extends Human{

    void run(){
      super.heart;(X)
      this.gene;

    }

}

```

</br>

- 접근 제어자

|             | priavate | default | protected | public |
| :---------: | :------: | :-----: | :-------: | :----: |
| 동일 패키지 |    X     |    O    |     O     |   O    |
|    상속     |    X     |    X    |     O     |   O    |
| class 내부  |    O     |    O    |     O     |   O    |

</br>

## 상속

> 상위 (상속 해주는) 부모, super, `추상객체`
> 하위(상속 받는) 자식, this, 구체 객체

> 오해 : 상속을 언제 쓰냐면 공통된 기능을 여러 객체에게 전달할때 -> `이건 interface`
> 왜냐면 `상속은 is-A 관계지 has-A ` 관계가 아니자나

</br>

## 추상화

</br>

> 추상화된 객체 : 추상체  
> 구체적인 객체 : 구상체
>
> 추상체와 구상체는 상대적인거임  
> 객체간의 관계에서 상위에 있는 것이 항상 하위보다 추상적이어야함

</br>

```java

// 의미적 추상체
class Login{

  void login();

}

class KaKaoLogin extends Login{

  void login();

}

```

```java
// 추상 기능을 가진 객체

abstract class Login{

  abstract void login();
}

class KaKaoLogin extends Login{

  @Override
  void login(){};

}

```

```java
// 객체 자체가 추상적

interface Login{

   void login();
}

class KaKaoLogin implements Login{

  @Override
  void login(){};

}

```

## 다형성

> type을 여러가지로 표현할 수 있다?  
> 추상체로 정의 하여 구현체 사용하기

```java

class KaKaoLogin implements Login{

  @Override
  void login(){};

}

interface Portal{

  void portal();

}

class NaberLogin implements Login, Portal{

  void naver(){

  }

  @Override
  void login(){};

}

KaKaoLogin k = new KakaoLogin();
Login k = new KakaoLogin();

```

추상체로 타입을 지정할 수 있다

```java

Login login = new Login();
Login login = new kakaoLogin();

Login login = new NaverLogin();
Portal portal = new NaverLogin();

login.login() // login만
portal.portal();

```

## 객체를 구성하는 5가지 원칙

> `SRP` : 단일 책임원칙 - 객체는 책임을 하나만 줘라 수정이 필요할 경우 수정되는 이유는 하나 때문이어야 한다.
> `OCP` : 수정에는 닫혀 있고 확장에는 열려있다. - 기능이 변경될 필요가 있다면 수정하지말고 확장 해라
> `LSP` : 추상 객체로 사용되는 부분에 구상객체가 들어가도 아무 문제 없어야 한다.
> `ISP` : 인터페이스를 나누어서 사용
> `DIP` : 의존관계 역전
