# 1~2일차

</br>

- Entity

> 엔터티는 다른 엔터티와 구별 할 수 있는 식별자를 가지고 있고, 시간에 흐름에 따라 지속적으로 변경이 되는 객체

</br>

- Value Object

> 각 속성이 개별적으로 변화하지 않고, 값 자체로 고유한 불변 객체
> s

|         Domain Element          |  State / Behavior  |
| :-----------------------------: | :----------------: |
| Entity, Value Object, Aggregate | State and Behavior |
|      Date Transfer Object       |     State Only     |
|       Service, Repository       |   Behavior Only    |

</br>

- Aggregate

> 일종의 엔터티지만 엔터티들의 집합이다  
> 각각의 aaggregate의 root가 존재  
> 루트는 곧 하나의 엔터티
>
> 하나의 트랜잭션 Aggregate로 보장되어야 된다.
> 스프링 서비스에서는 트랜잭션에 대한 보장

- ApplicationContext

> IOC 컨테이너에서는 개별 객체들의 의존관계 설정이 자동으로 이루어지고,  
> 객체들의 생성과 파괴 조합들을 관장
>
> 그것을 스프링에서는 ApplicationContext에서 이루어지게 한다.  
> BeanFactory를 상속 받는다. -> Ioc 기본 기능 제공  
> Bean은 스프링에서 제공하는 애플리케이션컨텍스트, 빈팩토리, ioc 컨테이너에 의해 관리되어지는 객체  
> IOC에 의해 관리되는 객체와 그렇지 않은 객체를 분리하기 위해 bean이라는 개념 사용

</br>

- Configuration Metadata

> 스프링의 애플리케이션컨텍스트는 실제 만들어야할 빈 정보를 설정 메타테이터로부터 받아온다.  
> 이 메타데이터를 이용해서 ioc 컨테이너에 의해 관리되는 객체들을 생성하고 구성,

# 3일차

</br>

- DI

> Ioc를 구성하는 일정한 패턴, 전략패턴, 서비스 로케이터 패턴, 팩토리패턴, 의존관계 주입패턴등이 있다.
> 객체를 생성자를 통해서 주입받는 패턴을 생성자 주입패턴이라고 한다.  
> -> Constuctor-based Dependency injection

</br>

- Circular dependencies

> A -> B를 참조하고 B -> A를 참조할 경우 순환 의존관계가 형성되면서  
> BeanCurrentlyInCreationException 예외 발생

- Component Scan

> 컴포넌트 스캔은 스프링이 직접 클래스를 검색해서 빈으로 등록해주는 기능  
> 설정 클래스에 빈으로 직접 등록하지 않아도 원하는 클래스를 빈으로 등록

- @Component
  - @Repository : Data access
  - @Service : Service class
  - @Controller : Spring MVC
  - @Configuration : Java Config

생성자 주입이
잘못된 패턴 가질 수 있어 쉽게 의존관계를 확인할 수 있고
스프링의 지원 없이도 테스트를 쉽게 해줘
생성된 주입은 불변성을 확보 해줘 -> 세터나 필드처럼 상태가 변하지 않는다. 이는 멀티 쓰레딩 환경에서도 의존관계의 안전성을 보존해준다.

스프링 컨테이너도 라이프 사이클을 가진다.
생성은 뉴를 받자나요
소멸은 어떻게 해요

# 4일차

Environment

애플리케이션컨텍스트가 제공해줌

예시

개발시에는 H2 데이터베이스를 사용하면
h2 디비를 사용하도록 빈이 등록
그러면 사용하는 데이터 소스의 커넥션 대상이 h2
그러다 운영중에는 mysql -> 환경이 바뀌는 거죠

profile에 따라 환경이가 바뀌고
그에 따라 property 소스가 다르게 설정돼
environment가 사용

properties

@Value 사용해서
프로퍼티의 값을 필드에 주입할 수 있다.

YAML
