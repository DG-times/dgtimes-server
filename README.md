# server

### 시스템 구조도

<img width="821" alt="Untitled" src="https://user-images.githubusercontent.com/66009926/185722322-7894e8c9-1e14-406a-a45f-7ea8782c7472.png">

### 프로젝트 목표

- 카테고리나 신문사 별로 뉴스를 보는것이 아닌, 사용자가 궁금해할만한 키워드로 검색을 해서, 해당 뉴스 구독, 모아보기를 할 수 있는 사이트를 만들려고 한다.

- 게시글 검색을 할 때, LIKE "%keyword%"를 많이 사용하게 되는데, 이 LIKE "%keyword%" 는성능이 좋지 못하다. 따라서 우리는 키워드 추출 및 매핑 테이블 전략으로 검색 속도를 향상시켜, 서비스를 제공해주는게, 서비스 목표이다.

### UI / UI

#### 메인
<img width="1440" alt="image" src="https://user-images.githubusercontent.com/66009926/185722667-c2681671-db4a-463c-999e-c623c103ffb2.png">

#### 로그인
<img width="1440" alt="image" src="https://user-images.githubusercontent.com/66009926/185722672-ac519b88-376c-486d-ba5b-283a61d2cbff.png">

#### 회원가입
<img width="1440" alt="image" src="https://user-images.githubusercontent.com/66009926/185722679-adcec99b-6cfd-4847-9b83-b42ca1802cda.png">

#### 검색 결과
<img width="1440" alt="image" src="https://user-images.githubusercontent.com/66009926/185722686-79f8bd89-4351-43f1-ab72-539524af09bc.png">


### 기술적 이슈

Exception 설계 및 구축하기

https://elderly-gruyere-ed2.notion.site/Exception-f6c84e77998e4eaca9b10fb1cc7cc8ac

MySQL Visual  Explain을 이용한 Query 성능 테스트

https://elderly-gruyere-ed2.notion.site/MySQL-Visual-Explain-Query-93d529c44a9d467da5912ddaf5df9935

Logging 정의와 동작원리

https://elderly-gruyere-ed2.notion.site/Logging-1-a1d74ec218fa42a7a5a41410778678a0

Logging System 구축하기

https://elderly-gruyere-ed2.notion.site/Logging-System-2-089871a42cfb4b03a1ac3bf27e319f02

뉴스 데이터 수집 및 가공하기

https://elderly-gruyere-ed2.notion.site/9dc4b6c2da6e4cc284bbdd703ad83feb

단위 테스트 도입기

https://elderly-gruyere-ed2.notion.site/5afc6adc51d74add9b3f26f6de43520b

부하테스트 설계

https://elderly-gruyere-ed2.notion.site/8a84be3e62904fd5bec8ba33c2d9bd23

### ERD

![Untitled](https://user-images.githubusercontent.com/66009926/185722355-ba566b09-14b8-4540-874a-ab26d8e8acf3.png)

### Commit 컨벤션
```
feat : 새로운 기능에 대한 커밋 
fix : 수정에 대한 커밋 
build : 빌드 관련 파일 수정에 대한 커밋
```

### Github flow
```
#Branch naming rules
main
항상 보호되는 안정된 브랜치

dev
새 버전 준비를 위한 개발 브랜치

hotfix/#notification_list
이슈 해결을 위한 브랜치, 기능 명을 기입하여 구분

feature/#notification_list
기능 추가/제거를 위한 브랜치, 기능명을 기입하여 구분

#Contribute method
1. Main 브랜치는 항상 안정된 빌드이자 사용자에게 서비스중인 빌드입니다.
2. 모든 feature 브랜치는 dev 브랜치에서 클론한다.
3. feature 브랜치는 기능 단위로 구분한다.
4. feature 작업이 끝나면 dev branch로 pull request 한다.
5. dev branch에서 모든 취합이 끝나면 main으로 pull request 한다.
6. merge 후 브랜치는 삭제한다.
```
