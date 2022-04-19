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
