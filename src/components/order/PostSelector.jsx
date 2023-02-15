import React, { useState, useEffect, useRef } from "react";
import DaumPostcode from 'react-daum-postcode';


import Modal from 'react-bootstrap/Modal';


function PostSelector(props){
  const [show, setShow] = useState(false);
  const [openPostcode, setOpenPostcode] = useState(false);


  const handleClose = () => setShow(false);
  const handleOpen = () => setShow(true);

  const selectAddress = (data) => {
          props.setAddress(data.address);
          props.setZonecode(data.zonecode);

          console.log(`
              주소: ${data.address},
              우편번호: ${data.zonecode}`)
          setOpenPostcode(false);
          setShow(false);
    }

  return(
    <div>
      <button onClick={handleOpen}  variant="outline-primary" > Address </button>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header>
          <Modal.Title> 주소 찾기 </Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <DaumPostcode
                onComplete={selectAddress}  // 값을 선택할 경우 실행되는 이벤트
                autoClose={false} // 값을 선택할 경우 사용되는 DOM을 제거하여 자동 닫힘 설정
             />
        </Modal.Body>
        <Modal.Footer>
          <button onClick={handleClose}> 닫기 </button>
        </Modal.Footer>
      </Modal>
    </div>
  )
}

export default PostSelector;
