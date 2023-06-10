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
    // 만약 List 정보가 없을시에는 Login으로 이동처리

    useEffect(() => {
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

    return (
        <Wrapper>
          <div className="noticeList">
            <table className="calign">
              <thead>
                <th className="calign" style={{width:"10%"}}>번호</th>
                <th className="calign" style={{width:"50%"}}>제목</th>
                <th className="calign" style={{width:"10%"}}>작성일</th>
                <th className="calign" style={{width:"10%"}}>조회수</th>
              </thead>
              <tbody>
              <tr>
                <td>1</td>
                <td><a href=""> 안녕하세요!</a></td>
                <td>2023.06.10</td>
                <td> 3</td>
              </tr>
              </tbody>
            </table>
          </div>
        </Wrapper>
    );
}

export default NoticeMain;
