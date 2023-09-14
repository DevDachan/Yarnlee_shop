import React, { useState ,useEffect, useRef } from "react";
import { useNavigate,useLocation } from "react-router-dom";
import axios from "axios";
import styled from "styled-components";
import queryString from 'query-string';
import Modal from 'react-bootstrap/Modal';
import ReactQuill from 'react-quill'
import 'react-quill/dist/quill.snow.css' // Quill 에디터 스타일시트
import ImageUploader from 'quill-image-uploader'
import Quill from 'quill';
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

function AdminNoticeEdit(props) {
  const navigate = useNavigate();
  const location = useLocation();
  // 만약 List 정보가 없을시에는 Login으로 이동처리
  const quillRef = useRef();
  const { search } = useLocation();
  const {noticeId} = queryString.parse(search);

  const [show, setShow] = useState(false);
  const [modalContent, setModalContent] = useState();
  const handleClose = () => setShow(false);
  const handleOpen = () => setShow(true);

  const [notice, setNotice] = useState();
  useEffect(() => {
    if(sessionStorage.getItem("admin") == null || sessionStorage.getItem("admin") == undefined || sessionStorage.getItem("jwt-auth-token") == null || sessionStorage.getItem("jwt-auth-token") == undefined){
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

    axios({
      method: "get",
      url: 'http://localhost:8090/shop-backend/notice/getNoticeAdminContent',
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
        navigate("../noticeMain");
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


  const imageHandler = () => {
    const input = document.createElement("input");
    input.setAttribute("type", "file");
    input.setAttribute("accept", "image/*");
    input.click();

    input.onchange = async () => {
      const file = input.files[0];
      const formData = new FormData();
      formData.append("file", file);

      try {
        const response = await axios.post("http://localhost:8090/shop-backend/notice/insertImage", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });

        const imageUrl = response.data;
        const editor = quillRef.current.getEditor();
        const cursorPosition = editor.getSelection().index;
        editor.insertEmbed(cursorPosition, "image", "http://localhost/noticeImage/"+imageUrl+".jpg");

      } catch (error) {
        if(error.response.status === 401){
          window.location.reload();

        }else if(error.response.status === 500){
          window.location.reload();
        }
      }
    };
  };


  const quillModules = React.useMemo(() => ({
    toolbar: {
      container: [
        [{ header: [1, 2, false] }],
        ['bold', 'italic', 'underline'],
        [{ 'color': [] }, { 'background': [] }],
        [{ 'list': 'ordered' }, { 'list': 'bullet' }],
        [{ 'align': [] }],
        ['link', 'image']
      ],
        // custom 핸들러 설정
      handlers: {
          image: imageHandler, // 이미지 tool 사용에 대한 핸들러 설정
      }
    },
  }), []);

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

  const changeContent = ()   =>{
    if (quillRef.current) {
      const editor = quillRef.current.getEditor();
      const content = editor.root.innerHTML;
      // 내용 추출 후 처리 로직 작성

      const formData = new FormData();
      formData.append("id", noticeId);
      formData.append("content", content);

      axios({
        method: "post",
        url: 'http://localhost:8090/shop-backend/notice/changeContent',
        data: formData,
        headers:{
          "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
        }
      })
      .then(function (response){
        //handle success
      })
      .catch(function(error){
        if(error.response.status === 401){
          window.location.reload();

        }else if(error.response.status === 500){
          window.location.reload();
        }
      });
    }
  }


  const changeTitle = (e)   =>{
    const formData = new FormData();
    formData.append("id", noticeId);
    formData.append("content", e.target.value);

    axios({
      method: "post",
      url: 'http://localhost:8090/shop-backend/notice/changeTitle',
      data: formData,
      headers:{
        "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
      }
    })
    .then(function (response){
      //handle success
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
        <div className="grid_t mb3">
          <div className="gr-4 calign">
            <h2> 제목 </h2>
          </div>
          <div className="gr-8">
            <input type="text" defaultValue={notice == undefined? "": notice.title} onChange={changeTitle}/>
          </div>
        </div>

        <ReactQuill
          ref={quillRef}
          value={notice == undefined? "":notice.content}
          modules={quillModules}
          onBlur={changeContent}
          onChange={changeContent}
          theme="snow"
        />


        <div className="calign pt3 grid_t">
          <div className="gr-3">
          </div>
          <div className="gr-2">
            <button className="bt_order" onClick={(e) => {navigate("../adminNoticeContent?noticeId="+noticeId)}}> 뒤로가기 </button>
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

export default AdminNoticeEdit;
