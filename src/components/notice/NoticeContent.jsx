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

function NoticeContent(props) {
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
          <table>
            <tbody>
            <tr>
              <td className="lalign pl3" style={{width: "160px"}}>제목</td>
              <td className="lalign font400" >안녕하세요!</td>
            </tr>
            <tr>
              <td className="lalign pl3">작성 일</td>
              <td className="lalign font400">2023.06.10</td>
            </tr>
            <tr>
              <td colSpan="2" style={{backgroundColor: "white"}}>
                <div className="noticeContent-content">
                본문본문
                본문
                본문
                본문
                본문
                본문
                본문
                본문
                본문
                본문
                본문<br />
                setAddressDetail
                </div>
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
