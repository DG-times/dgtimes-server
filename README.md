![facebook_cover_photo_2](https://user-images.githubusercontent.com/66009926/188244542-493a39d8-99c2-45b0-845d-9f3545e7d9e2.png)


<h2 align="center"> "키워드 검색 기반의 빠르고 간편한 뉴스 검색 및 분석 서비스" DG times 입니다. </h2>

<br/>

### 🐨 프로젝트 소개 영상

---

<p align="center">

<a href="https://youtu.be/vOECZVPOfe0">
  <img src="http://img.youtube.com/vi/vOECZVPOfe0/0.jpg" alt="Deploy">
</a>

</p>

<br/>

### 🐨 빠르고 간편한 뉴스 검색이 왜 필요할까요?

---

#### 현재 인터넷 상에는 수많은 정보와 데이터들이 모여 빅데이터를 이루고 있습니다. 사람들은 아날로그 대신 온라인 상의 공개된 전자도서, 인터넷뉴스로부터 빠르게 정보를 습득 할 수 있게 되었습니다. 

특히 뉴스 분야에서는 스마트기기와 컴퓨터를 통해 과거와는 비교 할 수 없을 만큼 편리하게 정보를 제공 및 접근 할 수 있게 되었지만, 데이터가 너무 많아 원하는 정보를 쉽고 빠르게 찾을 수 없습니다. 

따라서, 인터넷 상의 뉴스 정보들을 분석하고 사용자가 검색 키워드를 입력하면 빠르게 근접한 정보들을 제공하여 사용자가 쉽고 빠르게 정확한 데이터를 찾을 수 있도록 기여할 수 있습니다.

<br/>

### 🐨 DG times는 어떤 기능을 제공하나요?

---
**📌 Full Text Index를 활용하여 빠른 키워드 검색 구현**

- LIKE %keyword% 방식의 키워드 검색은 Index를 타지 않아 느림

- Full Text Index를 도입하여 검색 성능 향상(18s → 0.8ms)

<br/>

**📌 Redis를 활용한 캐싱으로 분석 결과 응답 구현**

- 뉴스 분석은 초, 분 단위의 변화로 결과 값에 영향을 주지 않고 반복되는 계산은 서버에 부하 발생

- Redis에 캐싱하여 응답 속도 향상(4s → 0.43ms) 및 부하 해소

<br/>

**📌 Main - Replica 구조를 활용하여 Database 부하 분산**

- 특정 시간대(오전 11시 ~ 오후 1시) 뉴스 조회 요청과 뉴스 주입 요청이 몰려 트래픽 부하가 심함

- DB를 Main - Replica 구조로 이분화하여 DB 부하를 분산시켜 성능 향상

<br/>

**📌 Word2Vec을 활용하여 빠르고 정확하게 연관 키워드 분석**

- 연관 키워드 분석을 위해 키워드 간의 유사도 분석이 필요

- Word2vec 모델을 사용하여 뉴스 내의 키워드 간의 유사도 분석 진행

<br/>

**📌 Rabbitmq & Scheduled를 활용하여 배치 처리 방식으로 뉴스 데이터 수집 성능 향상**

- Full Text Index 매핑으로 데이터 개별 삽입은 많은 시간이 소요되며 일괄로 처리하는 것이 성능이 더 좋음

- Rabbitmq로 뉴스 데이터를 모아 Scheduled를 사용하여 일정 시간마다 배치로 일괄 처리

<br/>

**📌 ALB를 활용하여 오토스케일링 방식으로 유동적인 트래픽에 효율적으로 대응**

- 한국언론진흥재단에 따르면 하루 뉴스 조회 요청 최대 최소 트래픽 차이가 약 5배 차이가 난다고 함

- 효율적인 인프라 관리를 위해 ALB의 오토스케일링 기능 도입

<br/>

### 🐨  시스템 구조도

---

<img width="600" alt="image" src="https://user-images.githubusercontent.com/66009926/190565188-a83a947d-0d10-4baa-b2e9-929b41dc07f6.jpg">


<br/>

### 🐨 ER Diagram

---

<img width="600" alt="image" src="https://user-images.githubusercontent.com/66009926/190562540-3073a6c3-58a7-4cee-86d9-d3c322bdd133.png">

<br/>

### 🐨 API 기술 문서

---


https://elderly-gruyere-ed2.notion.site/DGtimes-API-e1742d25040c4bebb70499673b4ec6af

<br/>



### 🐨 Commit 컨벤션
```
feat : 새로운 기능에 대한 커밋 
fix : 수정에 대한 커밋 
build : 빌드 관련 파일 수정에 대한 커밋
```

### 🐨 Github flow
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
