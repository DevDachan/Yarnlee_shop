import React, { useState, useEffect, useRef } from "react";
import { useNavigate,useLocation } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import Modal from 'react-bootstrap/Modal';
import Phone from "../../order/Phone";
import PostSelector from "../../order/PostSelector";
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
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
  const [orderDetail, setOrderDetail] = useState();
  const [productDetail, setProductDetail] = useState();

  const [name, setName] = useState();
  const [phoneNum, setPhoneNum] = useState();
  const [address, setAddress] = useState();
  const [zoneCode, setZonecode] = useState();
  const [addressDetail, setAddressDetail] = useState();
  const [imageId, setImageId] = useState();
  const [totalCost,setTotalCost] = useState("");
  const [uploadImage,setUploadImage] = useState();
  const [orderState, setOrderState] = useState();
  const [modealYesNo, setModealYesNo] = useState(false);
  const [parcelNum, setParcelNum] = useState();
  const [parcelType, setParcelType] = useState();

  const [show, setShow] = useState(false);
  const [modalContent, setModalContent] = useState();
  const [modalFunction, setModalFunction] = useState();
  const handleClose = () => setShow(false);
  const handleOpen = () => setShow(true);


  useEffect(() => {
    if(sessionStorage.getItem("admin") == null || sessionStorage.getItem("admin") == undefined || sessionStorage.getItem("jwt-auth-token") == null || sessionStorage.getItem("jwt-auth-token") == undefined){
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

    axios({
      method: "get",
      url: 'http://104.198.11.59:8090/shop-backend/order/getAdminOrderHistory',
      params:{
        hashKey: sessionStorage.getItem("adminHash"),
        id: sessionStorage.getItem("admin"),
        orderId: location.state.orderId,
      },
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      //handle success
      if(response.data == ""){
        navigate('../adminLogin');
      }else{
        setProductDetail(response.data.product);
        setOrderDetail(response.data.order);
        setName(response.data.order.orderName);
        setPhoneNum(response.data.order.orderPhone);
        setAddress(response.data.order.orderAddress);
        setZonecode(response.data.order.orderZonecode);
        setAddressDetail(response.data.order.addressDetail);
        setImageId(response.data.order.imageId);
        setTotalCost(response.data.order.totalCost);
        setUploadImage(response.data.order.imageId);
        setOrderState(response.data.order.state);
        if(response.data.order.parcelNum == undefined){
          setParcelNum("");
        }else{
          setParcelNum(response.data.order.parcelNum);
        }
        setParcelType(response.data.order.parcelType);
      }

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

  const deleteOrder = (e) =>{
    setModalContent("해당 주문을 삭제하시겠습니까?");
    setModealYesNo(true);
    setModalFunction(() => yesDelete);
    setShow(true);
  }

  const yesDelete = (e) =>{
    axios({
      method: "delete",
      url: 'http://104.198.11.59:8090/shop-backend/order/delete',
      params:{
        hashKey: sessionStorage.getItem("adminHash"),
        id: sessionStorage.getItem("admin"),
        orderId: orderDetail.id
      },
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      //handle success
      navigate('../adminOrderList');
    })
    .catch(function(error){
      //handle error
      window.location.reload();
    });
  }


  const editOrder = (e) =>{
    setModalFunction(() => yesEdit);
    setModealYesNo(true);
    setModalContent("수정하시겠습니까?");
    setShow(true);
  }

  const yesEdit = (e) => {
    const formData = new FormData();
    formData.append("id", orderDetail.id); //default for DTO
    formData.append("orderDate", orderDetail.orderDate);
    formData.append("userId", orderDetail.userId);
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
    formData.append("state", orderState);
    formData.append("parcelType", parcelType);
    formData.append("parcelNum", parcelType);

    axios({
      method: "post",
      url: 'http://104.198.11.59:8090/shop-backend/order/edit',
      data: formData,
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      //handle success
      setShow(false);
    })
    .catch(function(error){
      //handle error
      window.location.reload();
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
          url: 'http://104.198.11.59:8090/shop-backend/order/insertUserImage',
          data: formData,
          headers: {
            'Content-Type': 'multipart/form-data',
            "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
          }
        })
        .then(function (response){
          if(response.data != -1){
            setUploadImage(response.data);
          }
        })
        .catch(function(error){
          //handle error
          window.location.reload();
        });
      }
    }
  }
  const previewImage = (e) =>{
    setModalContent(<img style={{width: "100%"}} src={uploadImage == undefined ? "" :"http://104.198.11.59/userImage/"+uploadImage+".jpg"}></img>);
    setModealYesNo(false);
    setShow(true);
  }

 const handleOrderStateChange = (e) =>{
   axios({
     method: "get",
     url: 'http://104.198.11.59:8090/shop-backend/order/changeOrderState',
     params: {
      id: orderDetail.id,
      state: e.target.value
     },
     headers:{
       "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
     }
   })
   .then(function (response){
     window.location.reload();
   })
   .catch(function(error){
     //handle error
     window.location.reload();
   });
 }

 const changeParcelNum = (e) =>{
  setParcelNum(e.target.value);
   axios({
     method: "get",
     url: 'http://104.198.11.59:8090/shop-backend/order/changeParcelNum',
     params: {
      id: orderDetail.id,
      data: e.target.value
     },
     headers:{
       "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
     }
   })
   .then(function (response){
   })
   .catch(function(error){
     //handle error
     window.location.reload();
   });
 }


  return (
      <Wrapper>
        <div className="order_main" id="main">
          <div className="inner mg0">
            <div className="grid_t">
              <div className="gr-2" id="">
                <span className="image main order_span_img"><img className="order_img" src={productDetail == undefined ? "":"http://104.198.11.59/productImage/"+productDetail.imageId+".jpg"} alt="" /></span>
              </div>
              <div className="gr-10 calign">
                <h1>{productDetail == undefined? "" : productDetail.name}</h1>
              </div>

              <div className="gr-12 mb2 grid_t" style={{boxShadow: "3px 3px 3px 3px rgb(98 217 182)", borderRadius:"20px"}}>
                <div className="gr-4 calign" style={{borderRight: "3px solid rgb(98 217 182)", paddingTop: "20px"}}>
                  <h2 style={{color: "#00db9a"}}> 주문 상세 </h2>
                </div>
                <div className="gr-8 calign" style={{paddingTop: "20px"}}>
                  <div>
                    {productDetail == undefined? "" : productDetail.name}  | {productDetail == undefined? "": orderDetail.color}  |  {productDetail == undefined? "" :orderDetail.num}개
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
                <input type="text" className="prl1" id="ip_name" defaultValue={name} onChange={(e) => setName(e.target.value)}></input>
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
                <input type="text" className="prl1"  disabled id="zoneCode" value={zoneCode} required onChange={(e) => setZonecode(e.target.value)}/>
              </div>
              <div className="gr-12">
                <input type="text" className="prl1"  disabled id="address" value={address} required onChange={(e) => setAddress(e.target.value)}/>
              </div>
              <div className="gr-12">
                <input type="text" required className="prl1" id="address_detail" defaultValue={orderDetail == undefined? "": orderDetail.addressDetail} onChange={(e) => setAddressDetail(e.target.value)}/>
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
                <img className="detail_img" src={uploadImage == undefined ? "" :"http://104.198.11.59/userImage/"+uploadImage+".jpg"}
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
        <div className="order_main" style={{padding: "30px", boxShadow: "3px 3px 3px 3px rgb(98 217 182)"}}>
          <div className="grid_t">
            <div className="gr-12 calign mt3 mb3">
              <h2 > 배송 상태 관리 </h2>
            </div>
            <div className="gr-6 calign">
              <h3> 주문 상태 </h3>
            </div>
            <div className="gr-6">
              <select className="changeState" value={orderState} onChange={handleOrderStateChange}>
                <option value="주문 완료" className="option_select">주문 완료</option>
                <option value="결재 완료" className="option_select">결재 완료</option>
                <option value="제작 중" className="option_select">제작 중</option>
                <option value="발송 완료" className="option_select">발송 완료</option>
              </select>
            </div>
            <div className="gr-6 mt3 calign">
              <h3> 송장 번호 </h3>
            </div>
            <div className="gr-6 mt3 calign">
              {
                parcelNum == ""?
                <input type="text" id="parcelNum" placeholder="송장 번호를 입력해주세요" onChange={changeParcelNum} />
                :
                <input type="text" id="parcelNum" value={parcelNum} onChange={changeParcelNum} />
              }
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
