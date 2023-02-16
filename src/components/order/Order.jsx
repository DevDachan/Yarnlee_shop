import React, { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";


import Phone from "./Phone";
import PostSelector from "./PostSelector";
import Remittance from "./Remittance";


function Main(props) {
  const navigate = useNavigate();
  const [address, setAddress] = useState();
  const [zoneCode, setZonecode] = useState();
  const remittanceImage = useRef();

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
          <div className="inner mg0">
            <div className="grid_t">
              <div className="gr-2" id="imgDiv">
                <span className="image main order_span_img"><img className="order_img" src="images/pic13.jpg" alt="" /></span>
              </div>
              <div className="gr-10 calign">
                <h1>Circle Tote Bag</h1>
              </div>

              <div className="gr-12 mb2 grid_t" style={{boxShadow: "3px 3px 3px 3px rgb(98 217 182)", borderRadius:"20px"}}>
                <div className="gr-4 calign" style={{borderRight: "3px solid rgb(98 217 182)", paddingTop: "20px"}}>
                  <h2 style={{color: "#00db9a"}}> 주문 상세 </h2>
                </div>
                <div className="gr-8 calign" style={{paddingTop: "20px"}}>
                  <div>
                    서클 토트백 | 그린 | 수량 1
                    서클 토트백 | 그린 | 수량 1
                  </div>
                </div>
                <div className="gr-12 calign" style={{borderTop: "3px solid rgb(98 217 182)", paddingTop: "20px"}}>
                  <h3> 총액 : 32000원 </h3>
                </div>
              </div>
            </div>

            <div className="grid_t" style={{padding: "30px", boxShadow: "3px 3px 3px 3px rgb(98 217 182)"}}>

              <div className="gr-4 calign pt1">
                <h2 className="mg0">주문자 이름</h2>
              </div>
              <div className="gr-8">
                <input type="text" className="prl1" id="ip_name"></input>
              </div>



              <div className="gr-4 calign pt1">
                <h2 className="mg0">전화번호</h2>
              </div>
              <div className="gr-8">
                <Phone / >
              </div>

              <div className="gr-4 calign mt3">
                <PostSelector
                  setAddress = {setAddress}
                  setZonecode = {setZonecode}
                />
              </div>
              <div className="gr-8 mt3">
                <input type="text" className="prl1"  disabled id="zoneCode" value={zoneCode} />
              </div>
              <div className="gr-12">
                <input type="text" className="prl1"  disabled id="address" value={address} />
              </div>
              <div className="gr-12">
                <input type="text" required className="prl1" id="address_detail" placeholder="상세주소"/>
              </div>

              <div className="gr-12 mt3">
                <Remittance
                  remittanceImage = {remittanceImage}
                />
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
