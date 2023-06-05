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

function Login(props) {
    const navigate = useNavigate();
    const [show, setShow] = useState(true);
    const handleClose = () => {
      setShow(false);
      sessionStorage.clear();
      navigate("../");
      window.location.reload();
    };
    const handleOpen = () => setShow(true);

    return (
        <Wrapper>
          <Modal show={show} onHide={handleClose}>
            <Modal.Header>
              <Modal.Title>로그아웃</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              정상적으로 로그아웃됐습니다.
            </Modal.Body>
            <Modal.Footer>
              <button onClick={handleClose}>닫기</button>
            </Modal.Footer>
          </Modal>
        </Wrapper>
    );
}

export default Login;
