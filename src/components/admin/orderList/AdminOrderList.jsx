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

function AdminOrderList(props) {
    const navigate = useNavigate();
    const location = useLocation();
    // 만약 List 정보가 없을시에는 Login으로 이동처리
    const [orderList, setOrderList] = useState();
    const [productList, setProductList] = useState();

    useEffect(() => {
      if(sessionStorage.getItem("admin") == null || sessionStorage.getItem("admin") == undefined){
        navigate('../adminLogin');
      }else{
        axios({
          method: "get",
          url: 'http://localhost:8090/shop-backend/order/getAdminOrderList',
          params:{
            hashKey: sessionStorage.getItem("adminHash"),
            id: sessionStorage.getItem("admin")
          }
        })
        .then(function (response){
          //handle success
          if(response.data == ""){
            //navigate('../adminLogin');
          }else{
            setOrderList(response.data.orderList);
            setProductList(response.data.productList);
          }

        })
        .catch(function(error){
          //handle error
        })
        .then(function(){
          // always executed
        });
      }

    }, []);

    const viewOrder = (e) =>{
      var id= e.target.id;

      navigate("../adminOrderHistory", {
      state: {
        orderId: orderList[id].id
      }
      });
    }

    function makeList(){
      var arr = [];
      for(var i = 0; i < orderList.length; i++){
        arr.push(
          <tr>
            <td>{orderList[i].orderDate}</td>
            <td>{orderList[i].id}</td>
            <td>{productList[i].name}</td>
            <td>{orderList[i].totalCost}원 </td>
            <td>{orderList[i].orderName}</td>
            <td>{orderList[i].state}</td>
            <td>
              <button onClick={viewOrder} id={i}>
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
                <th>금액</th>
                <th>주문자</th>
                <th>주문 상태</th>
                <th>주문서</th>
              </thead>
              <tbody>
                {orderList == null ? "" :makeList()}
              </tbody>
            </table>
          </div>
        </Wrapper>
    );
}

export default AdminOrderList;
