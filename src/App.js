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



// 사용자 정의 태그 생성하기
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main />}></Route>
        <Route path="/content" element={<Content />}></Route>
        <Route path="/order" element={<Order />}></Route>
        <Route path="*" element={<NotFound />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
