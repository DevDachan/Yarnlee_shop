import React from 'react';
import ReactLoading from 'react-loading';
import Modal from 'react-bootstrap/Modal';


function Loading({type, message,cancelLoading,show}) {
  return (
    <>
    <Modal show={show} onHide={cancelLoading}>
      <Modal.Header>
        <Modal.Title>주문</Modal.Title>
      </Modal.Header>
      <Modal.Body>
            <ReactLoading
              type={type}
              style={{
                margin:"auto",
                height:'50%',
                width:'50%'
              }}
            />
            <div>
              <h3 className="mt3 calign">
                주문 정보를 확인하는 중입니다.
              </h3>
            </div>
      </Modal.Body>
      <Modal.Footer>
        <button onClick={cancelLoading}>취소</button>
      </Modal.Footer>
    </Modal>
    </>
  );
}

export default Loading;
