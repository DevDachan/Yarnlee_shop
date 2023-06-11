import React, { useState , useRef,useEffect} from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import Modal from 'react-bootstrap/Modal';

import Phone from "../order/Phone";


const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-bottom: 8em;
`;

function Login(props) {
    const navigate = useNavigate();
    const [id, setId] = useState();
    const [infoType, setInfoType] = useState("전화 번호");
    const [phoneNum, setPhoneNum] = useState();

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleOpen = () => setShow(true);


    const onClick = (event) =>{
      event.preventDefault();
      const formData = new FormData();
      formData.append("type", infoType);

      if(infoType == "전화 번호"){
        formData.append("name", document.getElementById("ip_name").value);
        formData.append("content", phoneNum);
      }else{
        formData.append("name", "none");
        formData.append("content", document.getElementById("ip_order").value);
      }
      axios({
        method: "post",
        url: 'http://localhost:8090/shop-backend/order/getOrderHistory',
        data: formData
      })
      .then(function (response){
        //handle success
        if(response.data == ""){
            setShow(true);
        }else{
          if(infoType == "전화 번호"){
            sessionStorage.setItem('phoneNum', phoneNum);
            sessionStorage.setItem('name', document.getElementById("ip_name").value);
          }else{
            sessionStorage.setItem('name', document.getElementById("ip_order").value);
          }

          navigate('../orderList', {
            state: {
              list: response.data
            }
          });
        }
      })
      .catch(function(error){
        //handle error
      })
      .then(function(){
        // always executed
      });
    }


    useEffect( () => {
      if(sessionStorage.getItem("name") != null && sessionStorage.getItem("phone") != null
        && sessionStorage.getItem("name") != undefined && sessionStorage.getItem("phone") != undefined
      ){
        const formData = new FormData();
        formData.append("type", "전화 번호");
        formData.append("name", sessionStorage.getItem("name"));
        formData.append("content", sessionStorage.getItem("phone"));
        axios({
          method: "post",
          url: 'http://localhost:8090/shop-backend/order/getOrderHistory',
          data: formData
        })
        .then(function (response){
          //handle success
          if(response.data == ""){
              setShow(true);
          }else{
            navigate('../orderList', {
              state: {
                list: response.data
              }
            });
          }
        })
        .catch(function(error){
          //handle error
        })
        .then(function(){
          // always executed
        });
      }
    },[]);

    const changeType = (e) =>{
      setInfoType(e.target.value);
    }

    return (
        <Wrapper>
          <div className="grid_t" style={{margin: "30px"}}>
            <div className="gr-12 mb-4">
              <select className="select-orderLogin" onChange={changeType}>
                <option key="phoneNum" value="전화 번호" > 전화 번호</option>
                <option key="orderNum" value="주문 번호"> 주문 번호</option>
              </select>
            </div>
            <div className="gr-12">
              <form className="row form-orderLogin" onSubmit={onClick}>
              {
                infoType == "주문 번호" ?
                (<>
                  <div className="gr-3 calign">
                    <h3 style={{paddingTop:"20px"}}> 주문 번호 </h3>
                  </div>
                  <div className="gr-9">
                    <input type="text" id="ip_order" required/>
                  </div>
                </>)
                :
                (<>
                  <div className="gr-3 calign">
                    <h3 style={{paddingTop:"20px"}}> 전화 번호</h3>
                  </div>
                  <div className="gr-9">
                    <Phone setPhoneNum={setPhoneNum} phoneNum={phoneNum}/>
                  </div>
                  <div className="gr-3 calign">
                    <h3 style={{paddingTop:"20px"}}> 이 름 </h3>
                  </div>
                  <div className="gr-9">
                    <input type="text" id="ip_name" required/>
                  </div>
                </>)
              }
                <div className="gr-12 calign mt1 mr1">
                  <input type="submit" value="조회하기" />
                </div>

              </form>
            </div>

          </div>
          <Modal show={show} onHide={handleClose}>
            <Modal.Header>
              <Modal.Title>입력 오류</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              올바른 내용을 입력해주세요
            </Modal.Body>
            <Modal.Footer>
              <button onClick={handleClose}>닫기</button>
            </Modal.Footer>
          </Modal>

        </Wrapper>
    );
}

export default Login;
