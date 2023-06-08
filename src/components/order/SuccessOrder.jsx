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

function SuccessOrder(props) {
    const navigate = useNavigate();
    const location = useLocation();
    const [show, setShow] = useState(true);
    const [content, sethContent] = useState(location.state.content);
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
              <Modal.Title>안내</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              주문이 완료 되었습니다.<br />
              주문번호는 {content}입니다. <br />
              주문 조회 메뉴를 통해 주문된 내용을 확인해주시기 바랍니다.
            </Modal.Body>
            <Modal.Footer>
              <button onClick={handleClose}>닫기</button>
            </Modal.Footer>
          </Modal>
        </Wrapper>
    );
}

export default SuccessOrder;
