import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import Phone from "../order/Phone";
import PostSelector from "../order/PostSelector";
import Modal from 'react-bootstrap/Modal';

const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-bottom: 8em;
    min-width: 720px;
`;

function Register(props) {
    const navigate = useNavigate();
    const [id, setId] = useState();
    const [pwd, setPwd] = useState();
    const [pwd2, setPwd2] = useState();
    const [userName, setUserName] = useState();
    const [address, setAddress] = useState();
    const [zoneCode, setZonecode] = useState();
    const [phoneNum, setPhoneNum] = useState();
    const [addressDetail, setAddressDetail] = useState();

    const [phoneAuth, setPhoneAuth] = useState(false);
    const [idDup, setIdDup] = useState(false);
    const [pwdDup, setPwdDup] = useState(false);
    const [phoneDup, setPhoneDup] = useState(false);

    const [show, setShow] = useState(false);
    const [modalContent, setModalContent] = useState();
    const handleClose = () => setShow(false);
    const handleOpen = () => setShow(true);


    useEffect( () => {
      if(sessionStorage.getItem("jwt-auth-token") == null &&  sessionStorage.getItem("jwt-auth-token") == undefined
      ){
        sessionStorage.clear();
        navigate("../login");
      }else{
        axios({
          method: "post",
          url: 'http://localhost:8090/shop-backend/user/info',
          headers:{
            "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
          },
          params: {
            id: sessionStorage.getItem("id")
          }
        })
        .then(function (response){
          //handle success
          setId(response.data.id);
          setUserName(response.data.name);
          setAddress(response.data.address);
          setPhoneNum(response.data.phone);
          setPhoneAuth(true);
          setZonecode(response.data.zoneCode);
          setAddressDetail(response.data.addressDetail);
        })
        .catch(function(error){
          //handle error
          sessionStorage.clear();
          navigate("../login");
          console.log(error);
        });
      }
    },[]);

    const onClick = () =>{
      if(id == undefined || pwd == undefined || pwd2 == undefined || userName == undefined || address == undefined || zoneCode == undefined ||
        phoneNum == undefined || addressDetail == undefined
      ){
        setModalContent("입력되지 않은 내용이 존재합니다.");
        setShow(true);
        return null;
      }
      else if(idDup || pwdDup || phoneDup){
        setModalContent("올바르지 않은 내용이 존재합니다.");
        setShow(true);
        return null;
      }else if(phoneAuth == false){
        setModalContent("핸드폰 인증을 해주세요");
        setShow(true);
        return null;
      }else{
        axios({
          method: "post",
          url: 'http://localhost:8090/shop-backend/user/register',
          headers:{
            "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
          },
          data: {
            id: id,
            password: pwd,
            name: userName,
            phone: phoneNum,
            zoneCode: zoneCode,
            address: address,
            addressDetail: addressDetail
          }
        })
        .then(function (response){
          //handle success

          sessionStorage.setItem("id", id);

          navigate('../', {
            state: {
              userName: userName
            }
          });
        })
        .catch(function(error){
          //handle error
          sessionStorage.clear();
          navigate("../login");
          console.log(error);
        });
      }
    }

    const sendSecretKey = (e) =>{
      if(!phoneDup && phoneNum != undefined && phoneNum.split("-").length -1 == 2){
        axios({
          method: "get",
          url: 'http://localhost:8090/shop-backend/phone/checkAuth',
          params: {
            phoneNum:phoneNum
          }
        })
        .then(function (response){
          if(response.data == "Yes"){
            setPhoneAuth(true);
            document.getElementById("bt-sendSecretKey").innerHTML = "인증 완료";
            document.getElementById('h3-SecretKey').style.display = "none";
            document.getElementById('ip-SecretKey').style.display = "none";
            document.getElementById('bt-OkSecretKey').style.display = "none";

          }else{
            var formData = new FormData();
            formData.append("phoneNum",phoneNum);
            formData.append("checkAuth","No");
            formData.append("secretKey","");

            axios({
              method: "post",
              url: 'http://localhost:8090/shop-backend/phone/register',
              data: formData
            })
            .then(function (response){
              setModalContent("인증번호가 전송되었습니다.");
              setShow(true);
              document.getElementById("bt-sendSecretKey").innerHTML = "재전송";
              document.getElementById('h3-SecretKey').style.display = "block";
              document.getElementById('ip-SecretKey').style.display = "block";
              document.getElementById('bt-OkSecretKey').style.display = "block";
            });
          }
        });
      }
    }

    const checkSecretKey = (e) =>{
      axios({
        method: "get",
        url: 'http://localhost:8090/shop-backend/phone/checkKey',
        params:{
          phoneNum: phoneNum,
          secretKey : document.getElementById("ip-SecretKey").value
        }
      })
      .then(function (response){
        if(response.data == "Success"){
          setPhoneAuth(true);
          document.getElementById("bt-sendSecretKey").innerHTML = "인증완료";
          document.getElementById('h3-SecretKey').style.display = "none";
          document.getElementById('ip-SecretKey').style.display = "none";
          document.getElementById('bt-OkSecretKey').style.display = "none";
        }else{
          setModalContent("인증번호가 일치하지 않습니다.");
          setShow(true);
        }
      });
    }

    const checkPhoneDup = (e) =>{
      var formData = new FormData();
      formData.append("phone", e.target.value);

      axios({
        method: "post",
        url: 'http://localhost:8090/shop-backend/user/phoneCheck',
        data: formData
      })
      .then(function (response){
        //handle success
        if(response.data == true){
          document.getElementById("p-dupPhone").style.display = "block";
          setPhoneDup(true);
        }else{
          document.getElementById("p-dupPhone").style.display = "none";
          setPhoneDup(false);
        }
        document.getElementById("bt-sendSecretKey").innerHTML = "변경하기";
        document.getElementById('h3-SecretKey').style.display = "none";
        document.getElementById('ip-SecretKey').style.display = "none";
        document.getElementById('bt-OkSecretKey').style.display = "none";
        setPhoneAuth(false);
      })
      .catch(function(error){
        //handle error
        console.log(error);
      });
    }


    const pwdCheck = (e) =>{
      if(pwd != e.target.value && pwd != undefined){
        document.getElementById("p-dupPwd").style.display = "block";
        setPwdDup(true);
      }else{
        document.getElementById("p-dupPwd").style.display = "none";
        setPwdDup(false);
      }
    }


    return (
        <Wrapper>
          <div className="grid_t" style={{margin: "30px"}}>

            <div className="gr-3">
              <h3> 아이디 </h3>
            </div>
            <div className="gr-9">
              <input type="text" id="ip_id" disabled value={id}/>
            </div>
            <div className="gr-12">
              <p id="p-dupId" className="register-alert"> 이미 존재하는 아이디 입니다. </p>
            </div>

            <div className="gr-3">
              <h3> 이름 </h3>
            </div>
            <div className="gr-9">
              <input type="text" id="ip_name" maxLength="10" value={userName} onChange={ (e) => setUserName(e.target.value)}/>
            </div>

            <div className="gr-3">
              <h3> 새 비밀번호 </h3>
            </div>
            <div className="gr-9">
              <input type="password" id="ip_pwd" maxLength="20" value={pwd} onChange={(e) =>setPwd(e.target.value)}/>
            </div>

            <div className="gr-3">
              <h3> 비밀번호 확인 </h3>
            </div>
            <div className="gr-9">
              <input type="password" id="ip_pwd" maxLength="20" value={pwd2} onChange={(e) =>setPwd2(e.target.value)} onBlur={pwdCheck}/>
            </div>
            <div className="gr-12">
              <p id="p-dupPwd" className="register-alert"> 비밀번호가 일치하지 않습니다. </p>
            </div>

            <div className="gr-3 mt2">
              <h3>전화번호</h3>
            </div>
            <div className="gr-6 mt2">
              <Phone
                phoneNum={phoneNum || ""}
                setPhoneNum = {setPhoneNum}
                onBlur= {checkPhoneDup}
              />
            </div>
            <div className="gr-3 mt2">
              <button id="bt-sendSecretKey" onClick={sendSecretKey}> 변경하기 </button>
            </div>

            <div className="gr-3 mt2">
              <h3 id="h3-SecretKey">인증번호</h3>
            </div>
            <div className="gr-6 mt2">
              <input id="ip-SecretKey" type="text" maxLength="6" />
            </div>
            <div className="gr-3 mt2">
              <button id="bt-OkSecretKey" onClick={checkSecretKey}> 확인 </button>
            </div>


            <div className="gr-12">
              <p id="p-dupPhone" className="register-alert"> 이미 가입된 전화번호 입니다. </p>
            </div>


            <div className="gr-4 calign mt2">
              <PostSelector
                setAddress = {setAddress}
                setZonecode = {setZonecode}
              />
            </div>
            <div className="gr-8 mt2">
              <input type="text" className="prl1"  disabled id="zoneCode" value={zoneCode} />
            </div>
            <div className="gr-12">
              <input type="text" className="prl1"  disabled id="address" value={address} />
            </div>
            <div className="gr-12">
              <input type="text" required className="prl1" id="address_detail" maxLength="50" placeholder="상세주소" value={addressDetail} onChange={(e) =>setAddressDetail(e.target.value)}/>
            </div>

            <div className="gr-12 calign mt2 mr1">
              <button onClick={onClick}> 정보 수정</button>
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

export default Register;
