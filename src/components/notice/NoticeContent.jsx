import React, { useState ,useEffect } from "react";
import { useNavigate,useLocation } from "react-router-dom";
import axios from "axios";
import queryString from 'query-string';
import styled from "styled-components";
const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 8em;
    margin:auto;
    min-width: 750px;
    min-height: 70vh;
`;

function NoticeContent(props) {
    const navigate = useNavigate();
    const location = useLocation();
    // 만약 List 정보가 없을시에는 Login으로 이동처리
    const { search } = useLocation();
    const {noticeId} = queryString.parse(search);

    const [notice, setNotice] = useState();

    useEffect(() => {
      if(noticeId == null){
        navigate("../noticeMain");
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
          navigate("../noticeMain");
        }
        setNotice(response.data);
      })
      .catch(function(error){
        //handle error
      });
    }, []);

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
          <div className="calign pt3">
            <button className="bt_order" onClick={(e) => {navigate("../noticeMain")}}> 목록으로 </button>
          </div>
        </div>
        </Wrapper>
    );
}

export default NoticeContent;
