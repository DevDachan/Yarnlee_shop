import React, { useState, useEffect, useRef } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";

import Phone from "./Phone";
import PostSelector from "./PostSelector";
import Remittance from "./Remittance";

const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: auto;
`;



function Order(props) {
  const navigate = useNavigate();
  const location = useLocation();


  const [phoneNum, setPhoneNum] = useState();
  const [address, setAddress] = useState();
  const [zoneCode, setZonecode] = useState();
  const [imageId, setImageId] = useState();
  const [addressDetail, setAddressDetail] = useState();
  const productId = location.state.productId;
  const productName = location.state.productName;
  const color = location.state.color;
  const numberOfProduct = location.state.productNum;
  const deliveryCost = location.state.deliveryCost;
  const totalCost = numberOfProduct * location.state.productPrice + deliveryCost;

  const remittanceImage = useRef();

  const goBack = (e) =>{
    navigate(-1);
  }

  const order = (e) =>{
    const formData = new FormData();
    formData.append("id", 1);
    formData.append("orderDate", "2023-05-23");
    formData.append("userId", "guest");
    formData.append("productId", productId);
    formData.append("color", productId);
    formData.append("num", numberOfProduct);
    formData.append("totalCost", totalCost);
    formData.append("orderName", document.getElementById("ip_name").value);
    formData.append("orderPhone", phoneNum);
    formData.append("orderZoneCode", zoneCode);
    formData.append("orderAddress", address);
    formData.append("addressDetail", addressDetail);
    formData.append("imageId", 53);

    axios({
      method: "post",
      url: 'http://localhost:8090/shop-backend/order/insert',
      data: formData
    })
    .then(function (response){
      //handle success
      navigate('../', {
        state: {
          userName: "dachan"
        }
      });
    })
    .catch(function(error){
      //handle error
      console.log(error);
        navigate('./', {
        state: {
          userName: "dachan"
        }
      });
    })
    .then(function(){
      // always executed
    });
  }





  return (
      <Wrapper>
        <div className="order_main" id="main">
          <div className="inner mg0">
            <div className="grid_t">
              <div className="gr-2" id="imgDiv">
                <span className="image main order_span_img"><img className="order_img" src={"images/product"+productId+".jpg"} alt="" /></span>
              </div>
              <div className="gr-10 calign">
                <h1>{productName}</h1>
              </div>

              <div className="gr-12 mb2 grid_t" style={{boxShadow: "3px 3px 3px 3px rgb(98 217 182)", borderRadius:"20px"}}>
                <div className="gr-4 calign" style={{borderRight: "3px solid rgb(98 217 182)", paddingTop: "20px"}}>
                  <h2 style={{color: "#00db9a"}}> 주문 상세 </h2>
                </div>
                <div className="gr-8 calign" style={{paddingTop: "20px"}}>
                  <div>
                    {productName} | {color} | 수량 {numberOfProduct}
                  </div>
                </div>
                <div className="gr-12 calign" style={{borderTop: "3px solid rgb(98 217 182)", paddingTop: "20px"}}>
                  <p>상품 금액: {numberOfProduct * location.state.productPrice}원 , 배송비: {deliveryCost}원</p>
                  <h3> 총액 : {totalCost}원 </h3>
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
                <Phone phoneNum={phoneNum || ""} setPhoneNum={setPhoneNum}/ >
              </div>

              <div className="gr-4 calign mt3">
                <PostSelector
                  setAddress = {setAddress || ""}
                  setZonecode = {setZonecode || ""}
                />
              </div>
              <div className="gr-8 mt3">
                <input type="text" className="prl1"  disabled id="zoneCode" value={zoneCode} />
              </div>
              <div className="gr-12">
                <input type="text" className="prl1"  disabled id="address" value={address} />
              </div>
              <div className="gr-12">
                <input type="text" required className="prl1" id="address_detail" placeholder="상세주소" value={addressDetail}
                  onChange={(e) =>setAddressDetail(e.target.value)}
                />
              </div>

              <div className="gr-12 mt3">
                <Remittance
                  remittanceImage = {remittanceImage}
                  setImageId = {setImageId}
                />
              </div>

              <div className="gr-6 calign pt3">
                <button className="bt_order" onClick={goBack}> Back </button>
              </div>

              <div className="gr-6 calign pt3">
                <button className="bt_order" onClick={order}> Order </button>
              </div>

            </div>
          </div>
        </div>

      </Wrapper>
  );
}

export default Order;
