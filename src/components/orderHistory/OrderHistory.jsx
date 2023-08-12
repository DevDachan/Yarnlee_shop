import React, { useState, useEffect, useRef } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import styled from "styled-components";


import Phone from "../order/Phone";
import PostSelector from "../order/PostSelector";


function OrderHistroy(props) {
  const navigate = useNavigate();
  const location = useLocation();
  const [address, setAddress] = useState();
  const [zoneCode, setZonecode] = useState();
  const remittanceImage = useRef();

  useEffect(() => {
    if (location.state == null) {
      navigate('../orderLogin');
    }
  }, [location, navigate]);

  if (location.state == null) {
     return null; // navigate 호출 후 컴포넌트의 렌더링을 중단
  }


  const orderDetail = location.state.orderDetail;
  const productDetail = location.state.productDetail;

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
              <div className="gr-2" id="">
                <span className="image main order_span_img"><img className="order_img" src="images/pic13.jpg" alt="" /></span>
              </div>
              <div className="gr-10 calign">
                <h1>{orderDetail.name}</h1>
              </div>

              <div className="gr-12 mb2 grid_t" style={{boxShadow: "3px 3px 3px 3px rgb(98 217 182)", borderRadius:"20px"}}>
                <div className="gr-4 calign" style={{borderRight: "3px solid rgb(98 217 182)", paddingTop: "20px"}}>
                  <h2 style={{color: "#00db9a"}}> 주문 상세 </h2>
                </div>
                <div className="gr-8 calign" style={{paddingTop: "20px"}}>
                  <div>
                    서클 토트백 | 그린 | 수량 1
                  </div>
                </div>
                <div className="gr-12 calign" style={{borderTop: "3px solid rgb(98 217 182)", paddingTop: "20px"}}>
                  <h3> 총액 : {orderDetail.totalCost}원 </h3>
                </div>
              </div>
            </div>

            <div className="grid_t" style={{padding: "30px", boxShadow: "3px 3px 3px 3px rgb(98 217 182)"}}>

              <div className="gr-4 calign pt1">
                <h2 className="mg0">주문자 이름</h2>
              </div>
              <div className="gr-8">
                <input type="text" disabled className="prl1" id="ip_name" defaultValue={orderDetail.orderName}></input>
              </div>



              <div className="gr-4 calign pt1">
                <h2 className="mg0">전화번호</h2>
              </div>
              <div className="gr-8">
                <input type="text" disabled className="prl1" id="ip_phone" defaultValue={orderDetail.orderPhone}></input>
              </div>

              <div className="gr-4 calign mt3">
                <h2 className="mg0">주소</h2>
              </div>
              <div className="gr-8 mt3">
                <input type="text" className="prl1"  disabled id="zoneCode" defaultValue={orderDetail.orderZonecode} />
              </div>
              <div className="gr-12">
                <input type="text" className="prl1"  disabled id="address" defaultValue={orderDetail.orderAddress} />
              </div>
              <div className="gr-12">
                <input type="text" required className="prl1" disabled id="address_detail" placeholder={orderDetail.addressDetail}/>
              </div>

              <div className="gr-12 mt3">
                <img className="detail_img" src={"http://104.198.11.59/userImage/"+orderDetail.imageId+".jpg"}
                alt=""
                />
              </div>
            </div>
            <div className="order_main" style={{padding: "30px", boxShadow: "3px 3px 3px 3px rgb(98 217 182)"}}>
              <div className="grid_t">
                <div className="gr-12 calign mt3 mb3">
                  <h2 > 주문 상태 </h2>
                </div>
                <div className="gr-12 mb3">
                  <h3>
                    <a href="https://www.cvsnet.co.kr/reservation-inquiry/delivery/index.do"
                    style={{color: "blue"}} target="_blank">
                    배송 조회
                    </a>
                  </h3>
                  <p>
                    기본 배송은 GS 편의점 택배를 이용해 배송이 됩니다. <br />
                    배송 관련 문의사항은 인스타그램 DM을 이용해주시기 바랍니다.
                  </p>

                </div>
                <div className="gr-6 calign">
                  <h3> 현재 상태 </h3>
                </div>
                <div className="gr-6">
                  <input type="text" value={orderDetail.state} disabled />
                </div>
                  {
                    orderDetail.parcelNum == null?
                    ""
                    :
                    <>
                    <div className="gr-6 mt3 calign">
                      <h3> 송장 번호 </h3>
                    </div>
                    <div className="gr-6 mt3 calign">
                      <input type="text" id="parcelNum" value={orderDetail.parcelNum} disabled/>
                    </div>
                    </>
                  }
              </div>
            </div>
            <div className="gr-12 calign pt3">
              <button className="bt_order" onClick={(e) => {navigate("../orderList")}}> 목록으로 </button>
            </div>
          </div>
        </div>
    </Wrapper>
  );
}

export default OrderHistroy;
