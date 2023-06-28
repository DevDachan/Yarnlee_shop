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
    min-height: 70vw;
`;

function Login(props) {
    const navigate = useNavigate();
    const [id, setId] = useState();
    const [pwd, setPwd] = useState();
    const [show, setShow] = useState(false);
    const [modalContent, setModalContent] = useState();
    const handleClose = () => setShow(false);
    const handleOpen = () => setShow(true);



    const goRegister = () =>{
      navigate(`/register`);
    }

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
        url: 'http://104.198.11.59:8090/shop-backend/user/login',
        data: {
          userId: id,
          password: pwd
        }
      })
      .then(function (response){
        //handle success
        if(response.data == ""){
          setModalContent("아이디 혹은 비밀번호가 잘못되었습니다.");
          setShow(true);
        }else{
          sessionStorage.setItem("id", response.data.id);
          sessionStorage.setItem("name", response.data.name);
          sessionStorage.setItem("phone", response.data.phone);
          navigate('../', {
            state: {
              userName: "dachan"
            }
          });
          window.location.reload();
        }
      })
      .catch(function(error){
        //handle error
          navigate('./', {
          state: {
            userName: "dachan"
          }
        });
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
            <div className="gr-6 ralign mt1 mr1">
              <button onClick={onClick}> 로그인 </button>
            </div>
            <div className="gr-6 lalign mt1 mr1">
              <button onClick={goRegister}> 회원 가입</button>
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

export default Login;
