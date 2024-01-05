<img src="https://capsule-render.vercel.app/api?type=waving&color=auto&height=200&section=header&text=Yarnlee_Shop&fontSize=70" width=100% />


<div align='center'>    
	<a href="https://github.com/DevDachan/React_Shopping/graphs/contributors">    
	  <img src="https://img.shields.io/github/contributors/DevDachan/React_Shopping" alt="contributors" />      
	</a>    
	<a href="">   
	<img src="https://img.shields.io/github/last-commit/DevDachan/React_Shopping" alt="last update" />   
	</a>   
	<a href="https://github.com/DevDachan/React_Shopping/network/members">   
	<img src="https://img.shields.io/github/forks/DevDachan/React_Shopping" alt="forks" />   
	</a>   
	<a href="https://github.com/DevDachan/React_Shopping/stargazers">   
	<img src="https://img.shields.io/github/stars/DevDachan/React_Shopping" alt="stars" />   
	</a>   
	<a href="https://github.com/DevDachan/React_Shopping/issues/)">   
	<img src="https://img.shields.io/github/issues/DevDachan/React_Shopping" alt="open issues" />   
	</a>
</div>   



<div align='center'>
	<img src="https://img.shields.io/badge/SpringBoot-007396?style=flat&logo=SpringBoot&logoColor=white"/>
	<img src="https://img.shields.io/badge/React-007396?style=flat&logo=React&logoColor=white"/>
	<img src="https://img.shields.io/badge/JavaScript-007396?style=flat&logo=JavaScript&logoColor=white"/>
	<img src="https://img.shields.io/badge/MariaDB-4479A1?style=flat&logo=MariaDB&logoColor=white"/>
	<img src="https://img.shields.io/badge/Apache Tomcat-F8DC75?style=flat&logo=Apache Tomcat&logoColor=white"/>
	<img src="https://img.shields.io/badge/JQuery-0769AD?style=flat&logo=JQuery&logoColor=white"/>
	<img src="https://img.shields.io/badge/HTML-007396?style=flat&logo=HTML5&logoColor=white"/>
	<img src="https://img.shields.io/badge/CSS-F43059?style=flat&logo=CSS Wizardry&logoColor=white"/>
	<img src="https://img.shields.io/badge/Bootstrap-F43059?style=flat&logo=Bootstrap&logoColor=white"/>
	
</div>
<br/>

## :computer: 프로젝트 소개 \(개인\)
**실제 운영을 위한 개인 쇼핑몰 서비스입니다.**

<br/>

#### 페이지 미리보기 [PAGE.md](https://github.com/DevDachan/Shopping_m/blob/main/PAGE.md)
#### 쇼핑몰 바로가기 [Yarnlee](http://yarnlee.shop)
<br/>


# 🗓️ 프로젝트 기간

### 2023.03.05 ~ 2023.06.26

# 🌟 프로젝트 설명

## 주제

- **개인 쇼핑몰 웹 서비스를 구현, 쇼핑몰에서는 주소 검색, 주문 이후 문자 발송, 문의 내역 관리와 같은 기능을 위해 여러 오픈 API를 사용해 주문을 위한 여러 기능들을 포함해야 한다.**

## 기획 배경

- 사용자가 특정 물건을 판매하고 싶지만 긱몬, Witchform과 같은 서비스들은 회원가입등이 필요하고 다른 서비스의 경우 수수료가 너무 많이 들어 개인이 관리 할 수 있는 서비스가 있었으면 좋겠다고 해서 프로젝트를 기획하게 됐습니다.
- Spring Boot 와 React 개발을 통한 직접 전체 웹 서비스를 구현해보기 위해 개발했습니다.
- 특정 예제 템플릿 없이 처음부터 끝까지 프론트엔드, 백엔드 작업을 혼자 개발해보기 위한 프로젝트입니다.

## 서비스 대상

- 개인 쇼핑몰을 운영하고 싶은 사람.
- 다양한 오픈 API를 활용한 기능이 필요한 사람.

## 서비스 목적

- 쇼핑몰을 관리하며 사람들이 쉽게 주문이 가능하도록 해야 한다.
- 관리자가 상품 관리를 위한 쉬운 인터페이스가 제공이 되어야 한다.
- 서버-클라이언트 구조로 Spring Boot와 React를 사용함으로써 각각의 기능을 분리 시킨다.


## 서비스 기능

### 사용자
- 회원 가입 및 로그인
- 상품 상세 정보
- 주문
- 주문 내역 확인
- 공지사항 확인

### 관리자
- 상품 등록 및 내용 수정
- 메인 화면 수정
- 전체 주문 내역 확인
- 공지사항 수정
- 전체 조회수 확인


## 사용 API
- 다음 주소API
- 네이버 클라우드 API

## 초기 디자인
![Untitled](https://github.com/DevDachan/Yarnlee_shop/assets/111109411/ca990348-536e-4e20-a74c-f2426b6e5296)
- 전체적인 디자인이 아닌 각각의 페이지에 어떤 기능들을 넣을지 구상한 내용입니다.
<br/>

## 서버

### Controller
| 이름 |  설명  | 제공 API |
|------|---|---|
| Admin |  관리자가 사용하는 API | adminLogin(),  getAllContent(), getMainContent(), getOrderContent(), editMainContent(), editOrderContent()|
| Notice | 공지사항과 관련된 API |  getNoticeList(),  getNotice(),  getNoticeAdminList(),  getNoticeAdmin(), createNotice(),  uploadImage(),changeContent(), changeTitle(),  deleteNotice(), upHits()|
| Order |  주문 사항에 대한 API |  insertOrder(), editProduct(),  changeParcelNum(), getOrder(),   getOrderHistory(),  getOrderHistoryToken(),  uploadImage(),  getAdminOrderList(), getAdminOrderList(), changeOrderState(), sendSms(), deleteOrder() |
| Phone |  핸드폰 인증과 관련해서 관리합니다 | sendSms(),  registerPhone(),  checkDup(),  checkKey(),checkAuth(),   sendMessage()|
| Product | 상품에 관련한 CRUD에 대한 API | getProduct(),  getAdminProduct(), changeColor(),   changeColorPrice(), insertColor(),  deleteColor(),  deleteProduct(),   changeName(), changePrice(), changeDeliveryTime(), changeState(), changeDeliveryCostHalf(), changeDeliveryCostGeneral(),   changeSubDetail(), changeDetail(),  getProductList() , getAdminProductList() , createProduct(), changePosition(), uploadImage(), uploadMainImage()|
| Review | 리뷰에 관련한 API | |
| User |  사용자에 대한 API | loginUser(),register(),   auth(), infoUser(),  checkIdDup(), checkPhoneDup()|

### Data
| 이름 |  설명  |
|------|---|
| Admin |  관리자 | 
| Hits |  조회수 |
| Image | Image 이름  |
| Jasy |  Jasy 사용자 데이터 암호화|
| Notice | 공지사항 |
| Order |  주문 사항|
| Phone |  핸드폰 인증|
| Product | 상품 정보 |
| Review | 리뷰 정보 |
| SMS |  문자전송 |
| User |  사용자 정보 |

## 프론트엔드 구조

| 이름 |  설명  | 
|------|---|
| admin |  관리자 페이지  |
| detail |   상품 상세 정보 페이지 |
| login  | 로그인 관련 페이지 |
| notice |  공지사항 관련 페이지  |
| order |  주문 페이지 |
| orderList |  주문 조회 페이지 |
| orderHistory | 주문 조회 상세페이지  |
| register |  회원 가입 페이지 |


## 기타 업데이트 사항
1. 조회수 추가 (2023.07.24)
2. 상품 품절 상태관리 추가 (2023.07.27)
4. 상품 색상별 개별 가격 관리 추가(2023.08.12)
5. 유튜브 영상 임베딩 추가(2023.08.13)
6. JWT Token 적용 (2023.08.15)
