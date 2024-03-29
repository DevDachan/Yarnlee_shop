import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import Modal from 'react-bootstrap/Modal';
const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-bottom: 8em;
`;

function AdminLogin(props) {
    const navigate = useNavigate();
    const [id, setId] = useState();
    const [pwd, setPwd] = useState();
    const [show, setShow] = useState(false);
    const [modalContent, setModalContent] = useState();
    const handleClose = () => setShow(false);
    const handleOpen = () => setShow(true);

    const onClick = () =>{
      if(document.getElementById("ip_id").value == ""){
        setModalContent("아이디를 입력해주세요.");
        setShow(true);
        return null;
      }else if(document.getElementById("ip_pwd").value == ""){
        setModalContent("비밀번호를 입력해주세요.");
        setShow(true);
        return null;
      }

      axios({
        method: "post",
        url: 'http://localhost:8090/shop-backend/admin/adminLogin',
        data: {
          id: id,
          password: pwd
        }
      })
      .then(function (response){
        //handle success
        if(response.data.id == "id"){
          setModalContent("아이디가 잘못되었습니다.");
          setShow(true);
        }else if(response.data.id == "password"){
          setModalContent("비밀번호가 잘못되었습니다.");
          setShow(true);
        }else{
          sessionStorage.setItem("admin", response.data.id);
          sessionStorage.setItem("adminHash", response.data.hashKey);
          sessionStorage.setItem("jwt-auth-token", response.data.token);

          navigate('../adminMain');
          window.location.reload();
        }
      })
      .catch(function(error){
        //handle error
      })
      .then(function(){
        // always executed
      });
    }




    return (
        <Wrapper>
          <div className="grid_t" style={{margin: "30px"}}>
            <div className="gr-3">
              <h3> ID </h3>
            </div>
            <div className="gr-9">
              <input type="text" id="ip_id" value={id} onChange={(e) =>setId(e.target.value)}/>
            </div>
            <div className="gr-3">
              <h3> Password </h3>
            </div>
            <div className="gr-9">
              <input type="password" id="ip_pwd" value={pwd} onChange={(e) =>setPwd(e.target.value)}/>
            </div>
            <div className="gr-12 mt1 mr1">
              <input type="button" className="bt-login" onClick={onClick} value="로그인" />
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

export default AdminLogin;
