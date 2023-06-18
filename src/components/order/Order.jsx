import React, { useState, useEffect, useRef } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import Modal from 'react-bootstrap/Modal';

import Phone from "./Phone";
import PostSelector from "./PostSelector";

const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: auto;
`;



function Order(props) {
  const navigate = useNavigate();
  const location = useLocation();
  const [name, setName] = useState();
  const [phoneNum, setPhoneNum] = useState();
  const [address, setAddress] = useState();
  const [zoneCode, setZonecode] = useState("");
  const [imageId, setImageId] = useState();
  const [addressDetail, setAddressDetail] = useState();
  const [uploadImage,setUploadImage] = useState();
  const [orderContent,setOrderContent] = useState("");
  const [show, setShow] = useState(false);
  const [modalContent, setModalContent] = useState();

  const handleClose = () => setShow(false);
  const handleOpen = () => setShow(true);

  useEffect(() => {
    if (location.state == null) {
      navigate('../');
    }
  }, [location, navigate]);

  useEffect(() => {
    if(sessionStorage.getItem("id") != null && sessionStorage.id != undefined ){
      const formData = new FormData();
      formData.append("id", sessionStorage.getItem("id"));
      axios({
        method: "post",
        url: 'http://104.198.11.59:8090/shop-backend/user/info',
        data: formData
      })
      .then(function (response){
        //handle success
        setPhoneNum(response.data.phone);
        setAddress(response.data.address);
        setZonecode(response.data.zoneCode);
        setAddressDetail(response.data.addressDetail);
        setName(response.data.name);
      })
      .catch(function(error){
        //handle error
        console.log(error);
      })
      .then(function(){
        // always executed
      });
    }

    axios({
      method: "get",
      url: 'http://104.198.11.59:8090/shop-backend/admin/getOrderContent'
    })
    .then(function (response){
      //handle success
      setOrderContent(response.data);
    })
    .catch(function(error){
      //handle error
      console.log(error);
    })
    .then(function(){
      // always executed
    });
  },[])

  if (location.state == null) {
     return null; // navigate 호출 후 컴포넌트의 렌더링을 중단
  }



  const productId = location.state.productId;
  const productName = location.state.productName;
  const color = location.state.color;
  const numberOfProduct = location.state.productNum;

  const deliveryCost = location.state.deliveryCost;
  const parcelType = location.state.parcelType;
  const totalCost = numberOfProduct * location.state.productPrice + deliveryCost;
  const productImageId = location.state.productImageId;


  const goBack = (e) =>{
    navigate(-1);
  }

  const order = (e) =>{
    e.preventDefault();
    if(uploadImage == -1 || uploadImage == undefined){
      setModalContent(<p> 입력되지 않은 정보가 존재합니다. </p>);
      setShow(true);
    }else{

      const currentTime = new Date();
      const year = currentTime.getFullYear();
      const month = currentTime.getMonth() + 1; // 월은 0부터 시작하므로 1을 더해줌
      const day = currentTime.getDate();
      const hours = currentTime.getHours();
      const minutes = currentTime.getMinutes();
      const seconds = currentTime.getSeconds();
      var timeFormat = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;

      const formData = new FormData();
      formData.append("id", 1); //default for DTO
      formData.append("orderDate", timeFormat);
      if(sessionStorage.getItem("id") != null && sessionStorage.getItem("id") != undefined){
        formData.append("userId", sessionStorage.getItem("id"));
      }else{
        formData.append("userId", "guest");
      }
      formData.append("productId", productId);
      formData.append("color", color);
      formData.append("num", numberOfProduct);
      formData.append("totalCost", totalCost);
      formData.append("orderName", document.getElementById("ip_name").value);
      formData.append("orderPhone", phoneNum);
      formData.append("orderZonecode", zoneCode);
      formData.append("orderAddress", address);
      formData.append("addressDetail", addressDetail);
      formData.append("imageId", uploadImage);
      formData.append("state", "주문 완료");
      formData.append("parcelType", parcelType);
      axios({
        method: "post",
        url: 'http://104.198.11.59:8090/shop-backend/order/insert',
        data: formData
      })
      .then(function (response){
        //handle success
        navigate('../successOrder',
          {
            state: {
              content: response.data
          }
        });
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
          url: 'http://104.198.11.59:8090/shop-backend/order/insertUserImage',
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
    setShow(true);
  }


  return (
      <Wrapper>
        <div className="order_main" id="main">
          <div className="inner mg0">
            <div className="grid_t">
              <div className="gr-1" >
                <span className="image main order_span_img"><img className="order_img" src={"../productImage/"+productImageId+".jpg"} alt="" /></span>
              </div>
              <div className="gr-11 calign">
                <h1>{productName}</h1>
              </div>

              <div className="gr-12 mb2 grid_t" style={{boxShadow: "3px 3px 3px 3px rgb(98 217 182)", borderRadius:"20px"}}>
                <div className="gr-4 calign" style={{borderRight: "3px solid rgb(98 217 182)", paddingTop: "20px"}}>
                  <h2 style={{color: "#00db9a"}}> 주문 상세 </h2>
                </div>
                <div className="gr-8 calign" style={{paddingTop: "20px"}}>
                  <div>
                    {productName} | {color} | 수량 {numberOfProduct}
                  </div>
                </div>
                <div className="gr-12 calign" style={{borderTop: "3px solid rgb(98 217 182)", paddingTop: "20px"}}>
                  <p>상품 금액: {numberOfProduct * location.state.productPrice}원 , 배송비: {deliveryCost}원</p>
                  <h3> 총액 : {totalCost}원 </h3>
                </div>
              </div>
            </div>
            <div style={{boxShadow: "3px 3px 3px 3px rgb(98 217 182)", borderRadius:"20px", padding: "2rem"}}>
              <h2 className="calign"> 안내 사항 </h2>
              <p className="order-content">
                {orderContent}
              </p>
                {parcelType == "반값 택배"?
                <>
                  <h2 className="calign"> 반값 택배 사항 </h2>
                  <a href="https://www.cvsnet.co.kr/service/search-store/index.do" target="_blank" style={{color: "blue"}}>
                    편의점 위치 확인
                  </a>
                  <p className="mt-3">
                    반값 택배는 GS편의점 택배를 이용합니다.<br/>
                    POSTBOX가 설치된 점포를 위 링크에서 확인 후
                    해당 점포 주소를 입력해주시기 바랍니다.
                    (만약 근처 지점이 없다면 일반 택배를 이용해주시기 바랍니다)
                  </p>
                </>
                :""}
            </div>

            <form onSubmit={order}>
              <div className="grid_t" style={{padding: "30px", boxShadow: "3px 3px 3px 3px rgb(98 217 182)"}}>
                <div className="gr-4 calign pt1">
                  <h2 className="mg0">주문자 이름</h2>
                </div>
                <div className="gr-8">
                  <input type="text" className="prl1" id="ip_name" value={name} required></input>
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
                  <input type="text" maxLength={255} required className="prl1" id="address_detail" placeholder={parcelType == "반값 택배"? "지점 명":"상세주소"} value={addressDetail}
                    onChange={(e) =>setAddressDetail(e.target.value)}
                  />
                </div>

                <div className="gr-12 mt3">
                  <span className="image main detail_span_img">
                    <h2 > 송금 내역 </h2>
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
                  </span>
                </div>

                <div className="gr-6 calign pt3">
                  <button className="bt_order" onClick={goBack}> Back </button>
                </div>

                <div className="gr-6 calign pt3">
                  <input type="submit" value="Order" />
                </div>
              </div>
            </form>
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

export default Order;
