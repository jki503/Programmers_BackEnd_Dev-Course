---
title: SpringBoot Part2
Author: Jung
---

## **Table of Contents**

- [Table of Contents](#table-of-contents)

</br>

## **Day 1**

</br>

### **소프트웨어 테스팅**

</br>

> 소프트웨어의 결함을 찾는 행위

</br>

- testing level
  - unit
  - integration
  - component
  - end to end
  - exploratory

</br>

#### **Unit test**

</br>

> 프로그램의 기본 단위인 모듈을 테스트  
> 소프트웨어에서 테스트할 수 있는 가장 작은 단위  
> ex) 도메인, 서비스 각기 다르게 테스트가 가능.
>
> 크게 두가지 목적
>
> - 지속적으로 변경 될 때 발생하는 오류를 테스트 보호에 의해 보호
> - 구현 내용을 읽어 보지 않더라도 테스트 케이스만 보더라도 기능을 쉽게 파악 가능
>   </br>

- SUT

> System under test  
> 테스트 대상 즉 클래스를 지칭  
> 의존 관계에 있는 다른 객체들을 협력 관계라가 지칭  
> 협력 관계에 있는 객체들을 테스트 더블로 대체해서  
> sut만 고립하여 진행하는게 단위 테스트

</br>

- Test Double
  - Stub
  - Mock

> sut의 협력 객체를 사용할 수 없을때 사용하는 것  
> 테스트 대상 코드와 상호작용하는 객체

</br>

#### **Integration Test**

</br>

> 개발자가 변경할 수 없는 부분까지 묶어서 검즈알 때 사용  
> DB에 접근하거나 전체 코드와 다양한 하ㅗㄴ경이 작동되는지 확인하는 모든 작업 수행  
> 단위테스트에서 발견하기 어려운 버그를 찾을 수 있다는 장점

</br>

#### **Junit**

</br>

> 매 단위 테스트시마다 테스트를 클래스의 인스턴스가 생성되어 독립적인 테스트가 가능  
> 애노테이션을 사용해서 라이프 사이클 관리 테스트 코드 간결하게 작성하도록 지원  
> 테스트 러너를 제공해서 인텔리제이/이클립스/메이븐에서도 쉽게 코트 실행  
> assert로 테스트 수행 결과를 판별 -> assertEquals

</br>

- Junit5

</br>

> `Junit Platform` : JVM상에 테스트 프레임워크를 런칭하기 위한 근간 제공, 테스트를 발견하고 테스트 계획을 생성하는 TestEngine 인터페이스를 가지고 있고, TestEngine을 통해서 테스트를 발견하고 실행하고 결과 보고  
> `Junit Jupiter` : Test Enigne의 실제 구현체, Jupiter api로 테스트 코드 작성 가능  
> `Junit Vintage` : Junit 4 버전으로 작성된 코드가 실행 될 때 사용

</br>

#### **Mock Object**

</br>

- Mock 오브젝트는 행위 검증을 사용하고,
- stub을 포함한 다른 대역들은 상태 검증을 사용

> 가짜객체가 stub이고
> Mock 객체는 호출에 대한 기대를 명세

- 상태 검증 : 메소드가 수행 된 후, 객체의 상태를 확인하여 올바르게 동작했는지 확인하는 검증법
- 행위 검증 : 메소드의 리턴 값으로 판단할 수 없는 경우 특정 동작을 수행하는지 확인하는 검증법

</br>

- Mock Object 생성을 도와주는 Test Framework
  - Mockito
  - JMock
  - EasyMock.

#### **Springdml JUnit 5**
