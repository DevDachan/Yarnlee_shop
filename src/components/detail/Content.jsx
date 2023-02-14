import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

function Main(props) {
  const navigate = useNavigate();
  const [productNum, setProductNum] = useState(1);

  const productUpDown = (e,temp) =>{
    if(temp == "up"){
      setProductNum(productNum+1);
    }else{
      if(productNum > 1){
        setProductNum(productNum-1);
      }
    }
  }

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
        <div className="detail_main" id="main">
          <div className="inner">
            <h1>Circle Tote Bag</h1>
            <div className="row detail_main_nav">

              <div className="col-12-medium" id="imgDiv">
                <span className="image main detail_span_img"><img className="detail_img" src="images/pic13.jpg" alt="" /></span>
              </div>

              <div className="col-12-medium calign" name="selectDiv" style={{paddingTop: "20%"}}>

                <div className="col-12-medium mt2">
                  <h2>판매 가격: 32000 원</h2>
                  <h2>배송 도착 예정일 : 5일 후</h2>
                </div>

                <div className="col-12-medium row mg0 mb1" name="numberDiv">
                  <div className="col-5 ralign pd0">
                    <button className="bt_up_down" onClick={(e) => productUpDown(e,"down")}> {"<"} </button>
                  </div>
                  <div className="col-2 calign pd0">
                    <input type="number" className="number_input" id="product_num" onChange={(e) => setProductNum(e.target.value) } min="1" max="10" value={productNum}/>
                  </div>
                  <div className="col-5 lalign pd0">
                    <button className="bt_up_down" onClick={(e) => productUpDown(e,"up")}> {">"} </button>
                  </div>
                </div>

                <div className="col-sm-12 calign">
                  <button className="bt_order" onClick={(e) => {}}> Order </button>
                </div>
              </div>

            </div>
            <div style={{textAlign: "center"}}>
            <p>여기는 상품 설명이 들어갈 공간입니다.</p>
            <p>자유롭게 상품에 대한 설명을 적어주시면 됩니다.</p>
            </div>
          </div>
        </div>

      </Wrapper>
  );
}

export default Main;
