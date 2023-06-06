import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import ItemList from "./list/ItemList";
// 나중에 현재 전체 목록 itemList로 변경하기.

const Wrapper = styled.div`
    padding: 0 2.5em;
    margin: 0 auto;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-width: 750px;
`;

function Main(props) {
  const [productList, setProductList] = useState();
  const [mainContent,setMainContent] = useState("");
  const navigate = useNavigate();

  var product_content = '';

  useEffect(() => {
    axios({
      method: "get",
      url: 'http://localhost:8090/shop-backend/product/productList'
    })
    .then(function (response){
      //handle success
      setProductList(response.data.productContent);
    })
    .catch(function(error){
      //handle error
      console.log(error);
    })
    .then(function(){
      // always executed
    });

    axios({
      method: "get",
      url: 'http://localhost:8090/shop-backend/admin/getMainContent'
    })
    .then(function (response){
      //handle success
      setMainContent(response.data);
    })
    .catch(function(error){
      //handle error
      console.log(error);
    })
    .then(function(){
      // always executed
    });

  },[]);

  function makeProductContent(){
    var arr = [];

    for(var i = 0; i < productList.length; i++){
      arr.push(
        <article>
          <span className="image">
            <img src={"productImage/"+productList[i].imageId+".jpg"} alt="" />
          </span>
          <a href={"content?productId="+productList[i].id}>
            <h2>{productList[i].name}</h2>
            <div className="content">
              <p>{productList[i].subDetail}.</p>
            </div>
          </a>
        </article>);
    }
    return arr;

  }


  return (
      <Wrapper>
          <div id="main">
            <div className="inner mg0">
              <header>
                <h1>Only Handmade</h1>
                <h2 style={{whiteSpace: "pre"}}>{mainContent}</h2>
              </header>
              <section className="tiles">
                {
                  productList == undefined ? "": makeProductContent()
                }
              </section>
            </div>
          </div>
      </Wrapper>
  );
}

export default Main;
