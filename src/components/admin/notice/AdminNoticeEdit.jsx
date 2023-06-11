import React, { useState ,useEffect, useRef } from "react";
import { useNavigate,useLocation } from "react-router-dom";
import axios from "axios";
import styled from "styled-components";
import queryString from 'query-string';

import ReactQuill from 'react-quill'
import 'react-quill/dist/quill.snow.css' // Quill 에디터 스타일시트
import ImageUploader from 'quill-image-uploader'
import Quill from 'quill';


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

  useEffect(() => {
    if(sessionStorage.getItem("admin") == null || sessionStorage.getItem("admin") == undefined){
      navigate('../adminLogin');
    }

    axios({
      method: "post",
      url: 'http://localhost:8090/shop-backend/order/getOrderHistory'
    })
    .then(function (response){
      //handle success
    })
    .catch(function(error){
      //handle error
    })
    .then(function(){
      // always executed
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
        editor.insertEmbed(cursorPosition, "image", "../noticeImage/"+imageUrl+".jpg");

      } catch (error) {
        console.log(error);
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

  const changeNotice = ()   =>{
    if (quillRef.current) {
      const editor = quillRef.current.getEditor();
      const content = editor.root.innerHTML;
      // 내용 추출 후 처리 로직 작성

      const formData = new FormData();
      formData.append("content", content);

      axios({
        method: "post",
        url: 'http://localhost:8090/shop-backend/product/',
        data: formData
      })
      .then(function (response){
        //handle success
      })
      .catch(function(error){
        //handle error
        console.log(error);
      })
      .then(function(){
        // always executed
      });
    }
  }

  return (
    <Wrapper>
      <div className="noticeList">
        <ReactQuill
          ref={quillRef}
          value={''}
          modules={quillModules}
          onBlur={changeNotice}
          theme="snow"
        />


        <div className="calign pt3 grid_t">
          <div className="gr-3">
          </div>
          <div className="gr-2">
            <button className="bt_order" onClick={(e) => {navigate("../noticeMain")}}> 목록으로 </button>
          </div>
          <div className="gr-2">
            <button className="bt_order" onClick={(e) => {navigate("../noticeMain")}}> 수정하기 </button>
          </div>
          <div className="gr-2">
            <button className="bt_order" onClick={(e) => {navigate("../noticeMain")}}> 삭제하기 </button>
          </div>
          <div className="gr-3">
          </div>
        </div>
      </div>
    </Wrapper>
  );
}

export default AdminNoticeEdit;
