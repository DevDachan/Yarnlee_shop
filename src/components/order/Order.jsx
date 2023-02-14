import React, { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";


import Phone from "./Phone";
import PostSelector from "./PostSelector";


function Main(props) {
  const navigate = useNavigate();
  const [address, setAddress] = useState();
  const [zoneCode, setZonecode] = useState();

  const Wrapper = styled.div`
      padding: 16px;
      width: calc(100% - 32px);
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      margin: auto;
  `;

  return (
      <Wrapper>
        <div className="order_main" id="main">
          <div className="inner">
            <div className="order-grid">

              <div className="gr-2" id="imgDiv">
                <span className="image main order_span_img"><img className="order_img" src="images/pic13.jpg" alt="" /></span>
              </div>
              <div className="gr-10 calign">
                <h1>Circle Tote Bag</h1>
              </div>


              <div className="gr-4 calign pt1">
                <h2 className="mg0">주문자 이름</h2>
              </div>
              <div className="gr-8">
                <input type="text" id="ip_name"></input>
              </div>



              <div className="gr-4 calign pt1">
                <h2 className="mg0">전화번호</h2>
              </div>
              <div className="gr-8">
                <Phone / >
              </div>

              <div className="gr-6 calign mt3">
                <PostSelector
                  setAddress = {setAddress}
                  setZonecode = {setZonecode}
                />
              </div>
              <div className="gr-6">
                <input type="text" disabled id="zoneCode" value={zoneCode} />
              </div>
              <div className="gr-12">
                <input type="text" disabled id="address" value={address} />
              </div>
              <div className="gr-12">
                <input type="text" id="address_detail" placeholder="상세주소"/>
              </div>


              <div className="gr-12 calign pt3">
                <button className="bt_order" onClick={(e) => {}}> Order </button>
              </div>


            </div>
          </div>
        </div>

      </Wrapper>
  );
}

export default Main;
