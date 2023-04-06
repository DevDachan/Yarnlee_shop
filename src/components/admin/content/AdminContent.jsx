import React, { useState, useEffect, useRef } from "react";
import { useNavigate, useParams } from "react-router-dom";
import styled from "styled-components";
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


function AdminContent(props) {
  const navigate = useNavigate();
  //const [productNum, setProductNum] = useState(1);
  const numberRef = useRef(1);
  const {productId} = useParams();
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
          <input type="text" className="ip-admin-content-head" defaultValue={product == undefined ? "":product.name}></input>
          <div className="row detail_main_nav">

            <div className="col-12-medium" id="imgDiv">
              <span className="image main detail_span_img"><img className="detail_img" src={"../images/product"+productId+".jpg"} alt="" /></span>
            </div>

            <div className="col-12-medium calign grid_t" name="selectDiv" style={{paddingTop: "10%"}}>
              <div className="gr-6 mt2">
                  <span>판매 가격: </span>
              </div>

              <div className="gr-6 mt2">
                  <input type="text" className="ip-admin-content-info" defaultValue={product == undefined ? "":product.price}></input>
              </div>
              <div className="gr-6 mt2">
                  <span>배송 예정일 : </span>
              </div>

              <div className="gr-6 mt2">
                  <input type="text" className="ip-admin-content-info" defaultValue={product == undefined ? "":product.deliveryTime}></input>
              </div>

              <div className="gr-12 mb1">
                <select id="select_color" defaultValue="">
                  <option value="" disabled className="option_select">Color</option>
                  <option value="Green" className="option_select">Green</option>
                  <option value="Blue" className="option_select">Blue</option>
                  <option value="Yellow" className="option_select">Yellow</option>
                </select>
              </div>

              <div className="gr-12 grid_t mg0 mb1" name="numberDiv">
                <div className="gr-5 ralign pd0">
                  <button className="bt_up_down" onClick={(e) => productUpDown(e,"down")}> {"<"} </button>
                </div>
                <div className="gr-2 calign pd0">
                  <input type="number" className="number_input" id="product_num" min="1" max="10" ref={numberRef} defaultValue={numberRef.current} />
                </div>
                <div className="gr-5 lalign pd0">
                  <button className="bt_up_down" onClick={(e) => productUpDown(e,"up")}> {">"} </button>
                </div>
              </div>

              <div className="gr-12" id="alert_div">
                <p id="alert_p"></p>
              </div>

              <div className="gr-12 calign">
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

export default AdminContent;
