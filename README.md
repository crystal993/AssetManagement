# Smart Asset Management 

--------------------------

## 💸 스마트 자산 관리 가계부 

내 통장이 텅장이 되기 십상일 때! 🤣

알뜰한 소비 생활을 위한 스마트 자산 관리 가계부 

<hr>


## 💁 프로젝트 소개

- 🔍 **회원가입**을 통해 **스마트 자산 관리 가계부** 이용이 가능합니다.
- 🔍  **로그인**을 성공하면  **수입, 지출, 예산 관리 기능**을 쓸 수 있습니다.

- 🔍  **수입, 지출 등록**과 **삭제**를 통해 **수입내역, 지출내역 관리**가 가능합니다. 
- 🔍  **예산 설정**을 통해 보다 더 **알뜰한 소비생활**이 가능합니다.
- 🔍 **통계 기능**을 통해 내역 **항목별 총 수입, 지출 합계**, **비율**, **기간별 조회**가 가능합니다. 

<hr>


## 🧍‍♂️ USE CASE DIAGRAM

<hr>


**스마트 자산관리 가계부**는 총 **4개의 서브시스템**으로 이루어져 있습니다. **회원관리 서브시스템 , 수입관리 서브 시스템, 지출 관리 서브시스템 , 예산 관리 서브시스템**  4가지로 이루어져 있습니다.

#### 전체 시스템

![](https://images.velog.io/images/ggg5483/post/f50b22b4-5ecb-4d6f-bc9b-9b865889c4a6/UseCaseDiagram.jpg)

#### 회원 관리 서브 시스템
![](https://images.velog.io/images/ggg5483/post/92c71b05-6b84-4acf-99b3-b76d0de6d548/UseCaseDiagram%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%EC%84%9C%EB%B8%8C%EC%8B%9C%EC%8A%A4%ED%85%9C.jpg)


#### 예산 관리 서브 시스템
![](https://images.velog.io/images/ggg5483/post/f8c1c5c6-7ae5-4831-bcb5-e5130528297f/UseCaseDiagram1%EC%98%88%EC%82%B0%EA%B4%80%EB%A6%AC%EC%84%9C%EB%B8%8C%EC%8B%9C%EC%8A%A4%ED%85%9C.jpg)


#### 지출 관리 서브시스템

![](https://images.velog.io/images/ggg5483/post/5932d3b5-cabd-4156-839a-f90f7898b500/UseCaseDiagram1%EC%A7%80%EC%B6%9C%EA%B4%80%EB%A6%AC%EC%84%9C%EB%B8%8C%EC%8B%9C%EC%8A%A4%ED%85%9C.jpg)

#### 수입 관리 서브시스템
![](https://images.velog.io/images/ggg5483/post/44b5cafb-2a5c-4f08-8f34-11d261e6e4dc/UseCaseDiagram1%EC%88%98%EC%9E%85%EA%B4%80%EB%A6%AC%EC%84%9C%EB%B8%8C%EC%8B%9C%EC%8A%A4%ED%85%9C.jpg)


## 👩‍🏫 CLASS DIAGRAM
#### ver.0.0.0 설계
![](https://images.velog.io/images/ggg5483/post/ea7d8484-f924-4da0-80c3-5228dd490bdc/ClassDiagram%EC%88%98%EC%A0%95%EC%A0%84.jpg)

#### ver.1.0.0 콘솔
![](https://images.velog.io/images/ggg5483/post/933336d5-8c2f-4b92-9bf8-9070ff2d7061/ClassDiagram2.jpg)

=> 클래스 분리설계를 하지 못했다. 

#### ver.2.0.0 JDBC 연동
![](https://images.velog.io/images/ggg5483/post/6e863059-1a85-42fb-943e-5862099ff818/%EA%B0%80%EA%B3%84%EB%B6%80_jdbc_ClassDiagram1.jpg)

=> MVC Pattern 을 적용하여 DB테이블 당 하나의 dao클래스가 존재하도록 dao클래스는 singleton patern으로 설계했다. 


## 테이블 관계도
![](https://images.velog.io/images/ggg5483/post/17d7385c-c016-4fb5-b34e-a631fa18f2ab/FINANCIAL2.png)


## 📄 테이블 설명

## 📜 이 프로젝트의 기술 구현 방식

## ✨ Issues
