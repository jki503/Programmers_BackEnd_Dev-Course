# Spring boot part4 JPA

jpa 장점은
객체의 필드가 수정된다 하더라도 쿼리를 수정할 필요가 없어서
객체 중심적인 개발이 가능해 생산성이 좋아진다.

객체와 RDB의 패러다임 불일치를 해결하여
객체지향 적인 개발이 가능

em에서 커밋이 일어날때 connection을 가져와서 사용

persist

변경 감지 -> dirty checking
lazy loading

detach 관리 안하겠다
clear 관리하고 있는 엔터티 모두 지우겠다
close 영속성 컨텍스트를 종료하면서

아 commitdl flush구나.. DB랑 동기화...
