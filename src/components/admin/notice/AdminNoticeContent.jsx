import React, { useState ,useEffect } from "react";
import { useNavigate,useLocation } from "react-router-dom";
import axios from "axios";
import styled from "styled-components";
import queryString from 'query-string';
import Modal from 'react-bootstrap/Modal';

const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-bottom: 8em;
    margin:auto;
    min-width: 750px;
`;

function AdminNoticeContent(props) {
    const navigate = useNavigate();
    const location = useLocation();
    const { search } = useLocation();
    const {noticeId} = queryString.parse(search);

    const [show, setShow] = useState(false);
    const [modalContent, setModalContent] = useState();
    const handleClose = () => setShow(false);
    const handleOpen = () => setShow(true);
    const [notice, setNotice] = useState();

    useEffect(() => {
      if(sessionStorage.getItem("admin") == null || sessionStorage.getItem("admin") == undefined){
        navigate('../adminLogin');
      }else if(noticeId == null){
        navigate("../adminNoticeMain");
      }
      axios({
        method: "get",
        url: 'http://localhost:8090/shop-backend/notice/getNoticeContent',
        params:{
          id: noticeId
        }
      })
      .then(function (response){
        //handle success
        if(response.data == null || response.data == undefined){
          navigate("../adminNoticeMain");
        }
        setNotice(response.data);
        console.log(response.data);
      })
      .catch(function(error){
        //handle error
      })
      .then(function(){
        // always executed
      });
    }, []);


    const deleteNoticeAlert = (e) =>{
      setModalContent("해당 공지사항을 삭제하시겠습니까?");
      setShow(true);
    }

    const deleteNotice = e =>{
      axios({
        method: "delete",
        url: 'http://localhost:8090/shop-backend/notice/deleteNotice',
        params:{
          noticeId: noticeId,
          hashKey: sessionStorage.getItem("adminHash"),
          id: sessionStorage.getItem("admin")
        }
      })
      .then(function (response){
        //handle success
        navigate("../adminNoticeMain");
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
        <div className="noticeList">
          <table>
            <tbody>
            <tr>
              <td className="lalign pl3" style={{width: "160px"}}>제목</td>
              <td className="lalign font400" >{notice == undefined? "": notice.title}</td>
            </tr>
            <tr>
              <td className="lalign pl3">작성 일</td>
              <td className="lalign font400">2023.06.10</td>
            </tr>
            <tr>
              <td className="lalign pl3">조회 수</td>
              <td className="lalign font400">{notice == undefined? "": notice.hits}</td>
            </tr>
            <tr>
              <td colSpan="2" style={{backgroundColor: "white"}}>
                <div className="noticeContent-content">
                  {notice == undefined? "": notice.content}
                </div>
              </td>
            </tr>
            </tbody>
          </table>
          <div className="calign pt3 grid_t">
            <div className="gr-3">
            </div>
            <div className="gr-2">
              <button className="bt_order" onClick={(e) => {navigate("../adminNoticeMain")}}> 목록으로 </button>
            </div>
            <div className="gr-2">
              <button className="bt_order" onClick={(e) => {navigate("../adminNoticeEdit")}}> 수정하기 </button>
            </div>
            <div className="gr-2">
              <button className="bt_order" onClick={deleteNoticeAlert}> 삭제하기 </button>
            </div>
            <div className="gr-3">
            </div>
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
            <div className="grid_t" style={{width: "100%"}}>
              <div className="gr-6 lalign">
                <button onClick={handleClose}>취소</button>
              </div>
              <div className="gr-6 ralign">
                <button className="bt-delete" onClick={deleteNotice}>삭제</button>
              </div>
            </div>
          </Modal.Footer>
        </Modal>
        </Wrapper>
    );
}

export default AdminNoticeContent;
