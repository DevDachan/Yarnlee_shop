import React from "react";

function Nav(props) {

  const nav = document.getElementById('nav-menu');

  //현재 session 값에 따라 로그인 혹은 로그 아웃을 추가하게 된다.
  if(sessionStorage.getItem("admin") != null && sessionStorage.getItem("admin") != undefined){
    const main = document.createElement('li');
    const mainA = document.createElement('a');
    mainA.href = '/';
    mainA.className = 'ft_bold';
    mainA.textContent = '메인';
    main.appendChild(mainA);

    const adminMain = document.createElement('li');
    const adminMainA = document.createElement('a');
    adminMainA.href = '/adminMain';
    adminMainA.className = 'ft_bold';
    adminMainA.textContent = '관리자 메인';
    adminMain.appendChild(adminMainA);

    const notice = document.createElement('li');
    const noticeA = document.createElement('a');
    noticeA.href = 'adminNoticeMain';
    noticeA.className = 'ft_bold';
    noticeA.textContent = '공지 사항';
    notice.appendChild(noticeA);

    const order = document.createElement('li');
    const orderA = document.createElement('a');
    orderA.href = 'adminOrderList';
    orderA.className = 'ft_bold';
    orderA.textContent = '주문 조회';
    order.appendChild(orderA);


    const logout = document.createElement('li');
    const logoutA = document.createElement('a');
    logoutA.href = 'logout';
    logoutA.className = 'ft_bold';
    logoutA.textContent = '로그 아웃';
    logout.appendChild(logoutA);


    nav.innerHTML = "";
    nav.appendChild(adminMain);
    nav.appendChild(main);
    nav.appendChild(notice);
    nav.appendChild(order);
    nav.appendChild(logout);
  }
  else if(sessionStorage.getItem("id") != null && sessionStorage.getItem("id") != undefined){
    const main = document.createElement('li');
    const mainA = document.createElement('a');
    mainA.href = '/';
    mainA.className = 'ft_bold';
    mainA.textContent = '메인';
    main.appendChild(mainA);


    const notice = document.createElement('li');
    const noticeA = document.createElement('a');
    noticeA.href = 'noticeMain';
    noticeA.className = 'ft_bold';
    noticeA.textContent = '공지 사항';
    notice.appendChild(noticeA);

    const order = document.createElement('li');
    const orderA = document.createElement('a');
    orderA.href = 'orderLogin';
    orderA.className = 'ft_bold';
    orderA.textContent = '주문 조회';
    order.appendChild(orderA);

    const info = document.createElement('li');
    const infoA = document.createElement('a');
    infoA.href = 'userInfo';
    infoA.className = 'ft_bold';
    infoA.textContent = '회원 정보';
    info.appendChild(infoA);

    const logout = document.createElement('li');
    const logoutA = document.createElement('a');
    logoutA.href = 'logout';
    logoutA.className = 'ft_bold';
    logoutA.textContent = '로그 아웃';
    logout.appendChild(logoutA);


    nav.innerHTML = "";
    nav.appendChild(main);
    nav.appendChild(notice);
    nav.appendChild(order);
    nav.appendChild(info);
    nav.appendChild(logout);

  }else{
    const main = document.createElement('li');
    const mainA = document.createElement('a');
    mainA.href = '/';
    mainA.className = 'ft_bold';
    mainA.textContent = '메인';
    main.appendChild(mainA);


    const notice = document.createElement('li');
    const noticeA = document.createElement('a');
    noticeA.href = 'noticeMain';
    noticeA.className = 'ft_bold';
    noticeA.textContent = '공지 사항';
    notice.appendChild(noticeA);

    const order = document.createElement('li');
    const orderA = document.createElement('a');
    orderA.href = 'orderLogin';
    orderA.className = 'ft_bold';
    orderA.textContent = '주문 조회';
    order.appendChild(orderA);

    const login = document.createElement('li');
    const loginA = document.createElement('a');
    loginA.href = 'login';
    loginA.className = 'ft_bold';
    loginA.textContent = '로그인';
    login.appendChild(loginA);


    nav.innerHTML = "";
    nav.appendChild(main);
    nav.appendChild(notice);
    nav.appendChild(order);
    nav.appendChild(login);
  }

}


export default Nav
