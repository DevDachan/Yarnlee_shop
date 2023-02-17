import logo from './logo.svg';
import './App.css';

import React from "react";
import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";
import styled from "styled-components";

import NotFound from "./components/NotFound";
import Main from "./components/Main";
import Content from "./components/detail/Content";
import Order from "./components/order/Order";
import Login from "./components/login/Login";
import Register from "./components/register/Register";
import Nav from "./components/Nav";
import OrderList from "./components/orderList/OrderList";
import OrderHistory from "./components/orderHistory/OrderHistory";
import AdminOrderList from "./components/admin/orderList/AdminOrderList";
import AdminOrderHistory from "./components/admin/orderHistory/AdminOrderHistory";
import AdminMain from "./components/admin/main/AdminMain";



// 사용자 정의 태그 생성하기
function App() {
  return (
    <BrowserRouter>
      <Nav />
      <Routes>
        <Route path="/" element={<Main />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/content" element={<Content />}></Route>
        <Route path="/order" element={<Order />}></Route>
        <Route path="/orderList" element={<OrderList />}></Route>
        <Route path="/orderHistory" element={<OrderHistory />}></Route>

        <Route path="/register" element={<Register />}></Route>
        <Route path="/adminMain" element={<AdminMain />}></Route>
        <Route path="/adminOrderList" element={<AdminOrderList />}></Route>
        <Route path="/adminOrderHistory" element={<AdminOrderHistory />}></Route> 

        <Route path="*" element={<NotFound />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
