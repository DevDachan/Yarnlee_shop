import React, { useEffect, useState } from "react";
import { useNavigate , useLocation} from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import Modal from 'react-bootstrap/Modal';

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
  const [deleteId, setDeleteId] = useState(0);
  const [mainContent,setMainContent] = useState("");
  const [orderContent, setOrderContent] = useState("");
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleOpen = () => setShow(true);


  useEffect(() => {
    if(sessionStorage.getItem("admin") == null || sessionStorage.getItem("admin") == undefined){
      navigate('../adminLogin');
    }

    axios({
      method: "get",
      url: 'http://104.198.11.59:8090/shop-backend/product/productList'
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
      url: 'http://104.198.11.59:8090/shop-backend/admin/getAllContent'
    })
    .then(function (response){
      //handle success
      setMainContent(response.data.main);
      setOrderContent(response.data.order);
    })
    .catch(function(error){
      //handle error
      console.log(error);
    })
    .then(function(){
      // always executed
    });



  },[]);

  const goContent = (e) =>{
    navigate('/adminContent?productId='+e.target.id);
  }

  const changeMainContent = (e) =>{
    let content = e.target.value;

    const formData = new FormData();
    formData.append("content", content);

    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/admin/editMainContent',
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

  const changeOrderContent = (e) =>{
    let content = e.target.value;

    const formData = new FormData();
    formData.append("content", content);

    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/admin/editOrderContent',
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

  const changeName = (e) =>{
    let content = e.target.value;
    let id = e.target.id;

    const formData = new FormData();
    formData.append("id", id);
    formData.append("content", content);

    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/product/changeName',
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


  const changeSubDetail = (e) =>{
    let content = e.target.value;
    let id = e.target.id;

    const formData = new FormData();
    formData.append("id", id);
    formData.append("content", content);

    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/product/changeSubDetail',
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

  const goDelete = (e) =>{
    setShow(true);
    setDeleteId(e.target.id);
  }

  const deleteProduct = (e) =>{
    if(deleteId == 0) return null;
    axios({
      method: "get",
      url: 'http://104.198.11.59:8090/shop-backend/product/deleteProduct',
      params: {
        id: deleteId,
        adminId: sessionStorage.getItem("admin"),
        hashKey: sessionStorage.getItem("adminHash")
      }
    })
    .then(function (response){
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



  function makeProductContent(){
    var arr = [];

    for(var i = 0; i < productList.length; i++){
      arr.push(
        <article>
          <span className="image">
            <img src={"thumbnails/"+productList[i].imageId+".jpg"} alt="" />
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
          <div className="gr-1">
            <button name="up" id={productList[i].position} onClick={changePosition} className="mr3"> ^ </button>
          </div>
          <div className="gr-1">
            <button name="down" id={productList[i].position} onClick={changePosition} > v </button>
          </div>
          <div className="gr-2 ml2" >
            <button name="goContent" id={productList[i].id} className="bt-productEdit" onClick={goContent} > 관리 </button>
          </div>
          <div className="gr-2" >
            <button name="deleteContent" id={productList[i].id} className="bt-productEdit" onClick={goDelete} > 삭제 </button>
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
      url: 'http://104.198.11.59:8090/shop-backend/product/changePosition',
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
      url: 'http://104.198.11.59:8090/shop-backend/product/createProduct'
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
  function resize(e) {
    e.target.style.height = "auto";
    e.target.style.height = (e.target.scrollHeight)+"px";
    e.target.style.width = "100%";
  }

  return (
      <Wrapper>
          <div id="main">
            <div className="inner mg0">
              <header className="main-header">
                <textarea placeholder="메인 화면에서 나타날 내용 입력"
                  onKeyDown={resize} onKeyUp={resize} onFocus={resize}
                  onChange={ (e) => changeMainContent(e)} defaultValue={mainContent} >
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

          <div className="div-adminMain-editOrder">
            <h2> 주문서 안내 문구</h2>
            <textarea placeholder="주문서 안내 문구 내용 입력"
              onKeyDown={resize} onKeyUp={resize} onFocus={resize}
              onChange={ (e) => changeOrderContent(e)} defaultValue={orderContent} >
            </textarea>
          </div>

          <Modal show={show} onHide={handleClose}>
            <Modal.Header>
              <Modal.Title>안내</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              삭제하시겠습니까?
            </Modal.Body>
            <Modal.Footer>
              <div className="grid_t" style={{width: "100%"}}>
                <div className="gr-6 lalign">
                  <button onClick={handleClose}>아니오</button>
                </div>
                <div className="gr-6 ralign">
                  <button onClick={deleteProduct}>네</button>
                </div>
              </div>
            </Modal.Footer>
          </Modal>
      </Wrapper>
  );
}

export default AdminMain;
