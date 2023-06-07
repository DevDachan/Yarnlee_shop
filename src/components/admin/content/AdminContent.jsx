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

Quill.register('modules/imageUploader', ImageUploader);

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
  const location = useLocation();
  const navigate = useNavigate();
  const numberRef = useRef(1);
  const productId = location.state.productId;
  const [product, setProduct] = useState();
  const [color, setColor] = useState();
  const [show, setShow] = useState(false);
  const [relandering, setRelangering] = useState();
  const quillRef = useRef();
  const handleClose = () => setShow(false);
  const handleOpen = () => setShow(true);


  useEffect(() => {
    if(sessionStorage.getItem("admin") == null || sessionStorage.getItem("admin") == undefined ){
      navigate('../adminLogin');
    }
    axios({
      method: "get",
      url: 'http://localhost:8090/shop-backend/product/select/id/'+productId
    })
    .then(function (response){
      //handle success
      setProduct(response.data.product);
      setColor(response.data.color);
    })
    .catch(function(error){
      //handle error
      console.log(error);
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

  const changePrice = (e) =>{
    let content = e.target.value;
    let id = productId;

    const formData = new FormData();
    formData.append("id", id);
    formData.append("content", content);

    axios({
      method: "post",
      url: 'http://localhost:8090/shop-backend/product/changePrice',
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

  const changeDeliveryTime = e =>{
    let content = e.target.value;
    let id = productId;

    const formData = new FormData();
    formData.append("id", id);
    formData.append("content", content);

    axios({
      method: "post",
      url: 'http://localhost:8090/shop-backend/product/changeDeliveryTime',
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

  const changeDeliveryCostHalf = e =>{
    let content = e.target.value.replace(/[^0-9]/g, "");
    let id = productId;
    console.log(content);
    if(content != ""){
      const formData = new FormData();
      formData.append("id", id);
      formData.append("content", content);

      axios({
        method: "post",
        url: 'http://localhost:8090/shop-backend/product/changeDeliveryCostHalf',
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
        url: 'http://localhost:8090/shop-backend/product/changeDeliveryCostGeneral',
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
  }


  const changeDetail = ()   =>{
    if (quillRef.current) {
      const editor = quillRef.current.getEditor();
      const content = editor.root.innerHTML;
      // 내용 추출 후 처리 로직 작성
      let id = productId;

      const formData = new FormData();
      formData.append("id", id);
      formData.append("content", content);

      axios({
        method: "post",
        url: 'http://localhost:8090/shop-backend/product/changeDetail',
        data: formData
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
    }
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
        const response = await axios.post("http://localhost:8090/shop-backend/product/insertImage", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });
        const imageUrl = response.data;
        const editor = quillRef.current.getEditor();
        const cursorPosition = editor.getSelection().index;
        editor.insertEmbed(cursorPosition, "image", "../productImage/"+imageUrl+".jpg");
        } catch (error) {
        console.log(error);
      }
    };
  };

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
          url: 'http://localhost:8090/shop-backend/product/insertMainImage',
          data: formData,
          headers: {
            'Content-Type': 'multipart/form-data'
          },
        })
        .then(function (response){
          setProduct(response.data);
        })
        .catch(function(error){
          //handle error
          console.log(error);
        })
        .then(function(){
          // always executed
        });
      }
    }
  }

  const insertColor =  (e) =>{
    const formData = new FormData();
    formData.append("productId", productId);
    formData.append("colorId", color.length);

    axios({
      method: "post",
      url: 'http://localhost:8090/shop-backend/product/insertColor',
      data: formData
    })
    .then(function (response){
      setProduct(response.data.product);
      setColor(response.data.color);
    })
    .catch(function(error){
      //handle error
      console.log(error);
    })
    .then(function(){
      // always executed
    });
  }

  const chnageColor =  (e) =>{
    const formData = new FormData();
    formData.append("colorId", e.target.id);
    formData.append("colorContent", e.target.value);
    formData.append("productId", productId);

    axios({
      method: "post",
      url: 'http://localhost:8090/shop-backend/product/changeColor',
      data: formData
    })
    .then(function (response){
      setProduct(response.data.product);
      setColor(response.data.color);
    })
    .catch(function(error){
      //handle error
      console.log(error);
    })
    .then(function(){
      // always executed
    });
  }

  const deleteColor =  (e) =>{
    const formData = new FormData();
    formData.append("colorId", e.target.id);
    formData.append("productId", productId);
    axios({
      method: "post",
      url: 'http://localhost:8090/shop-backend/product/deleteColor',
      data: formData
    })
    .then(function (response){
      setProduct(response.data.product);
      setColor(response.data.color);
    })
    .catch(function(error){
      //handle error
      console.log(error);
    })
    .then(function(){
      // always executed
    });
  }


  function makeColorList(){
    var arr = [];

    for(var i = 0; i < color.length; i++){
      arr.push(
        <div className="grid_t">
          <input type="text" className="gr-6 mb-2" defaultValue={color[i]} id={color[i]} onChange={chnageColor}/>
          <input type="button" className="gr-6 mb-2" value="DELETE" id={color[i]} onClick={deleteColor}/>
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
        ['link', 'image']
      ],
        // custom 핸들러 설정
      handlers: {
          image: imageHandler, // 이미지 tool 사용에 대한 핸들러 설정
      }
    },
  }), []);

  return (
    <Wrapper>
      <div className="detail_main" id="main">
        <div className="inner">
          <input type="text" className="ip-admin-content-head" defaultValue={product == undefined ? "":product.name} onChange={changeName}></input>

          <div className="row mt-2 detail_main_nav">

            <div className="col-12-medium" id="admin-imgDiv">
              <span className="image main detail_span_img">
                <img className="main_img" src={product == undefined ? "" :"../productImage/"+product.imageId+".jpg"}
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

            <div className="mt3 mb1">
              <h3> Color </h3>
                {color == undefined ? "" : makeColorList()}
              <input type="button"  value="+" onClick={insertColor}/>
            </div>

          </div>
          <ReactQuill
            ref={quillRef}
            value={product?.detail || ''}
            modules={quillModules}
            onBlur={changeDetail}
            theme="snow"
          />

        </div>
      </div>
    </Wrapper>
  );
}

export default AdminContent;
