import React, { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
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

function SuccessRegister(props) {
    const navigate = useNavigate();
    const location = useLocation();

    const [show, setShow] = useState(true);

    const handleClose = () => {
      setShow(false);
      navigate("../");
      window.location.reload();
    };
    const handleOpen = () => setShow(true);

    return (
        <Wrapper>
          <Modal show={show} onHide={handleClose}>
            <Modal.Header>
              <Modal.Title>회원 가입</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              축하합니다 {location.state.userName}님!! <br />
              Yarnlee Mall에 회원가입이 되었습니다.
            </Modal.Body>
            <Modal.Footer>
              <button onClick={handleClose}>닫기</button>
            </Modal.Footer>
          </Modal>
        </Wrapper>
    );
}

export default SuccessRegister;
