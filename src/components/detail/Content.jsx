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
  `;
  return (
      <Wrapper>
        <div className="detail_main" id="main">
          <div className="inner">
            <h1>Circle Tote Bag</h1>
            <div className="row detail_main_nav">

              <div className="col-sm-6" name="imgDiv">
                <span className="image main detail_span_img"><img className="detail_img" src="images/pic13.jpg" alt="" /></span>
              </div>

              <div className="col-sm-6" name="selectDiv">

                <div className="col-sm-12 row mg0 mb1" name="numberDiv">
                  <div className="col-5 ralign pd0">
                    <button className="bt_up_down" onClick={(e) => productUpDown(e,"down")}> {"<"} </button>
                  </div>
                  <div className="col-2 calign">
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
            <p>여기는 상품 설명이 들어갈 공간입니다.</p>
            <p>자유롭게 상품에 대한 설명을 적어주시면 됩니다.</p>
          </div>
        </div>

      </Wrapper>
  );
}

export default Main;
