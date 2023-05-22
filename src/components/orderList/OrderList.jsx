import React, { useState ,useEffect } from "react";
import { useNavigate,useLocation } from "react-router-dom";
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
`;

function OrderList(props) {
    const navigate = useNavigate();
    const location = useLocation();
    const orderList = location.state.list;

    const viewOrder = () =>{
      navigate("../orderHistory")
    }

    function makeList(){
      var arr = [];
      for(var i = 0; i < orderList.length; i++){
        arr.push(
          <tr>
            <td>{orderList[i].orderDate}</td>
            <td>{orderList[i].id}</td>
            <td>{orderList[i].productId} 금액: {orderList[i].totalCost} </td>
            <td>{orderList[i].orderName}</td>
            <td>{orderList[i].orderPhone}</td>
            <td>
              <button onClick={viewOrder}>
                View
              </button>
            </td>
          </tr>
        );
      }
      return arr;
    }

    return (
        <Wrapper>
          <div className="orderList">
            <table>
              <thead>
                <th>주문 일자</th>
                <th>주문 번호</th>
                <th>상품 명</th>
                <th>주문자</th>
                <th>전화번호</th>
                <th>주문서</th>
              </thead>
              <tbody>
                {makeList()}
              </tbody>
            </table>
          </div>
        </Wrapper>
    );
}

export default OrderList;
