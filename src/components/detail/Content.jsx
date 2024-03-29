import React, { useState, useEffect, useRef } from "react";
import { useNavigate, useParams,useLocation } from "react-router-dom";
import styled from "styled-components";
import queryString from 'query-string';
import axios from "axios";

const Wrapper = styled.div`
  padding: 4em 2.5em 0 2.5em;
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
  const [colorList, setColorList] = useState();

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
    //&& document.getElementById("select_parcel").value !== ""
    if (document.getElementById("select_color").value !== "") {
      navigate("/order", {
        state: {
          productId: product.id,
          productNum: numberRef.current.value,
          productPrice: product.price,
          productName: product.name,
          deliveryCost: product.deliveryCostGeneral,
          parcelType: "일반 택배",
          thumbnailImageId: product.imageId,
          color: colorList[document.getElementById("select_color").value].color,
          colorPrice: colorList[document.getElementById("select_color").value].addPrice
        },
      });
    }
    //else if(document.getElementById("select_parcel").value == ""){
    //  document.getElementById("alert_p").innerText = "배송 유형을 선택해주세요!";
    //}
    else{
      document.getElementById("alert_p").innerText = "색상을 선택해주세요!";
    }
  };

  useEffect(() => {
    if(productId == undefined){
      navigate("../");
    }
    axios({
      method: "get",
      url: 'http://localhost:8090/shop-backend/product/select/id/'+productId
    })
    .then(function (response){
      //handle success
      setProduct(response.data.product);
      setColorList(response.data.color);
    })
    .catch(function(error){
      //handle error
      console.log(error);
    });

  },[]); //마지막에 아무 파라미터를 안넣어줌으로써 페이지가 처음 로드 될 때만 적용


  function makeColorList(){
    var arr = [];

    for(var i = 0; i < colorList.length; i++){
      arr.push(
        <option value={i} key={i} className="option_select">{colorList[i].color}</option>
      );
    }
    return arr;
  }

  return (
    <Wrapper>
      <div className="detail_main" id="main">
        <div className="inner">
          <h1>{product == undefined ? "":product.name}</h1>
          <div className="row justify-content-center">

            <div className="col-6-medium" id="imgDiv">
              <span className="image main detail_span_img"><img className="main_img" src={product == undefined ? "":"http://localhost:3000/productImage/"+product.imageId+".jpg"} alt="" /></span>
            </div>

            <div className="col-6-medium calign" id="selectDiv">

              <div className="col-12-medium mt2">
                <h2 className="calign">판매 가격: {product == undefined ? "":product.price}원</h2>
                <h2 className="calign">배송 예정일 : {product == undefined ? "":product.deliveryTime} </h2>
                <h2 className="calign">배송비 : {product == undefined ? "":product.deliveryCostGeneral} </h2>

              </div>
              <div className="col-12-medium mb1">
                <select id="select_color" defaultValue="">
                  <option value="" disabled className="option_select">색상</option>
                  {colorList == undefined? "" : makeColorList()}
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
              {
                product == undefined ?
                  "": product.state == "open"?
                    <button className="bt_order" onClick={orderClick}> Order </button>
                    :
                    <button className="bt_order" disabled> 품절 </button>
              }
              </div>

            </div>

          </div>

          {product && <div id="contentArea" dangerouslySetInnerHTML={{__html: product.detail}} />}


        </div>
      </div>
    </Wrapper>
  );
}


/*

<div className="col-12-medium mb1">
  <select id="select_parcel" defaultValue="">
    <option value="" disabled className="option_select">배송 유형</option>
    <option value="일반 택배" className="option_select">일반 택배({product == undefined ? "":product.deliveryCostGeneral}원)</option>
    <option value="반값 택배" className="option_select">반값 택배({product == undefined ? "":product.deliveryCostHalf}원)</option>
  </select>
</div>


*/
export default Content;
