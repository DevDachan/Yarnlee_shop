import React, { useEffect, useState } from "react";
import { useNavigate , useLocation} from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import AdminItemList from "./list/AdminItemList";
// 나중에 현재 전체 목록 itemList로 변경하기.


const Wrapper = styled.div`
    padding: 0 2.5em;
    margin: 0 auto;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-width: 800px;
`;


function AdminMain(props) {
  const navigate = useNavigate();
  const [productList, setProductList] = useState();
  const [relandering, setRelangering] = useState();

  useEffect(() => {
    if(sessionStorage.getItem("admin") == null || sessionStorage.getItem("admin") == undefined){
      navigate('../adminLogin');
    }

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
  },[]);


  const changeName = (e) =>{
    let content = e.target.value;
    let id = e.target.id;

    const formData = new FormData();
    formData.append("id", id);
    formData.append("content", content);

    axios({
      method: "post",
      url: 'http://localhost:8090/shop-backend/product/changeName',
      data: formData
    })
    .then(function (response){
      //handle success
      setRelangering("");
    })
    .catch(function(error){
      //handle error
      console.log(error);
    })
    .then(function(){
      // always executed
    });
  }

  const goContent = (e) =>{
    navigate('/adminContent', {
      state: {
        productId: e.target.id
      }
    });
  }

  const changeSubDetail = (e) =>{
    let content = e.target.value;
    let id = e.target.id;

    const formData = new FormData();
    formData.append("id", id);
    formData.append("content", content);

    axios({
      method: "post",
      url: 'http://localhost:8090/shop-backend/product/changeSubDetail',
      data: formData
    })
    .then(function (response){
      //handle success
      setRelangering("");
    })
    .catch(function(error){
      //handle error
      console.log(error);
    })
    .then(function(){
      // always executed
    });
  }



  function makeProductContent(){
    var arr = [];

    for(var i = 0; i < productList.length; i++){
      arr.push(
        <article>
          <span className="image">
            <img src={"productImage/"+productList[i].imageId+".jpg"} alt="" />
          </span>
          <a>
            <input type="text" className="ip-admin-product" id={productList[i].id} onChange={ (e) => changeName(e)} defaultValue={productList[i].name} />
            <div className="content">
              <input type="text" className="ip-admin-product" id={productList[i].id} onChange={ (e) => changeSubDetail(e)}  defaultValue={productList[i].subDetail} />
            </div>
          </a>
        </article>
      );
    }
    return arr;
  }

  function makeChangePosition(){
    var arr = [];
    for(var i = 0; i < productList.length; i++){
      arr.push(
        <>
          <div className="gr-1">
            <h3>{productList[i].position}</h3>
          </div>
          <div className="gr-5 mr3">
            <h3>{productList[i].name}</h3>
          </div>
          <div className="gr-3">
            <button name="up" id={productList[i].position} onClick={changePosition} className="mr3"> ^ </button>
            <button name="down" id={productList[i].position} onClick={changePosition} > v </button>
          </div>
          <div className="gr-3 ml3" >
            <button name="goContent" id={productList[i].id} className="bt-productEdit" onClick={goContent} > 관리 </button>
          </div>
        </>
      );
    }
    return arr;
  }

  const changePosition = (e) => {
    var check = e.target.name;
    var num = e.target.id;
    const formData = new FormData();

    if(check == "up" && num == 1){
      return;
    }else if(check == "down" && num == productList.length){
      return;
    }else{
      formData.append("direction", check);
      formData.append("position", num);
    }

    axios({
      method: "post",
      url: 'http://localhost:8090/shop-backend/product/changePosition',
      data: formData
    })
    .then(function (response){
      //handle success
      setProductList(response.data.productContent);
      window.location.reload();
    })
    .catch(function(error){
      //handle error
      console.log(error);
    })
    .then(function(){
      // always executed
    });
  }

  const createProduct = (e) =>{
    axios({
      method: "get",
      url: 'http://localhost:8090/shop-backend/product/createProduct'
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
  }

  return (
      <Wrapper>
          <div id="main">
            <div className="inner mg0">
              <header>
                <textarea placeholder="메인 화면에서 나타날 내용 입력" defaultValue="<h1>안녕하십니까!!<br />
                예제 쇼핑몰 페이지입니다!</h1>">
                </textarea>
              </header>
              <section className="tiles mt2">
              {
                productList == undefined ? "": makeProductContent()
              }
              </section>
            </div>
          </div>


          <h2>순서 바꾸기</h2>

          <div className="grid_t mb2">
          {
            productList == undefined ? "":makeChangePosition()
          }
          </div>
          <button name="goContent" className="bt-productEdit" onClick={createProduct} >생성 </button>
      </Wrapper>
  );
}

export default AdminMain;
