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
    min-height: 70vh;
`;

function OrderList(props) {
    const navigate = useNavigate();
    const location = useLocation();
    // 만약 List 정보가 없을시에는 Login으로 이동처리
    const [orderList, setOrderList] = useState();
    const [productList, setProductList] = useState();


    useEffect(() => {
      if(location.state == null && (sessionStorage.getItem("jwt-auth-token") == null || sessionStorage.getItem("jwt-auth-token") == undefined)){
        navigate('../orderLogin');
      }else if(location.state != null){
        setOrderList(location.state.list.orderList);
        setProductList(location.state.list.productList);
      }else{

        const formData = new FormData();
        formData.append("type", "사용자 인증");
        formData.append("name", sessionStorage.getItem("id"));
        formData.append("content", "");

        axios({
          method: "post",
          url: 'http://104.198.11.59:8090/shop-backend/order/getOrderHistory',
          headers:{
            "jwt-auth-token": sessionStorage.getItem("jwt-auth-token")
          },
          data: formData
        })
        .then(function (response){
          //handle success
          setOrderList(response.data);
          setProductList(response.data);
        })
        .catch(function(error){
          //handle error
          sessionStorage.clear();
          navigate("../login");
        });
      }


    }, [location, navigate]);

    if (location.state == null && sessionStorage.getItem("phoneNum") == null && sessionStorage.getItem("orderNum") == null ) {
       return null; // navigate 호출 후 컴포넌트의 렌더링을 중단
    }

    const viewOrder = (e) =>{
      var id= e.target.id;

      navigate("../orderHistory", {
      state: {
        orderDetail: orderList[id],
        productDetail: productList[id]

      }
      });
    }

    function makeList(){
      var arr = [];
      for(var i = 0; i < orderList.length; i++){
        if(orderList[i].state == "발송 완료"){
          arr.push(
            <tr>
              <td className="order-end">{orderList[i].orderDate}</td>
              <td className="order-end">{orderList[i].id}</td>
              <td className="order-end">{productList[i].name}</td>
              <td className="order-end">{orderList[i].totalCost}원 </td>
              <td className="order-end">{orderList[i].orderName}</td>
              <td className="order-end">{orderList[i].state}</td>
              <td>
                <button onClick={viewOrder} id={i}>
                  View
                </button>
              </td>
            </tr>
          );
        }else{
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
      }
      return arr;
    }

    return (
        <Wrapper>
          <div className="orderList">
            <div className="grid_t" style={{padding: "2rem"}}>
              <div className="gr-12">
                <h2> 주문 과정</h2>
              </div>
              <div className="gr-2">
                <h3> 주문 완료</h3>
              </div>
              <div className="gr-1">
                <h3> > </h3>
              </div>
              <div className="gr-2">
                <h3> 결재 완료</h3>
              </div>
              <div className="gr-1">
                <h3> > </h3>
              </div>
              <div className="gr-2">
                <h3> 제작 중</h3>
              </div>
              <div className="gr-1">
                <h3> > </h3>
              </div>
              <div className="gr-2">
                <h3> 발송 완료</h3>
              </div>
            </div>
          </div>
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

export default OrderList;
