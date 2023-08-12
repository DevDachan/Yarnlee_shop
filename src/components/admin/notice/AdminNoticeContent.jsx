import React, { useState ,useEffect } from "react";
import { useNavigate,useLocation } from "react-router-dom";
import axios from "axios";
import styled from "styled-components";
import queryString from 'query-string';
import Modal from 'react-bootstrap/Modal';
import Swal from 'sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
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
        url: 'http://104.198.11.59:8090/shop-backend/notice/getNoticeAdminContent',
        params:{
          id: noticeId
        },
        headers:{
          "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
        }
      })
      .then(function (response){
        //handle success
        if(response.data == null || response.data == undefined){
          navigate("../adminNoticeMain");
        }
        setNotice(response.data);
      })
      .catch(function(error){
        if(error.response.status === 401){
          sessionStorage.clear();
          Swal.fire({
            icon: 'error',
            title: '세션 만료',
            text: '다시 로그인 해주시기 바랍니다.',
            confirmButtonText: '확인'
          }).then(() => {
            navigate("../adminLogin");
            window.location.reload();
          });

        }else if(error.response.status === 500){
          sessionStorage.clear();

          Swal.fire({
            icon: 'error',
            title: '세션 만료',
            text: '다시 로그인 해주시기 바랍니다.',
            confirmButtonText: '확인'
          }).then(() => {
            navigate("../adminLogin");
            window.location.reload();
          });
        }
      });
    }, []);


    const deleteNoticeAlert = (e) =>{
      setModalContent("해당 공지사항을 삭제하시겠습니까?");
      setShow(true);
    }

    const deleteNotice = e =>{
      axios({
        method: "delete",
        url: 'http://104.198.11.59:8090/shop-backend/notice/deleteNotice',
        params:{
          noticeId: noticeId,
          hashKey: sessionStorage.getItem("adminHash"),
          id: sessionStorage.getItem("admin")
        },
        headers:{
          "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
        }
      })
      .then(function (response){
        //handle success
        navigate("../adminNoticeMain");
      })
      .catch(function(error){
        if(error.response.status === 401){
          window.location.reload();

        }else if(error.response.status === 500){
          window.location.reload();
        }
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
              <td className="lalign font400">{notice == undefined? "": notice.createTime}</td>
            </tr>
            <tr>
              <td className="lalign pl3">조회 수</td>
              <td className="lalign font400">{notice == undefined? "": notice.hits}</td>
            </tr>
            <tr>
              <td colSpan="2" style={{backgroundColor: "white"}}>
                {notice && <div className="noticeContent-content" dangerouslySetInnerHTML={{__html: notice.content}} />}
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
              <button className="bt_order" onClick={(e) => {navigate("../adminNoticeEdit?noticeId="+noticeId)}}> 수정하기 </button>
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
