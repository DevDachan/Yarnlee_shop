<img src="https://capsule-render.vercel.app/api?type=waving&color=auto&height=200&section=header&text=Shopping-SpringBoot&fontSize=70" width=100% />


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
**다음 주소찾기 API, 문자 전송 API등 오픈 API를 사용해 만들어진 개인 쇼핑몰 서비스입니다.**

<img src="https://user-images.githubusercontent.com/111109411/230763151-8a9e4331-6210-41ad-b6d3-0fc18b54bdcb.png" width=70%>

<img src="https://user-images.githubusercontent.com/111109411/228116610-551bbe11-6930-4e63-a223-67162b676135.png" width=70%>

<img src="https://user-images.githubusercontent.com/111109411/230763153-c666fbfd-e619-4911-a077-59dcd7eba5f7.png" width=70%>

  
  


# 🗓️ 프로젝트 기간

### 2023.03.05 ~ 2023.06.26

# 🌟 프로젝트 설명

## 주제

- **개인 쇼핑몰 웹 서비스를 구현, 쇼핑몰에서는 주소 검색, 주문 이후 문자 발송, 문의 내역 관리와 같은 기능을 위해 여러 오픈 API를 사용해 주문을 위한 여러 기능들을 포함해야 한다.**

## 기획 배경

- 사용자가 특정 물건을 판매하고 싶지만 긱몬, Witchform과 같은 서비스들은 회원가입등이 필요하고 다른 서비스의 경우 수수료가 너무 많이 들어 개인이 관리 할 수 있는 서비스가 있었으면 좋겠다고 해서 프로젝트를 기획하게 됐습니다.
- Spring Boot 와 React 개발을 통한 직접 전체 웹 서비스를 구현해보기 위해 기획하게 됐습니다.
- 특정 예제 템플릿 없이 처음부터 끝까지 프론트엔드, 백엔드 작업을 혼자 개발해보기 위한 프로젝트입니다.

## 서비스 대상

- 개인 쇼핑몰을 운영하고 싶은 사람
- 다양한 오픈 API를 활용한 기능이 필요한 사람.

## 서비스 목적

- 쇼핑몰을 관리하며 사람들이 쉽게 주문이 가능하도록 해야 한다.
- 관리자가 상품 관리를 위한 쉬운 인터페이스가 제공이 되어야 한다.
- 서버-클라이언트 구조로 Spring Boot와 React를 사용함으로써 각각의 기능을 분리 시킨다.
- 다양한 API를 사용해 고객으로부터 받는 데이터를 쉽게 관리한다


## 서비스 기능

### 사용자
- 회원 가입 및 로그인
- 주문
- 주문 내역 확인

### 관리자
- 상품 등록 및 내용 수정
- 메인 화면 수정
- 전체 주문 내역 확인
- 회원 정보 확인


## 사용 API
- 다음 주소API
- 네이버 클라우드 문자 전송API


[초기 기능 디자인 Figma](https://www.figma.com/file/ndJjGc6edEYel3tvo5ZsKr/Untitled?node-id=1%3A2&t=K6SYZlhiHiuidXpb-1)
- 전체적인 디자인이 아닌 각각의 페이지에 어떤 기능들을 넣을지 구상한 내용입니다.

<br/>


## 컴포넌트 구조
```
components
|
|   Main.jsx
|   Nav.jsx
|   NotFound.jsx
|
+---admin
|   +---content
|   |       AdminContent.jsx
|   |
|   +---main
|   |   |   AdminMain.jsx
|   |   |
|   |   \---list
|   |           AdminItemList.jsx
|   |
|   +---orderHistory
|   |       AdminOrderHistory.jsx
|   |
|   \---orderList
|           AdminOrderList.jsx
|
+---detail
|       Content.jsx
|
+---list
|       Item.jsx
|       ItemList.jsx
|
+---login
|       Login.jsx
|
+---order
|       Order.jsx
|       Phone.jsx
|       PostSelector(past).jsx
|       PostSelector.jsx
|       Remittance.jsx
|
+---orderHistory
|       OrderHistory.jsx
|       OrderLogin.jsx
|
+---orderList
|       OrderList.jsx
|
\---register
        Register.jsx
```


## Spring Boot 

```
+---dao
|   |   OrderDAO.java
|   |   ProductDAO.java
|   |   UserDAO.java
|   |
|   \---Impl
|           OrderDAOImpl.java
|           ProductDAOImpl.java
|           UserDAOImpl.java
|
+---dto
|       AdminDTO.java
|       ImageDTO.java
|       OrderDetailDTO.java
|       OrderDTO.java
|       ProductColorDTO.java
|       ProductDTO.java
|       UserDTO.java
|
+---entity
|       AdminEntity.java
|       BaseEntity.java
|       ImageEntity.java
|       OrderDetailEntity.java
|       OrderEntity.java
|       ProductColorEntity.java
|       ProductEntity.java
|       UserEntity.java
|
+---handler
|   |   OrderDataHandler.java
|   |   ProductDataHandler.java
|   |   UserDataHandler.java
|   |
|   \---Impl
|           OrderDataHandlerImpl.java
|           ProductDataHandlerImpl.java
|           UserDataHandlerImpl.java
|
+---repository
|       OrderRepository.java
|       ProductRepository.java
|       UserRepository.java
|
\---service
    |   OrderService.java
    |   ProductService.java
    |   UserService.java
    |
    \---Impl
            OrderServiceImpl.java
            ProductServiceImpl.java
            UserServiceImpl.java
```

### Controller

- HTTP 통신과 관련된 메소드를 관리하는 계층
- Service계층의 데이터를 프론트 엔드 측으로 넘겨준다.


### Service

- 직접적인 사용자에게 반환한 데이터를 만드는 계층으로써 상품의 정보를 DB로부터 불러와 가공해 controller로 전달하는 계층

### Data Handler

- DAO 계층으로부터 Entity 형식의 데이터를 받아와 DTO형태의 데이터로 가공하는 계층
- 주로 데이터 분석, 변환 작업등을 담당한다

### DAO

- DB로부터 데이터를 직접적으로 받아오는 작업을 담당하는 계층


### Repository

- DB에 직접적인 쿼리문을 수행해 결과를 받아오는 계층
- 해당 서비스에서는 jpa를 사용해 쿼리메소드 기능을 함께 사용한다


