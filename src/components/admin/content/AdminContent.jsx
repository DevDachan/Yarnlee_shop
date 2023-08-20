import React, { useState, useEffect, useRef } from "react";
import { useNavigate, useParams ,useLocation} from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import Modal from 'react-bootstrap/Modal';
import queryString from 'query-string';

import ReactQuill from 'react-quill'
import 'react-quill/dist/quill.snow.css' // Quill 에디터 스타일시트
import ImageUploader from 'quill-image-uploader'
import Quill from 'quill';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';

Quill.register('modules/imageUploader', ImageUploader);

const Wrapper = styled.div`
  padding: 16px;
  width: calc(100% - 32px);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: auto;
  min-width: 920px;
`;


function AdminContent(props) {
  const location = useLocation();
  const navigate = useNavigate();
  const numberRef = useRef(1);
  const { search } = useLocation();
  const {productId} = queryString.parse(search);
  const [product, setProduct] = useState();
  const [detail, setDetail] = useState();
  const [hits, setHits] = useState();

  const [color, setColor] = useState();
  const [show, setShow] = useState(false);
  const [relandering, setRelangering] = useState();
  const quillRef = useRef();
  const handleClose = () => setShow(false);
  const handleOpen = () => setShow(true);
  const [modalContent, setModalContent] = useState();

  useEffect(() => {
    if(sessionStorage.getItem("admin") == null || sessionStorage.getItem("admin") == undefined || sessionStorage.getItem("jwt-auth-token") == null || sessionStorage.getItem("jwt-auth-token") == undefined){
      navigate('../adminLogin');
    }else if( productId == undefined){
      navigate('../adminMain');
    }

    axios({
      method: "get",
      url: 'http://104.198.11.59:8090/shop-backend/product/admin/adminSelect/id/'+productId,
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }

    })
    .then(function (response){
      //handle success
      setHits(response.data.hits);
      setProduct(response.data.product);
      setColor(response.data.color);
      setDetail(response.data.product.detail);
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
  },[]); //마지막에 아무 파라미터를 안넣어줌으로써 페이지가 처음 로드 될 때만 적용



  const changeName = (e) =>{
    let content = e.target.value;
    let id = productId;

    const formData = new FormData();
    formData.append("id", id);
    formData.append("content", content);

    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/product/admin/changeName',
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
      //handle error
      if(error.response.status === 401){
        window.location.reload();

      }else if(error.response.status === 500){
        window.location.reload();
      }
    });
  }

  const changePrice = (e) =>{
    let content = e.target.value;
    let id = productId;

    const formData = new FormData();
    formData.append("id", id);
    formData.append("content", content);

    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/product/admin/changePrice',
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

      //handle error
      if(error.response.status === 401){
        window.location.reload();

      }else if(error.response.status === 500){
        window.location.reload();
      }
    });
  }

  const changeDeliveryTime = e =>{
    let content = e.target.value;
    let id = productId;

    const formData = new FormData();
    formData.append("id", id);
    formData.append("content", content);

    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/product/admin/changeDeliveryTime',
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
      //handle error
      if(error.response.status === 401){
        window.location.reload();

      }else if(error.response.status === 500){
        window.location.reload();
      }
    });
  }

  const changeDeliveryCostHalf = e =>{
    let content = e.target.value.replace(/[^0-9]/g, "");
    let id = productId;
    if(content != ""){
      const formData = new FormData();
      formData.append("id", id);
      formData.append("content", content);

      axios({
        method: "post",
        url: 'http://104.198.11.59:8090/shop-backend/product/admin/changeDeliveryCostHalf',
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
        //handle error
        if(error.response.status === 401){
          window.location.reload();

        }else if(error.response.status === 500){
          window.location.reload();
        }
      });
    }
  }

  const changeDeliveryCostGeneral = e =>{
    let content = e.target.value.replace(/[^0-9]/g, "");
    let id = productId;
    if(content != ""){
      const formData = new FormData();
      formData.append("id", id);
      formData.append("content", content);

      axios({
        method: "post",
        url: 'http://104.198.11.59:8090/shop-backend/product/admin/changeDeliveryCostGeneral',
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
        //handle error
        if(error.response.status === 401){
          window.location.reload();

        }else if(error.response.status === 500){
          window.location.reload();
        }
      });
    }
  }


  const changeDetail = ()   =>{
    if (quillRef.current) {
      const editor = quillRef.current.getEditor();
      const content = editor.root.innerHTML;
      // 내용 추출 후 처리 로직 작성
      setDetail(content);
    }
  }

  const changeState = (e) =>{
    const formData = new FormData();
    formData.append("id", productId);
    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/product/admin/changeState',
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
      //handle error
      if(error.response.status === 401){
        window.location.reload();

      }else if(error.response.status === 500){
        window.location.reload();
      }
    });

  }

  const saveDetail = (e) =>{
    const formData = new FormData();
    formData.append("id", productId);
    formData.append("content", detail);

    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/product/admin/changeDetail',
      data: formData,
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      //handle success
      setProduct(response.data);
      setModalContent("저장이 완료 되었습니다");
      setShow(true);
    })
    .catch(function(error){
      //handle error
      if(error.response.status === 401){
        window.location.reload();

      }else if(error.response.status === 500){
        window.location.reload();
      }
    });

  }

  const makeContent= (e) => {
    e = e.replace(/&lt;/g, "<");
    e = e.replace(/&gt;/g, ">");
    e = e.replace(/<br>/g, "\n");
    e = e.replace(/&nbsp;/g, " ");
    return e;
  }

  const imageHandler = () => {
    const input = document.createElement("input");
    input.setAttribute("type", "file");
    input.setAttribute("accept", "image/*");
    input.click();

    input.onchange = async () => {
      const file = input.files[0];
      const formData = new FormData();
      formData.append("file", file);

      try {
        const response = await axios.post("http://104.198.11.59:8090/shop-backend/product/insertImage", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });
        const imageUrl = response.data;
        const editor = quillRef.current.getEditor();
        const cursorPosition = editor.getSelection().index;
        editor.insertEmbed(cursorPosition, "image", "http://104.198.11.59/productImage/"+imageUrl+".jpg");
        } catch (error) {
        console.log(error);
      }
    };
  }

  const linkHandler = () => {
    Swal.fire({
     title: 'Youtube Link',
     input: 'url',
     inputLabel: 'ex) https://www.youtube.com/watch?v=vR5Fss-xsO4',
     showCancelButton: true,
     inputValidator: (value) => {
       if (!value ) {
         return '링크를 입력해주세요';
       }else if(!value.includes("youtube.com")){
         return '올바른 입력해주세요';
       }
     },
     preConfirm: (url) => {
       if(url.includes("youtube.com")){
         var imageUrl;
         if(url.includes("watch?v=")){
           imageUrl = url.split("watch?v=")[1];
         }else if(url.includes("shorts/")){
           imageUrl = url.split("shorts/")[1];
         }
         const makeUrl = "https://www.youtube.com/embed/"+imageUrl;
         const embed = (
                <iframe width="560" height="315"
                src={`${makeUrl}`}
                title="YouTube video player" frameborder="0"
                allow="accelerometer; clipboard-write; encrypted-media;
                gyroscope; picture-in-picture; web-share"
                allowfullscreen></iframe>
              );
         const editor = quillRef.current.getEditor();
         var cursorPosition = editor.selection.savedRange.index;
         if(cursorPosition == 0){
           cursorPosition = editor.getLength()-1;
         }
         editor.insertEmbed(cursorPosition, "video", makeUrl);
       }
     }
   });

  }

  const changeImage = (e) =>{
    var input = document.createElement("input");
    input.setAttribute("type", "file");
    input.setAttribute("accept", "image/*");
    input.click();
    input.onchange = async() => {
      if(input.files){
        var file: any = input.files[0];
        var formData = new FormData();

        formData.append("file", file);
        formData.append("productId", productId);
        axios({
          method: "post",
          url: 'http://104.198.11.59:8090/shop-backend/product/admin/insertMainImage',
          data: formData,
          headers: {
            'Content-Type': 'multipart/form-data',
            "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
          }
        })
        .then(function (response){
          setProduct(response.data);
        })
        .catch(function(error){
          //handle error
          if(error.response.status === 401){
            window.location.reload();

          }else if(error.response.status === 500){
            window.location.reload();
          }
        });
      }
    }
  }

  const insertColor =  (e) =>{
    const formData = new FormData();
    formData.append("productId", productId);
    if(color == null){
      formData.append("colorId", 0);
    }else{
      formData.append("colorId", color.length);
    }

    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/product/admin/insertColor',
      data: formData,
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      setProduct(response.data.product);
      setColor(response.data.color);
    })
    .catch(function(error){
      //handle error
      if(error.response.status === 401){
        window.location.reload();

      }else if(error.response.status === 500){
        window.location.reload();
      }
    });
  }

  const chnageColor =  (e) =>{
    const formData = new FormData();
    formData.append("colorId", e.target.id);
    formData.append("colorContent", e.target.value);
    formData.append("productId", productId);

    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/product/admin/changeColor',
      data: formData,
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      setProduct(response.data.product);
      setColor(response.data.color);
    })
    .catch(function(error){
      //handle error
      if(error.response.status === 401){
        window.location.reload();

      }else if(error.response.status === 500){
        window.location.reload();
      }
    });
  }

  const changeColorPrice = (e) =>{
    const formData = new FormData();
    formData.append("colorId", e.target.id);
    formData.append("colorPrice", e.target.value);
    formData.append("productId", productId);

    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/product/admin/changeColorPrice',
      data: formData,
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      setProduct(response.data.product);
      setColor(response.data.color);
    })
    .catch(function(error){
      //handle error
      if(error.response.status === 401){
        window.location.reload();

      }else if(error.response.status === 500){
        window.location.reload();
      }
    });
  }

  const deleteColor =  (e) =>{
    const formData = new FormData();
    formData.append("colorId", e.target.id);
    formData.append("productId", productId);
    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/product/admin/deleteColor',
      data: formData,
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      setProduct(response.data.product);
      setColor(response.data.color);
    })
    .catch(function(error){
      //handle error
      if(error.response.status === 401){
        window.location.reload();

      }else if(error.response.status === 500){
        window.location.reload();
      }
    });
  }


  function makeColorList(){
    var arr = [];

    for(var i = 0; i < color.length; i++){

      arr.push(
        <div className="grid_t_3">
          <input type="text" className="gr-6 mb-2" placeHolder="색상" defaultValue={color[i].color} id={color[i].colorId} onChange={chnageColor}/>
          <input type="number" className="gr-6 mb-2" placeHolder="추가 금액" defaultValue={color[i].addPrice == 0 ? "":color[i].addPrice} id={color[i].colorId} onChange={changeColorPrice}/>
          <input type="button" className="gr-6 mb-2" value="DELETE" id={color[i].colorId} onClick={deleteColor}/>

        </div>
      );
    }
    return arr;
  }

  const quillModules = React.useMemo(() => ({
    toolbar: {
      container: [
        [{ header: [1, 2, false] }],
        ['bold', 'italic', 'underline'],
        [{ 'color': [] }, { 'background': [] }],
        [{ 'list': 'ordered' }, { 'list': 'bullet' }],
        [{ 'align': [] }],
        ['link'],['image']
      ],
        // custom 핸들러 설정
      handlers: {
          image: imageHandler, // 이미지 tool 사용에 대한 핸들러 설정
          link: linkHandler    // 유튜브 링크 사용에 대한 핸들러 설정
      }
    },
  }), []);

  return (
    <Wrapper>
      <div className="detail_main" id="main">
        <div className="inner">
          <h3> 현재 조회수 : {hits} </h3>
          <input type="text" className="ip-admin-content-head" defaultValue={product == undefined ? "":product.name} onChange={changeName}></input>

          <div className="row mt-2 detail_main_nav">

            <div className="col-12-medium" id="admin-imgDiv">
              <span className="image main detail_span_img">
                <img className="main_img" src={product == undefined ? "" :"http://104.198.11.59/productImage/"+product.imageId+".jpg"}
                alt=""
                onClick={changeImage}
                />
              </span>
            </div>

            <div className="col-12-medium calign grid_t" id="admin-selectDiv" style={{paddingTop: "10%"}}>
              <div className="gr-12">
                <h3>가격의 경우 원을 빼고 입력</h3>
              </div>
              <div className="gr-6 mt2">
                  <span>판매 가격: </span>
              </div>
              <div className="gr-6 mt2">
                  <input type="text" className="ip-admin-content-info" defaultValue={product == undefined ? "":product.price} onChange={changePrice}></input>
              </div>

              <div className="gr-6 mt2">
                  <span>배송 예정일 : </span>
              </div>
              <div className="gr-6 mt2">
                  <input type="text" className="ip-admin-content-info" defaultValue={product == undefined ? "":product.deliveryTime} onChange={changeDeliveryTime}></input>
              </div>

              <div className="gr-6 mt2">
                  <span>반값 택배 가격 : </span>
              </div>
              <div className="gr-6 mt2">
                <input type="text" maxLength={8} className="ip-admin-content-info" defaultValue={product == undefined ? "":product.deliveryCostHalf} onChange={changeDeliveryCostHalf}></input>
              </div>
              <div className="gr-6 mt2">
                  <span>일반 택배 가격 : </span>
              </div>
              <div className="gr-6 mt2">
                <input type="text" maxLength={8} className="ip-admin-content-info" defaultValue={product == undefined ? "":product.deliveryCostGeneral} onChange={changeDeliveryCostGeneral}></input>
              </div>

            </div>
          </div>
          <div className="mt3 mb1">
            <h3> 상품 상태 </h3>
            <div className="grid_t">
              <div className="gr-6">
                {
                  product == undefined ? "" :
                    product.state == "open"?
                    <input type="button" id="state-open" name="state-open" value="open" onClick={changeState}/>
                    :
                    <input type="button" id="state-close" name="state-open" value="close" onClick={changeState}/>
                }
              </div>
            </div>
          </div>

          <div className="mt3 mb1">
            <h3> Color </h3>
              {color == undefined ? "" : makeColorList()}
            <input type="button"  value="+" onClick={insertColor}/>
          </div>



          <ReactQuill
            ref={quillRef}
            value={detail? detail : ''}
            modules={quillModules}
            onChange={changeDetail}
            theme="snow"
          />
          <button onClick={saveDetail} >저장하기</button>

        </div>
      </div>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header>
          <Modal.Title>안내</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {modalContent}
        </Modal.Body>
        <Modal.Footer>
          <button onClick={handleClose}>닫기</button>
        </Modal.Footer>
      </Modal>
    </Wrapper>
  );
}

export default AdminContent;
