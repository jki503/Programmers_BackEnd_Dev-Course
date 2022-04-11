---
title: SpringBoot Part 1
---

</br>

## Table Of Contents

[Table Of Contents](#table-of-contents)

- [Table Of Contents](#table-of-contents)
  - [**Day 1**](#day-1)
  - [**Day 2**](#day-2)
  - [**DDD 기본**](#ddd-기본)
    - [**ApplacationContext**](#applacationcontext)
    - [**IOC**](#ioc)
  - [**Day 3**](#day-3)
  - [**Day 4**](#day-4)
    - [**DI**](#di)
    - [**Bean Scope**](#bean-scope)
  - [**Day 5**](#day-5)
    - [**Logging**](#logging)
    - [**SL4J**](#sl4j)
    - [**Log Level**](#log-level)
    - [Logger](#logger)
    - [LogBack](#logback)
    - [**jar 파일로 만들기**](#jar-파일로-만들기)

### **Day 1**

</br>

### **Day 2**

</br>

### **DDD 기본**

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

</br>

#### **ApplacationContext**

</br>

- ApplicationContext

> IOC 컨테이너에서는 개별 객체들의 의존관계 설정이 자동으로 이루어지고,  
> 객체들의 생성과 파괴 조합들을 관장
>
> 그것을 스프링에서는 ApplicationContext에서 이루어지게 한다.  
> BeanFactory를 상속 받는다. -> Ioc 기본 기능 제공  
> Bean은 스프링에서 제공하는 애플리케이션컨텍스트, 빈팩토리, ioc 컨테이너에 의해 관리되어지는 객체  
> IOC에 의해 관리되는 객체와 그렇지 않은 객체를 분리하기 위해 bean이라는 개념 사용
>
> xml : GenericXmlApplicationContext
> java file : AnnotationConfigApplicationContext

</br>

- Configuration Metadata

> 스프링의 애플리케이션컨텍스트는 실제 만들어야할 빈 정보를 설정 메타테이터로부터 받아온다.  
> 이 메타데이터를 이용해서 ioc 컨테이너에 의해 관리되는 객체들을 생성하고 구성,

</br>

#### **IOC**

</br>

> Ioc는 객체 자신이 사용할 구현체를 선택하는 것이 아니라  
> 외부에서 생성된 객체를 주입하는 것  
> 고수준 모듈이 저수준 모듈을 바라보는 것이 아니라  
> 고수준 모듈과 저수준 모듈의 결합도를 느슨하게 하기 위해  
> 인터페이스를 고수준 모듈에 만들어 사용.

</br>

- 스프링이 생성자 주입을 권장하는 이유

</br>

> 객체간의 의존성을 명확하게 확인하기 쉽다.  
> 스프링 지원 없이도 테스트 용이  
> 의존관계의 불변성을 확보 해줘 상태가 변하지 않는다.  
> -> 멀티 쓰레딩 환경에서도 의존관계 안전성 보장  
> 사실 애플리케이션 실행 도중 의존관계가 변경되는 경우가 거의 없을 것

</br>

### **Day 3**

</br>

### **Day 4**

</br>

#### **DI**

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

</br>

#### **Bean Scope**

</br>

- 싱글톤
- 프로토타입
- 리퀘스트
- 세션
- 애플리케이션
- 웹소켓

</br>

4일차

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

### **Day 5**

</br>

#### **Logging**

</br>

> 시스템 작동 상태의 기록과 보존,  
> 이용자의 습성 조사 및 시스템 동작의 분석 등을 하기 위해  
> 작동 중의 각종 정보 기록을 만드는 것

</br>

- Java Logging Framework
  - java.util.logging
  - Apache Commons logging
  - Log4J
  - `Logback`
  - `SL4J(Simple Logging Facade for Java)`

</br>

#### **SL4J**

</br>

> Logging Framework들을 추상화 해놓은 것  
> Facade Pattern을 이용한 Logging Framework

</br>

- 로깅 프레임워크의 Binding의 모듈

|                   Manual                   |
| :----------------------------------------: |
| ![sl4j image](./res/concrete-bindings.png) |

</br>

#### **Log Level**

- trace : 모든 흐름을 추적하겠다.
- debug : 디버깅 모드시
- info : 단순 정보
- warn
- error

#### Logger

> 이름 기반 생성

</br>

```java

  private static final Logger logger = LoggerFactory.getLogger("org.prgms.kdt.kdtspringorder.LogTester");

  private static final Logger logger = LoggerFactory.getLogger(LogTester.class);

  private final Logger logger = LoggerFactory.getLogger(this.class);

```

</br>

- 첫 번째 경우 패키지이름으로 명시하는 경우

</br>

> 패키지의 level을 상위로 지정할 경우  
> 특정 패키지의 class만 하위 레벨 단계로 보고싶을 때  
> 세밀하게 설정 가능

</br>

#### LogBack

</br>

- logback 설정하기
  - logback-test.xml 파일을 먼저 찾음
  - 없으면 logback.groovy 찾기
  - 없으면 logback.xml 찾기
  - 없으면 BasicConfiguration

</br>

- logback.xml

```xml
<configuration>

    <conversionRule
            conversionWord="clr"
            converterClass="org.springframework.boot.logging.logback.ColorConverter"/>

    <property name = "LOG_PATTERN" value = "%clr(%d{HH:mm:ss.SSS}){red} [%thread] %-5level %logger{36} - %msg%n"/>
    <property name = "FILE_LOG_PATTERN" value = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"/>

    <timestamp key="bySecond" datePattern="yyyyMMdd'T'HHmmss" />

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- encoders are assigned the type
             ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
        <encoder>
            <pattern>${LOG_PATTERN}</pattern>
        </encoder>
    </appender>

<!--    <appender name="FILE" class="ch.qos.logback.core.FileAppender">-->
<!--        <file>logs/kdt_${bySecond}}.log</file>-->
<!--        <append>false</append>-->
<!--        <encoder>-->
<!--            <pattern>${LOG_PATTERN}</pattern>-->
<!--        </encoder>-->
<!--    </appender>-->

    <appender name="ROLLING_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
<!--        <file>logs/access.log}.log</file>-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/access-%d{yyyy-MM-dd}.log</fileNamePattern>
        </rollingPolicy>

        <encoder>
            <pattern>${FILE_LOG_PATTERN}</pattern>
        </encoder>
    </appender>

    <logger name = "org.prgms.kdt.kdtspringorder" level = "debug" >
        <appender-ref ref="ROLLING_FILE"/>
    </logger>

    <root level="warn">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>

```

</br>

#### **jar 파일로 만들기**

</br>

> mvn clean package spring-boot:repackage
> executable jar 만들어지고, target에서 확인 가능

</br>

> 실행하면서 사용할 profile은  
> application.yaml을 분리하여 프로파일별로 만들면  
> suffix인색해서 설정정보 사용 가능
