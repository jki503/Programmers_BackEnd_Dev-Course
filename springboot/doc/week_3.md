---
title: SpringBoot part 3
Author: Jung
---

## **Table Of Contents**

- [**Table Of Contents**](#table-of-contents)
- [**Day 1**](#day-1)
  - [**HTTP**](#http)
  - [**HTML**](#html)
  - [**Servlet**](#servlet)
    - [**Servlet Life Cycle**](#servlet-life-cycle)
    - [**Servlet Tomcat 구동**](#servlet-tomcat-구동)
- [**Day 2**](#day-2)
  - [**Dispatch Servlet**](#dispatch-servlet)
  - [**Resource Handler**](#resource-handler)
- [**Day 3**](#day-3)
  - [**WebApplicationContext**](#webapplicationcontext)
  - [**REST API**](#rest-api)
- [**Day 4**](#day-4)
  - [**SPA**](#spa)
  - [**CORS**](#cors)

  - [**SPA**](#spa)

  - [**SPA**](#spa)

  - [**SPA**](#spa)

  - [**SPA**](#spa)

  - [**WebApplicationContext**](#webapplicationcontext)
  - [**REST API**](#rest-api)

  - [**WebApplicationContext**](#webapplicationcontext)

  - [**WebApplicationContext**](#webapplicationcontext)

  - [**HTTP**](#http)
  - [**HTML**](#html)
  - [**Servlet**](#servlet)
    - [**Servlet Life Cycle**](#servlet-life-cycle)
    - [**Servlet Tomcat 구동**](#servlet-tomcat-구동)

- [**Day 2**](#day-2)
  - [**Dispatch Servlet**](#dispatch-servlet)

</br>

## **Day 1**

- Web의 구성
  - HTTP : Application Control
  - URI : HTTP가 조작대상을 지정하는 리소스 식별자
  - HTML : URI가 사용할 HTML 링크

</br>

- URI
  - (http://blog.example.com/entries/1)
    - URI Schema : http
    - 호스트명 : blog.examples.com
    - 패스 : /entries/1
  - (http://jung:pass@kdt.programmers.com:8080/serach?q=test&debug=true)
    - URI Schema : http
    - 사용자 : jung:pass (보안 취약)
    - 호스트 명 : jung:pass@kdt.programmers.com
    - 포트 번호 :8080
    - 쿼리 파라미터 : q=test&debug=true

</br>

### **HTTP**

|            HTTP             |
| :-------------------------: |
| ![http img](./res/http.png) |

</br>

- Http는 요청이 있으면 응답이 없을 수 없다.
  - Http는 대표적인 동기형 프로토콜
- 클라이언트가 웹브라우저에 요청을 하면

  - 브라우저가 OS 자원을 사용하여
  - 서버에서 네트워크를 타서 전송

- HTTP의 주요 특징
  - TCP/IP 기반
  - 요청/응답형 프로토콜
  - 동기형 프로토콜
  - stateless(무상태성)

</br>

| 메서드  |                        의미                         |
| :-----: | :-------------------------------------------------: |
|   GET   |                     리소스 취득                     |
|  POST   | 서브 리소스의 작성, 리소스 데이터 추가, 그밖의 동작 |
|   PUT   |              리소스 갱신, 리소스 작성               |
| DELETE  |                     리소스 삭제                     |
|  HEAD   |                 리소스의 헤더 취득                  |
| OPTIONS |           리소스가 서포트하는 메소드 취득           |
|  TRACE  |         자기 앞으로 요청 메시지를 반환 시험         |
| CONNECT |          프록시 동작의 터널 접속으로 변경           |

</br>

| CRUD 명 | 의미 |  메서드  |
| :-----: | :--: | :------: |
| CREATE  | 작성 | POST/PUT |
|  READ   | 읽기 |   GET    |
| UPDATE  | 갱신 |   PUT    |
| DELETE  | 삭제 |  DELETE  |

</br>

### **HTML**

> 하나의 정보만이 아니라  
> 다른 정보들과 연결되어 있어 다른 정보를 볼 수 있다.
>
> 구조적인 표현을 하는 언어로  
> 단순 텍스트가 아니라 특정 구조를 갖고 있게 만든 것.
>
> `연결성을 갖고 있는 마크업 언어!`

</br>

|              Web Application Architecture              |
| :----------------------------------------------------: |
| ![Web Application Architecture](./res/web_server.webp) |

- 1. user가 request
- 2. Web Server가 file systme이나 DB 접근
- 3. TCP/IP 위에 있는 HTTP로 response를 보낸다.

</br>

|                           Web Application Architecture Diagram                            |
| :---------------------------------------------------------------------------------------: |
| ![Web Application Architecture diagram](./res/web_application_architecture_diagram_.webp) |
|            [reference](https://litslink.com/blog/web-application-architecture)            |

</br>

- 1. 요청을 날리면, url에 해당하는 도메인 네임에 대한 ip를 가져옴, http는 ip를 통해 요청이 날라감
- 2. LoadBalancer가 브라우저의 요청을 분산 시켜준다. 수평적 확장.
- 4. Web app server는 자바코드가 실행 되면 jdbc를 통해 db 접근 가능 - 5. 캐싱 서비스를 이용해서 db접근에 대한 부하를 막아줄 수 있다.
- 6a. 6b. 예시로 컨텐츠를 압축하는 것이 오래걸리는 일이어서 Job Queue가 처리해준다. -> 비동기 방식으로 동작
- 7. Full text search service
  - 검색시 많은 리소스를 요구하여 따로 구분
- 8.  웹앱서버에 별도의 요청을 service로 나누어 보낼 수 있다.

</br>

### **Servlet**

|          서블릿 이해하기          |
| :-------------------------------: |
| ![servlet img](./res/servlet.png) |

> 자바 인터페이스로 존재하고 웹 서버 안에서 동작하는 자바 프로그램이고  
> 웹 클라이언트로부터의 요청에 대해 응답해준다.  
> 서블릿의 구현체로 HttpServlet 사용

</br>

- WAS가 없다면...
  - TCP/IP 커넥션 연결/해제 구현...
  - http 메시지 오면 헤더랑 바디 parsing해서 정의한 클래스를 타입화 해서 http 자체를 서포트하는 서버도 만들어야되고...

</br>

#### **Servlet Life Cycle**

</br>

- 1. Web Contanier가 Servlet을 상속받은 class들을 load
- 2. Instantiate servlet -> constructor runs
- 3. init()
- 4. service() : 클라이언트의 요청에 대해서 쓰레드를 생성하거나 쓰레드 풀에서 가져온 다음, 요청을 핸들링
- 5. doGet() / doPost() : 핸들링 된 요청에 의해 (post/get) 메서드 수행
- 6. destroy()

</br>

> 하나의 요청은 하나의 쓰레드 안에서 돌아야 한다.  
> 그 후 doGet이나 doPost 메서드 호출 후 쓰레드가 반환 되거나 없어지거나.  
> 다른 요청의 필드를 사용할 경우 기존 요청과 뒤 섞일 수 있다. -> 메소드 안에서 변수를 만들고 처리해야함.

</br>

#### **Servlet Tomcat 구동**

</br>

- WEB-INF, webapp.xml

|              servlet 매핑              |
| :------------------------------------: |
| ![diretory img](./res/servlet_dir.png) |
|    ![xml img](./res/webapp_xml.png)    |

> war로 배포할때 directory 주의  
> 매핑 해야 servlet 찾을 수 있음

</br>

- @WebServlet
  - value : url 매핑
  - loadOnStartUp : (1)서블릿을 was가 올라갈 때 요청 받기 전에 미리 로드를 시키겠다.

</br>

- WebApplicationInitializer : spinrgframework 지원

```java

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class KdtWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("test", new TestServlet());
        servletRegistration.addMapping("/*");
        servletRegistration.setLoadOnStartup(1);
    }
}

```

</br>
</br>
</br>

## **Day 2**

</br>

### **Dispatch Servlet**

</br>

|                  Dispatch servlet                   |
| :-------------------------------------------------: |
| ![dispatch servlet img](./res/dispatch_servlet.png) |

> 프론트 컨트롤러를 사용하여 디스패치 서블릿 제공  
> 서블릿을 작성하는 것이 아니라 컨트롤러를 작성하고  
> 스프링으 디스패치 서블릿을 통해 컨트롤러를 싱행시키는 동작 원리

</br>

|                Spring MVC 흐름                |
| :-------------------------------------------: |
| ![spring mvc flow](./res/spring_mvc_flow.png) |

- 1. HTTP를 요청 후 dispatch servlet이 컨트롤러를 찾아서 요청을 위임
- 2. 컨트롤러가 서비스를 호출
- 3. 비즈니스 로직을 처리 후 결과를 전달
- 4. 컨트롤러는 화면을 전달할 모델을 생성
- 5. 그 후 모델과 뷰를 dispatch servlet으로 전달한 후 응답을 만들어냄

</br>

|            handler            |
| :---------------------------: |
| ![handelr](./res/handler.png) |

> url 정보에 대하여 어떤 핸들러를 처리해야 할 지 정해주는 것

</br>

- RequestMappingHandlerMapping.class
- 핸들러가 갖고 있는 메서드 파라미터들을 파라미터에 맞는 정보로 변환해주는 것을 adapter

</br>

### **Resource Handler**

</br>

> 디폴트 서블릿의 요청을 위임해서, 디폴트 서블릿을 통해서 리소를 서빙하는 것.

</br>

## **Day 3**

</br>

### **WebApplicationContext**

</br>

|                    WebApplicationContex                    |
| :--------------------------------------------------------: |
| ![WebApplicationContex](./res/web_application_context.png) |

> 애플리케이션 컨텍스트를 상속받고  
> 서블릿 컨텍스트에 접근할 수 있는 기능이 추가된 애플리케이션 컨텍스트  
> 서블릿 컨텍스트는 서블릿 컨테이너에 의해 생성되는 객체  
> 여러 서블릿이 공유가 가능한 정보를 서블릿 컨텍스트에 담아서 사용하는 공유 자원  
> 모든 애플리케이션 컨텍스트에 접근 가능한 빈? -> Root ApplicationContext...  
> ServletContext가 만들어질 때 root ApplicationContext가 만들어지고  
> ServletContext의 setAttribute()에 의해 들어간다.
> 그러면 디스패처서블릿들은 서블릿 컨텍스트에 접근하고,  
> 그 곳에서 애플리케이션 컨텍스트를 가져와서  
> 디스패처 서블릿 컨텍스트가 만든 애플리케이션 컨텍스트와 부모 자식 관계가 형성

</br>

|               WebApplication 동작                |
| :----------------------------------------------: |
|       ![web action](./res/web_action.png)        |
| ![web action detail](./res/web_action_layer.png) |

</br>

> 디스패처 서블릿들이, 웹 애플리케이션 컨텍스트에 있는  
> 컨트롤러들에게 요청을 위임

</br>

- 프레젠테이션 계층
  - 사용자 요청에 대해서 처리가 되는 부분
- 서비스
  - 비즈니스 로직
- 데이터 액세스
  - DAO와 Repository를 통해 DB 접근

</br>

### **REST API**

</br>

> 간단한 의미로, 웹상의 자료를 HTTP위에서 SOAP이나  
> 쿠키를 통한 세션 트랙킹 같은 별도의 전송 계층 없이  
> 전송하기 위한 아주 간단한 인터페이스

</br>
  
- REST 아키텍처 스타일
  - 클라이언트 - 서버
  - 스테이트리스
  - 캐시
  - 균일한 인터페이스
    - URL로 지정한 리소스에 대한 조작을 통일되고 한정적인 인터페이스로 수행하는 아키텍처 스타일
  - 계층화된 시스템

</br>

|                       HTTP 메시지 컨버터 위치                        |
| :------------------------------------------------------------------: |
| ![http message converter location](./res/http_message_converter.png) |

</br>

> 핸들러 어댑터가 매핑해서 핸들러가 호출  
> 넘겨준 파라미터를 변형하는 것을 Argument Resolver가 실행  
> 그 후 응답하는 것을 ReturnValueHandler가 처리  
> @ResponseBody나 HttpEntity가 반환되면  
> http 메시지 컨버터가 동작

</br>

RestController 추가하기
argument resolver 내용 디테일하게 ㄱ

</br>

## **Day 4**

</br>

### **SPA**

</br>

- 단일 페이지 웹 애플리케이션

> 사용자 인터렉션에 의해 URL이 변경시 화면 전체의 로드가 없이  
> 화면의 일부분만 동적으로 렌더링하여 데스크탑 어플리케이션과  
> 비슷한 유저경험을 제공

</br>

- AJAX를 사용하여 대부분의 리소스(HTML, CSS, Script)들은 어플리케이션 로드시 한 번 읽는다.
- Json과 같은 데이터만 어플리케이션 실행중에 읽어오고 관련된 화면을 변경시킨다.

</br>

|                  spa                   |
| :------------------------------------: |
| ![spa 설명](./res/spa_description.png) |

</br>

- 장점
  - 페이지 요청시 전체를 렌더링 하지 않고 변경되는 부분만 갱신
    - 전체 트래픽 감소
    - 렌더링에서 보다 더 좋은 효율
    - 모듈화 또는 컴포넌트 개발을 용이하게 함
    - 백엔드와 프론트엔드이 비교적 명확하게 구분

</br>

- 단점
  - 웹 애플리케이션에 필요한 정적 리소스를 한 번에 다운하여 초기 구동 속도가 느리다
  - 데이터 처리를 클라이언트에서 하는 경우가 있어 JS 코드가 외부 노출되어 보안 문제
  - 검색엔진 최적화가 어렵다.
    - 검색 엔진이 크롤링할 때 JavaScript를 실행하지 않고 어플리케이션이 로드되기 전의 빈 상태의 코드를 크롤링하기 때문에 인덱싱이 제대로 이루어지지 않는다.

</br>

|                     페이지 로딩                      |
| :--------------------------------------------------: |
| ![페이지 로딩 절차](./res/page_loading_sequence.png) |

</br>

- 메뉴 정보를 동적으로 만드는 경우
  - 계속해서 사용자 데이터 모델에 추가 될 경우 세션이 커지게 된다.
  - SPA에서는 사용자 데이터 모델의 일부를 브라우저 메모리에 들고 있게 되는 것.
  - 요청을 할 때 필요한 부분을 그 메모리에서 사용하는 것

</br>

|            라우팅 처리            |
| :-------------------------------: |
| ![라우팅 처리](./res/routing.png) |

</br>

### **CORS**

</br>

|                       same-origin                        |
| :------------------------------------------------------: |
| ![same origin](/springboot/doc/res/cors_same_origin.png) |

</br>

> HTTP 헤더를 이용해서  
> 애플리케이션의 다른 origin에  
> 리소스에 접근 할 수 있도록 하는 메커니즘

</br>

|                      cors sequence                      |
| :-----------------------------------------------------: |
| ![cors sequence](/springboot/doc/res/cors_sequence.png) |

</br>

- Options에 origin을 실어서 보낸다. - 보내는 호스트
- 서버는 액세스를 허용하는 origin을 보낸다.
- 서버가 origin을 허용하는 것을 판단
- 그 후 본요청을 보낸다.

</br>

- Simple requests
  - 메서드
    - GET
    - HEAD
    - POST
  - 헤더
    - Accpet
    - Accept-Language
    - Content-Language
    - Content-Type
  - Content-Type
    - application/x-www-form-urlencoded
    - multipart/form-data
    - text/plain
  - XMLHttpRequestUpload 객체에는 이벤트 리스터가 등록 X
    - XMLHttpRequest.upload 프로퍼티를 사용하여 접근
    - 요청에 ReadableStream 객체 사용 X
