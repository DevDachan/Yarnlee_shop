


import React from "react";

function Nav(props) {


  return (
      <nav id="menu">
        <h2>Menu</h2>
        <ul>
          <li><a href="/" className="ft_bold">메인</a></li>
          <li><a href="content" className="ft_bold">상품</a></li>
          <li><a href="orderList" className="ft_bold">주문 목록</a></li>
          <li><a href="login" className="ft_bold">로그인</a></li>
        </ul>
      </nav>
  )
}


export default Nav
