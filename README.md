# health-club-java
- 스프링 구조를 구현한 자바 프로젝트 리팩토링
- 최초 리팩토링 : 2024-08-02(금) ~ 2024-09-28(토)
- 2nd 리팩토링 :
  
## 1. 프로젝트 목적
### 주 목적
- [x] java8 -> java17로 버전 향상시켜 lamda 및 stream 기능 구현
    - 회원 등록, 수정 & 사물함 등록, 수정 입력 값 검증 요소에 사용
- [x] 소프트웨어 아키텍처 패턴인 MVC 구조 개선
    - WebContainer, dispatcherServlet 구조 구현
- [x] 사용자 편의성 및 예외처리 개선
    - Controller 단에서 Exception을 모아서 처리하도록 구조 변경
    - 회원 및 사물함 목록에서 페이징 처리를 추가하여 편의성 제공
    - 등록 및 수정 팝업 중간 종료 및 skip 기능 추가
- [x] Java로 DB 구현
    - LinkedHashMap을 활용하여 DB table 인스턴스 구현
    - Connection을 따로 두지않고 각 DAO에서 DB table을 조회하도록 구성
    - DAO에서 sql 쿼리와 유사하게 동작하도록 로직 구성
- [x] 추가 작업
    - 싱글톤 객체 구현
    - 검증을 위한 class를 따로 생성 및 리팩토링하여 검증기능 모듈화 (Validator.java)
    - 등록, 수정 팝업에서 입력값 검증, 중간 종료, 스킵 기능을 공통 처리하는 모듈 클래스 생성 (ViewCore.java)
    - @Controller 기능을 수행하는 bean.properties, 메시징 처리를 하는 message.properties 파일을 두고 해당 properties 파일을 처리하는 모듈 클래스 구현 (MessageSource.java)

### 부수 효과
- git & github 활용도 형상
  - 예) 깃허브 - Issues 사용, 깃 명령어 및 컨벤션 가이드 적용
- eclipse 활용도 향상
  - 예) 단축키 적용, git 연동

## 2. 프로젝트 설명
- 헬스장 프로젝트 (회원 CRUD, 사물함 CRUD, 회원 통계) 기능 구현

## 3. 차후 리팩토링
- Spring FrameWork 및 WebContainer 구조 고도화 (각 파일에 TODO 존재)
  - 추가적으로 비동기 통신을 할 수 있도록 구조 추가 - 아이디 중복 확인과 같은 기능에 사용 (후순위)
  - AOP 기능 구현 (후순위)
- ModelView를 params로 받도록 구조 변경
- 통계 작업 시, 람다 스트림을 활용하여 추가 기능 구현 (람다 스트림 적용 연습)
- Collection 클래스 작업을 Stream으로 처리하도록 학습 팔요 (자료구조에서 공통으로 사용하는 추상 클래스를 다루는 능력 향상 목표)
