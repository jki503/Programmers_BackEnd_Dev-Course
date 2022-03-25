# 실습 프로젝트

## 외부 라이브러리 사용하기

- gradel : Build Tools
  - 빌드 한다 / 실행 한다.
  - 외부에 의존성이 있는 라이브러리의 설치

mavenCentral 저장소가 jar 파일 찾아서 라이브러리 찾아옴
search.maven.org

- [Javafaker](https://search.maven.org/artifact/com.github.javafaker/javafaker/1.0.2/jar)

- [Lombok](https://projectlombok.org)

|                     |
| :-----------------: |
| @AllArgsConstructor |
|      @ToString      |
| @EqualsAndHashCode  |
|       @Getter       |
|       @Setter       |
| @NoArgsConstructor  |

## 설계

- 숫자야구 게임 설계
- 요구사항 파악하기
  - 게임의 룰을 이해 
  - 동작 환경, 데이터 범위
    - 3자리 숫자 사용
    - 중복된 숫자를 사용하지 않는다.
    - 1~9의 숫자 사용
    - console 상에서 동작하는 프로그램을 제작
- 일을 객체로 나누기/객체를 연관짓기
  - BaseBall
  - Input
  - Output
  - Console
  - NumberGenerator
- 핵심 로직 설계하기
- flowchart

Input -> Basball -> NumberGenerator
검증