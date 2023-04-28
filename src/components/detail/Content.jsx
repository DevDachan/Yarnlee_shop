import React, { useState, useEffect, useRef } from "react";
import { useNavigate, useParams,useLocation } from "react-router-dom";
import styled from "styled-components";
import queryString from 'query-string';
import axios from "axios";

const Wrapper = styled.div`
  padding: 16px;
  width: calc(100% - 32px);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: auto;
`;


function Content(props) {
  const navigate = useNavigate();
  const { search } = useLocation();
  const {productId} = queryString.parse(search);
  const numberRef = useRef(1);
  const [product, setProduct] = useState();

  const productUpDown = (e, temp) => {
    if (temp === "up") {
      numberRef.current.value = Number(numberRef.current.value) + 1;
    } else {
      if (numberRef.current.value > 1) {
        numberRef.current.value = Number(numberRef.current.value) - 1;
      }
    }
  };

  const orderClick = () => {
    if (document.getElementById("select_color").value !== "") {
      navigate("/order", {
        state: {
          productId: 1,
          productNum: numberRef.current.value,
          productPrice: product.price,
          productName: product.name,
          deliveryCost: product.deliveryCost,
          color: document.getElementById("select_color").value,
        },
      });
    } else {
      document.getElementById("alert_p").innerText = "색상을 선택해주세요!";
    }
  };

  useEffect(() => {
      axios({
        method: "get",
        url: 'http://localhost:8090/shop-backend/product/select/id/'+productId
      })
      .then(function (response){
        //handle success
        setProduct(response.data);
      })
      .catch(function(error){
        //handle error
        console.log(error);
      })
      .then(function(){
        // always executed
      });
    },[]); //마지막에 아무 파라미터를 안넣어줌으로써 페이지가 처음 로드 될 때만 적용

  return (
    <Wrapper>
      <div className="detail_main" id="main">
        <div className="inner">
          <h1>{product == undefined ? "":product.name}</h1>
          <div className="row detail_main_nav">

            <div className="col-12-medium" id="imgDiv">
              <span className="image main detail_span_img"><img className="detail_img" src={"../images/product"+productId+".jpg"} alt="" /></span>
            </div>

            <div className="col-12-medium calign" name="selectDiv" style={{paddingTop: "10%"}}>

              <div className="col-12-medium mt2">
                <h2 className="calign">판매 가격: {product == undefined ? "":product.price}원</h2>
                <h2 className="calign">배송 예정일 : {product == undefined ? "":product.deliveryTime} </h2>
              </div>

              <div className="col-12-medium mb1">
                <select id="select_color" defaultValue="">
                  <option value="" disabled className="option_select">Color</option>
                  <option value="Green" className="option_select">Green</option>
                  <option value="Blue" className="option_select">Blue</option>
                  <option value="Yellow" className="option_select">Yellow</option>
                </select>
              </div>

              <div className="col-12-medium row mg0 mb1" name="numberDiv">
                <div className="col-5 ralign pd0">
                  <button className="bt_up_down" onClick={(e) => productUpDown(e,"down")}> {"<"} </button>
                </div>
                <div className="col-2 calign pd0">
                  <input type="number" className="number_input" id="product_num" min="1" max="10" ref={numberRef} defaultValue={numberRef.current} />
                </div>
                <div className="col-5 lalign pd0">
                  <button className="bt_up_down" onClick={(e) => productUpDown(e,"up")}> {">"} </button>
                </div>
              </div>

              <div className="col-12" id="alert_div">
                <p id="alert_p"></p>
              </div>

              <div className="col-sm-12 calign">
                <button className="bt_order" onClick={orderClick}> Order </button>
              </div>

            </div>

          </div>

          <div style={{textAlign: "center"}}>
            <p>{product == undefined ? "":product.detail}</p>
          </div>

        </div>
      </div>
    </Wrapper>
  );
}

export default Content;
