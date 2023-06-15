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
import Nav from "./components/Nav";
import Main from "./components/Main";
import Content from "./components/detail/Content";
import Order from "./components/order/Order";
import SuccessOrder from "./components/order/SuccessOrder";
import Login from "./components/login/Login";
import Logout from "./components/login/Logout";
import Register from "./components/register/Register";
import SuccessRegister from "./components/register/SuccessRegister";
import UserInfo from "./components/register/UserInfo";

import OrderLogin from "./components/orderList/OrderLogin";
import OrderList from "./components/orderList/OrderList";
import OrderHistory from "./components/orderHistory/OrderHistory";
import NoticeMain from "./components/notice/NoticeMain";
import NoticeContent from "./components/notice/NoticeContent";

import AdminOrderList from "./components/admin/orderList/AdminOrderList";
import AdminOrderHistory from "./components/admin/orderHistory/AdminOrderHistory";
import AdminMain from "./components/admin/main/AdminMain";
import AdminLogin from "./components/admin/login/AdminLogin";
import AdminNoticeMain from "./components/admin/notice/AdminNoticeMain";
import AdminNoticeContent from "./components/admin/notice/AdminNoticeContent";
import AdminNoticeEdit from "./components/admin/notice/AdminNoticeEdit";
import AdminContent from "./components/admin/content/AdminContent";

// 사용자 정의 태그 생성하기
function App() {
  return (
    <BrowserRouter>
      <Nav />
      <Routes>
        <Route path="/" element={<Main />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/logout" element={<Logout />}></Route>
        <Route path="/content" element={<Content />}></Route>
        <Route path="/order" element={<Order />}></Route>
        <Route path="/successOrder" element={<SuccessOrder />}></Route>
        <Route path="/orderLogin" element={<OrderLogin />}></Route>
        <Route path="/orderList" element={<OrderList />}></Route>
        <Route path="/orderHistory" element={<OrderHistory />}></Route>
        <Route path="/noticeMain" element={<NoticeMain />}></Route>
        <Route path="/noticeContent" element={<NoticeContent />}></Route>

        <Route path="/register" element={<Register />}></Route>
        <Route path="/successRegister" element={<SuccessRegister />}></Route>
        <Route path="/userInfo" element={<UserInfo />}></Route>


        <Route path="/adminMain" element={<AdminMain />}></Route>
        <Route path="/adminOrderList" element={<AdminOrderList />}></Route>
        <Route path="/adminOrderHistory" element={<AdminOrderHistory />}></Route>
        <Route path="/adminLogin" element={<AdminLogin />}> </Route>
        <Route path="/adminContent" element={<AdminContent />}></Route>
        <Route path="/adminNoticeMain" element={<AdminNoticeMain />}></Route>
        <Route path="/adminNoticeContent" element={<AdminNoticeContent />}></Route>
        <Route path="/adminNoticeEdit" element={<AdminNoticeEdit />}></Route>

        <Route path="*" element={<NotFound />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
