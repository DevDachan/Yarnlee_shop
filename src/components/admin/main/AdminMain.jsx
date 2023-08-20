import React, { useEffect, useState } from "react";
import { useNavigate , useLocation} from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import Modal from 'react-bootstrap/Modal';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
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
  const [hits, setHits] = useState(0);

  useEffect(() => {
    if(sessionStorage.getItem("admin") == null || sessionStorage.getItem("admin") == undefined || sessionStorage.getItem("jwt-auth-token") == null || sessionStorage.getItem("jwt-auth-token") == undefined){
      navigate('../adminLogin');
    }

    axios({
      method: "get",
      url: 'http://104.198.11.59:8090/shop-backend/product/admin/productList',
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      //handle success
      setProductList(response.data.productContent);
    })
    .catch(function(error){
      if(error.response.status === 401){
        sessionStorage.clear();
        Swal.fire({
          icon: 'error',
          title: '세션 만료',
          text: '다시 로그인 해주시기 바랍니다.',
          confirmButtonText: '확인'
        }).then(() => {
          navigate("../adminLogin");
          window.location.reload();
        });

      }else if(error.response.status === 500){
        sessionStorage.clear();

        Swal.fire({
          icon: 'error',
          title: '세션 만료',
          text: '다시 로그인 해주시기 바랍니다.',
          confirmButtonText: '확인'
        }).then(() => {
          navigate("../adminLogin");
          window.location.reload();
        });
      }
    });

    axios({
      method: "get",
      url: 'http://104.198.11.59:8090/shop-backend/admin/getAllContent',
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      //handle success
      setMainContent(response.data.main);
      setOrderContent(response.data.order);
      setHits(response.data.hits);
    })
    .catch(function(error){
      //handle error
      if(error.response.status === 401){
        sessionStorage.clear();
        Swal.fire({
          icon: 'error',
          title: '세션 만료',
          text: '다시 로그인 해주시기 바랍니다.',
          confirmButtonText: '확인'
        }).then(() => {
          navigate("../adminLogin");
          window.location.reload();
        });

      }else if(error.response.status === 500){
        sessionStorage.clear();

        Swal.fire({
          icon: 'error',
          title: '세션 만료',
          text: '다시 로그인 해주시기 바랍니다.',
          confirmButtonText: '확인'
        }).then(() => {
          navigate("../adminLogin");
          window.location.reload();
        });
      }

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
      data: formData,
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      //handle success
      setRelangering("");
    })
    .catch(function(error){
      window.location.reload();
    });
  }

  const changeOrderContent = (e) =>{
    let content = e.target.value;

    const formData = new FormData();
    formData.append("content", content);

    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/admin/editOrderContent',
      data: formData,
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      //handle success
      setRelangering("");
    })
    .catch(function(error){
      window.location.reload();
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
      data: formData,
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      //handle success
      setRelangering("");
    })
    .catch(function(error){
      window.location.reload();
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
      data: formData,
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      //handle success
      setRelangering("");
    })
    .catch(function(error){
      window.location.reload();
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
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      },
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
      window.location.reload();
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
      data: formData,
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      //handle success
      setProductList(response.data.productContent);
      window.location.reload();
    })
    .catch(function(error){
      //handle error
      window.location.reload();
    });
  }

  const createProduct = (e) =>{
    axios({
      method: "get",
      url: 'http://104.198.11.59:8090/shop-backend/product/createProduct',
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      //handle success
      setProductList(response.data.productContent);
    })
    .catch(function(error){
      window.location.reload();
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
              <h3>
                메인 화면 조회수 : {hits}
              </h3>
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
