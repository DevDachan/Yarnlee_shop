import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import Phone from "../order/Phone";
import PostSelector from "../order/PostSelector";


const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-bottom: 8em;
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

    const [idDup, setIdDup] = useState(true);
    const [pwdDup, setPwdDup] = useState(true);
    const [phoneDup, setPhoneDup] = useState(true);


    const onClick = () =>{
      axios({
        method: "post",
        url: 'http://localhost:8090/shop-backend/user/register',
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
        sessionStorage.setItem("name", userName);
        sessionStorage.setItem("phoneNum", phoneNum);

        navigate('../', {
          state: {
            userName: "dachan"
          }
        });
      })
      .catch(function(error){
        //handle error
        console.log(error);
          navigate('./', {
          state: {
            userName: "dachan"
          }
        });
      })
      .then(function(){
        // always executed
      });
    }

    const idCheck = (e) => {
      var formData = new FormData();
      formData.append("id", e.target.value);

      axios({
        method: "post",
        url: 'http://localhost:8090/shop-backend/user/idCheck',
        data: formData
      })
      .then(function (response){
        //handle success
        console.log(response.data);
        if(response.data == false){
          document.getElementById("p-dupId").style.display = "block";
          setIdDup(false);
        }else{
          document.getElementById("p-dupId").style.display = "none";
          setIdDup(true);
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


    return (
        <Wrapper>
          <div className="grid_t" style={{margin: "30px"}}>

            <div className="gr-3">
              <h3> 아이디 </h3>
            </div>
            <div className="gr-9">
              <input type="text" id="ip_id" value={id} onBlur={idCheck}/>
            </div>
            <div className="gr-12">
              <p id="p-dupId" className="register-alert"> 이미 존재하는 아이디 입니다. </p>
            </div>

            <div className="gr-3">
              <h3> 이름 </h3>
            </div>
            <div className="gr-9">
              <input type="text" id="ip_name" value={userName} onBlur={setIdDup}/>
            </div>

            <div className="gr-3">
              <h3> 비밀번호 </h3>
            </div>
            <div className="gr-9">
              <input type="password" id="ip_pwd" value={pwd} onChange={(e) =>setPwd(e.target.value)}/>
            </div>

            <div className="gr-3">
              <h3> 비밀번호 확인 </h3>
            </div>
            <div className="gr-9">
              <input type="password" id="ip_pwd" value={pwd2}/>
            </div>
            <div className="gr-12">
              <p id="p-dupPwd" className="register-alert"> 비밀번호가 일치하지 않습니다. </p>
            </div>

            <div className="gr-3">
              <h3>전화번호</h3>
            </div>
            <div className="gr-9">
              <Phone
                phoneNum ={phoneNum}
                setPhoneNum = {setPhoneNum}
              />
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
              <input type="text" required className="prl1" id="address_detail" placeholder="상세주소" value={addressDetail} onChange={(e) =>setAddressDetail(e.target.value)}/>
            </div>



            <div className="gr-12 calign mt2 mr1">
              <button onClick={onClick}> 회원 가입</button>
            </div>
          </div>
        </Wrapper>
    );
}

export default Register;
