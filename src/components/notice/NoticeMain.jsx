import React, { useState ,useEffect } from "react";
import { useNavigate,useLocation } from "react-router-dom";
import axios from "axios";
import styled from "styled-components";
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

function NoticeMain(props) {
    const navigate = useNavigate();
    const location = useLocation();
    const [noticeList, setNoticeList] = useState();

    useEffect(() => {
      axios({
        method: "get",
        url: 'http://localhost:8090/shop-backend/notice/getNoticeList'
      })
      .then(function (response){
        setNoticeList(response.data);
        //handle success
      })
      .catch(function(error){
        //handle error
      })
      .then(function(){
        // always executed
      });
    }, []);

    function makeNotice(){
      var arr = [];
      for(var i = 0; i < noticeList.length; i++){
        arr.push(
          <tr>
            <td>{noticeList[i].id}</td>
            <td><a href={"../noticeContent?noticeId="+noticeList[i].id}> {noticeList[i].title}</a></td>
            <td>{noticeList[i].createTime}</td>
            <td> {noticeList[i].hits}</td>
          </tr>
        );
      }
      return arr;
    }
    return (
        <Wrapper>
          <div className="noticeList">
            <table className="calign">
              <thead>
                <th className="calign" style={{width:"10%"}}>번호</th>
                <th className="calign" style={{width:"50%"}}>제목</th>
                <th className="calign" style={{width:"20%"}}>작성일</th>
                <th className="calign" style={{width:"10%"}}>조회수</th>
              </thead>
              <tbody>
              {noticeList == undefined? "" : makeNotice()}
              </tbody>
            </table>
          </div>
        </Wrapper>
    );
}

export default NoticeMain;
