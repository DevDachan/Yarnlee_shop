import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-bottom: 8em;
`;

function OrderList(props) {
    const navigate = useNavigate();

    const viewOrder = () =>{
      navigate("../orderHistory")
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
                <tr>
                  <td>2023.01.02</td>
                  <td>2323131</td>
                  <td>토트서클백 그린 1개 <br/> 토트서클백 그린 1개</td>
                  <td>다찬</td>
                  <td>010-0000-0000</td>
                  <td>
                    <button onClick={viewOrder}>
                      View
                    </button>
                  </td>
                </tr>
                <tr>
                  <td></td>
                  <td>2323131</td>
                  <td>토트서클백 그린 1개 <br/> 토트서클백 그린 1개</td>
                  <td>다찬</td>
                  <td>010-0000-0000</td>
                  <td>
                    <button onClick={viewOrder}>
                      View
                    </button>
                  </td>
                </tr>
                <tr>
                  <td></td>
                  <td>2323131</td>
                  <td>토트서클백 그린 1개 <br/> 토트서클백 그린 1개</td>
                  <td>다찬</td>
                  <td>010-0000-0000</td>
                  <td>
                    <button onClick={viewOrder}>
                      View
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </Wrapper>
    );
}

export default OrderList;
