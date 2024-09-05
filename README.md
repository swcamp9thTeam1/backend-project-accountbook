
# 🐷 Save Pig (세이브 피그)
![표지](https://github.com/user-attachments/assets/c6c58a25-5d6e-4f9f-b6bf-2b798c7ce00b)
> 1조: 장호정, 양현진, 박경희, 노다민, 윤채연, 김시우
<br>

## 기획 배경
![기획 소개 - 현재 소비 트랜드 (1)](https://github.com/user-attachments/assets/e7d68e5d-4d2f-483d-97a9-dd5a161aac40)
> 평균 점심 식사 가격 만원 시대! 들어오는 나의 급여, 용돈은 적은데.. 합리적인 소비를 할 수 있는 방법이 없을까? 있다면 어디에서 찾을 수 있을까?<br>
> => 같은 목적(절약하고자 하는 의지)을 가진 유저들끼리 모여 착한 소비를 찾아 실천해보자!<br>

## • 서비스 소개 및 차별점
![서비스 소개 - 차별점 (1)](https://github.com/user-attachments/assets/bec35d92-00f3-4b54-b81e-a9c5934a7db0)
> 그룹을 만들어 서로의 가계부 공유한다
* 비슷한 목적을 가진 회원들끼리 그룹을 만들어 다른 그룹원의 착한 소비를 구경할 수 있습니다.<br>

> 착한가격업소 목록을 지도에서 찾기
* 행정안전부에서 제공하는 [착한가격업소](https://www.goodprice.go.kr/) 목록을 확인하여 착한 가격의 가게를 검색할 수 있습니다.<br>

> 다른 사람의 남긴 가게 리뷰 확인하기
* 가게에 대한 리뷰를 작성할 때 총 인원수/총 지출금액/한줄리뷰를 남길 수 있고, 세이브피그는 입력한 리뷰를 바탕으로 1인당 평균 소비를 계산하여 지도에 표시합니다.
* 각자 자신 만의 판단하에 적절한 금액이라고 생각되는 가게를 찾아볼 수 있습니다.<br>

# ☑️ DDD - Bounded Context

> Domain-Driven Design (도메인 주도 설계)<br>
> - 이번에는 기획 단계에서 도메인 주도 설계 기법을 도입하여 세이브피그에 사용되는 도메인을 추출하였습니다.

## • 회원, 자산, 고정지출 Context
![image](https://github.com/user-attachments/assets/f1929515-ec31-494b-a091-d48b2f75982b)

> - 회원: 세이브피그의 유저들<br>
> - 자산: 가계부를 작성할 때 이용된 유저의 자산 (계좌, 카드 등)<br>
> - 고정지출: 고정으로 지출되는 항목 (OTT 서비스 구독료, 주거 관리비 등)<br>

## • 가계부 내역, 가계부 카테고리, 가계부 댓글 Context
![image](https://github.com/user-attachments/assets/931207c5-bb0c-4390-aecc-386c0a398cd0)

> - 가계부: 회원의 수입/지출/이체 내역<br>
> - 가계부 카테고리: 수입 카테고리 - 급여, 금융이자, 지출 카테고리 - 식비/관리비/통신비 (회원마다 본인의 가계부 카테고리를 꾸려나갈 수 있습니다.)<br>
> - 가계부 댓글: 가계부에 달린 댓글 (그룹 멤버가 남긴 댓글 or 본인이 메모성으로 남긴 댓글)<br>

## • 가게, 가게 리뷰, 리뷰 첨부파일 Context
![image](https://github.com/user-attachments/assets/feea9e58-232b-4efe-a3c6-5749b2ccc1a8)

> - 가게: 세이브피그에서 관리하는 가게 목록 (착한가격 업소도 포함)<br>
> - 가게 리뷰: 가게에 작성된 회원들의 리뷰<br>
> - 리뷰 첨부파일: 리뷰 작성 시 사용된 파일<br>

## • 그룹, 그룹 게시글, 댓글, 첨부파일 Context
![image](https://github.com/user-attachments/assets/b85700db-c93f-456d-a7b1-2bfd2f730357)

> - 그룹: 가계부를 공유할 수 있는 회원 모임의 단위<br>
> - 그룹 게시글: 그룹 멤버들끼리 공유되는 작은 게시판(커뮤니티)<br>
> - 그룹 게시글 댓글: 그룹 게시글에 달린 댓글 (대댓글 포함)<br>
> - 그룹 게시글 첨부파일: 그룹 게시글에 사용된 파일<br>

## • 커뮤니티 게시글, 댓글, 첨부파일 Context
![image](https://github.com/user-attachments/assets/e09aa09d-1bf4-4bcc-aa17-618708470627)

> - 커뮤니티 게시글: 세이브피그의 전체 회원들에게 공유되는 게시판(커뮤니티)<br>
> - 커뮤니티 댓글: 커뮤니티 게시글에 달린 댓글 (대댓글 포함)<br>
> - 커뮤니티 첨부파일: 커뮤니티 게시글에 사용된 파일<br>
---
<br>

# ☑️ DDD - 연관 관계
![image](https://github.com/user-attachments/assets/2071d817-f6c3-4420-9ce9-e15e8107f8eb)

---
<br>

# WBS

# ☑️ 개념 모델링
![image](https://github.com/user-attachments/assets/fe4cad2b-75e3-4398-b30d-a5b36ea99d16)

---
<br>

# ☑️ 논리 모델링
![공유가계부_논리모델링](https://github.com/user-attachments/assets/fcbe4413-7e92-4e06-93d0-9e1343d31051)

---
<br>

# ☑️ 물리 모델링
![공유가계부_물리모델링](https://github.com/user-attachments/assets/669cd20f-f56e-43e7-8eea-b0ce8a3eae9e)

# 테이블 DDL

# 개발적 측면 특징들

## 1. 서버 구조
![Slide 16_9 - 26](https://github.com/user-attachments/assets/80099bf6-80d2-4ad4-9a72-5849a79ea98e)

> - 회원/가계부/나머지 도메인 3개의 마이크로서비스<br>
> - MariaDB 데이터베이스<br>
> - Eureka 서버와 Gateway 서버<br>

## 2. 공통으로 사용될 응답(Response) 클래스
![Slide 16_9 - 19](https://github.com/user-attachments/assets/c3777d92-5547-41f9-b211-a0b939898f95)

> - API 응답을 내보낼 때 정해진 틀로 응답을 보내기 위한 클래스<br>
> - 추후 유지보수 관점에서 용이하다 생각되어 제작<br>

## 3. 커스텀 예외(Exception) 클래스
![Slide 16_9 - 22](https://github.com/user-attachments/assets/4a1e9372-4ef1-426a-bbc7-a41473722851)

> - 예외 상황 케이스 별로 Exception 클래스를 제작하여 추후 오류 발생에 대한 빠른 대응 가능<br>
> - 이 또한 유지보수 관점에서 용이하다 생각되어 제작<br>

## 4. FeignClient를 통한 다른 마이크로서비스와의 통신

![Slide 16_9 - 30](https://github.com/user-attachments/assets/ad176065-c75f-4676-845e-fb337ee74ae7)    
![Slide 16_9 - 32](https://github.com/user-attachments/assets/552b49b1-4d68-41d3-a9b8-bc47e3c287a1)    

> - 가계부 등록 기능을 구현할 때 FeignClient를 이용하여 다른 마이크로서비스의 조회 Controller를 호출<br>

# 동료 평가
```
<<장호정>>
```
```
<<양현진>>
```
```
<<박경희>>
```
```
<<노다민>>
```
```
<<윤채연>>
```
```
<<김시우>>
```
