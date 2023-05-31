import React, { useState, useEffect, useRef } from "react";
import { useNavigate,useLocation } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import Modal from 'react-bootstrap/Modal';
import Phone from "../../order/Phone";
import PostSelector from "../../order/PostSelector";

const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: auto;
`;

function AdminOrderHistroy(props) {
  const navigate = useNavigate();
  const location = useLocation();
  const [orderDetail, setOrderDetail] = useState(location.state.orderDetail);
  const [productDetail, setProductDetail] = useState(location.state.productDetail);

  const [name, setName] = useState(location.state.orderDetail.orderName);
  const [phoneNum, setPhoneNum] = useState(location.state.orderDetail.orderPhone);
  const [address, setAddress] = useState(location.state.orderDetail.orderAddress);
  const [zoneCode, setZonecode] = useState(location.state.orderDetail.orderZonecode);
  const [addressDetail, setAddressDetail] = useState(location.state.orderDetail.addressDetail);
  const [imageId, setImageId] = useState(location.state.orderDetail.imageId);
  const totalCost = location.state.orderDetail.totalCost;
  const [uploadImage,setUploadImage] = useState(location.state.orderDetail.imageId);

  const [modealYesNo, setModealYesNo] = useState(false);

  const [show, setShow] = useState(false);
  const [modalContent, setModalContent] = useState();
  const [modalFunction, setModalFunction] = useState();
  const handleClose = () => setShow(false);
  const handleOpen = () => setShow(true);


  const deleteOrder = (e) =>{
    setModalContent("해당 주문을 삭제하시겠습니까?");
    setModealYesNo(true);
    setModalFunction(() => yesDelete);
    setShow(true);
  }

  const yesDelete = (e) =>{
    axios({
      method: "delete",
      url: 'http://localhost:8090/shop-backend/order/delete/id/'+orderDetail.id
    })
    .then(function (response){
      //handle success
      navigate('../adminOrderList');
    })
    .catch(function(error){
      //handle error
      console.log(error);
    })
    .then(function(){
      // always executed
    });
  }


  const editOrder = (e) =>{
    setModalFunction(() => yseEdit);
    setModealYesNo(true);
    setModalContent("수정하시겠습니까?");
    setShow(true);
  }

  const yseEdit = (e) => {
    console.log(document.getElementById("ip_name").value);

    const formData = new FormData();
    formData.append("id", orderDetail.id); //default for DTO
    formData.append("orderDate", "2023-05-23");
    formData.append("userId", "guest");
    formData.append("productId", productDetail.id);
    formData.append("color", orderDetail.color);
    formData.append("num", orderDetail.num);
    formData.append("totalCost", totalCost);
    formData.append("orderName", document.getElementById("ip_name").value);
    formData.append("orderPhone", phoneNum);
    formData.append("orderZonecode", zoneCode);
    formData.append("orderAddress", address);
    formData.append("addressDetail", addressDetail);
    formData.append("imageId", uploadImage);

    axios({
      method: "post",
      url: 'http://localhost:8090/shop-backend/order/edit',
      data: formData
    })
    .then(function (response){
      //handle success
      setShow(false);
    })
    .catch(function(error){
      //handle error
      console.log(error);
    })
    .then(function(){
      // always executed
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

        axios({
          method: "post",
          url: 'http://localhost:8090/shop-backend/order/insertUserImage',
          data: formData,
          headers: {
            'Content-Type': 'multipart/form-data'
          },
        })
        .then(function (response){
          if(response.data != -1){
            setUploadImage(response.data);
          }
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
  const previewImage = (e) =>{
    setModalContent(<img style={{width: "100%"}} src={uploadImage == undefined ? "" :"../userImage/"+uploadImage+".jpg"}></img>);
    setModealYesNo(false);
    setShow(true);
  }


  return (
      <Wrapper>
        <div className="order_main" id="main">
          <div className="inner mg0">
            <div className="grid_t">
              <div className="gr-2" id="">
                <span className="image main order_span_img"><img className="order_img" src="images/pic13.jpg" alt="" /></span>
              </div>
              <div className="gr-10 calign">
                <h1>{productDetail.name}</h1>
              </div>

              <div className="gr-12 mb2 grid_t" style={{boxShadow: "3px 3px 3px 3px rgb(98 217 182)", borderRadius:"20px"}}>
                <div className="gr-4 calign" style={{borderRight: "3px solid rgb(98 217 182)", paddingTop: "20px"}}>
                  <h2 style={{color: "#00db9a"}}> 주문 상세 </h2>
                </div>
                <div className="gr-8 calign" style={{paddingTop: "20px"}}>
                  <div>
                    {productDetail.name}  | {orderDetail.color}  |  {orderDetail.num}개
                  </div>
                </div>
                <div className="gr-12 calign" style={{borderTop: "3px solid rgb(98 217 182)", paddingTop: "20px"}}>
                  <h3> 총액 : {totalCost}원 </h3>
                </div>
              </div>
            </div>

            <div className="grid_t" style={{padding: "30px", boxShadow: "3px 3px 3px 3px rgb(98 217 182)"}}>

              <div className="gr-4 calign pt1">
                <h2 className="mg0">주문자 이름</h2>
              </div>
              <div className="gr-8">
                <input type="text" className="prl1" id="ip_name" defaultValue={name}></input>
              </div>



              <div className="gr-4 calign pt1">
                <h2 className="mg0">전화번호</h2>
              </div>
              <div className="gr-8">
                  <Phone phoneNum={phoneNum || ""} setPhoneNum={setPhoneNum}/ >
              </div>

              <div className="gr-4 calign mt3">
                <PostSelector
                  setAddress = {setAddress || ""}
                  setZonecode = {setZonecode || ""}
                />
              </div>
              <div className="gr-8 mt3">
                <input type="text" className="prl1"  disabled id="zoneCode" value={zoneCode} required />
              </div>
              <div className="gr-12">
                <input type="text" className="prl1"  disabled id="address" value={address} required />
              </div>
              <div className="gr-12">
                <input type="text" required className="prl1" id="address_detail" defaultValue={orderDetail.addressDetail}/>
              </div>

              <div className="gr-12 mt3">
              {
                uploadImage == undefined ?
                <>
                <label id="lb_image" htmlFor="image_remittance" onClick={changeImage}>Upload</label>
                </>
                :
                <>
                <div style={{"white-space": "nowrap"}}>
                  <input type="button" className="mb-3 mr3 imagebtn" onClick={changeImage} value="REUPLOAD" />
                  <input type="button" className="mb-3 imagebtn" onClick={previewImage} value="PREVIEW" />
                </div>
                <img className="detail_img" src={uploadImage == undefined ? "" :"../userImage/"+uploadImage+".jpg"}
                alt=""/>
                </>
              }
              </div>

              <div className="gr-4 calign pt3">
                <button className="bt_back" onClick={(e) => {navigate("../AdminOrderList")}}> 목록으로 </button>
              </div>
              <div className="gr-6 calign pt3">
                <button className="bt_edit" onClick={editOrder}> 수정하기 </button>
              </div>
              <div className="gr-2 calign pt3">
                <button className="bt_delete" onClick={deleteOrder}> 삭제 </button>
              </div>
            </div>
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
            {
              modealYesNo?
              <div className="grid_t" style={{width: "100%"}}>
                <div className="gr-6 lalign">
                  <button onClick={handleClose}>아니오</button>
                </div>
                <div className="gr-6 ralign">
                  <button onClick={modalFunction}>네</button>
                </div>
              </div>:
              <button onClick={handleClose}>닫기</button>
            }
          </Modal.Footer>
        </Modal>
      </Wrapper>
  );
}

export default AdminOrderHistroy;
