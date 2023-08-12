import React, { useState ,useEffect } from "react";
import { useNavigate,useLocation } from "react-router-dom";
import axios from "axios";
import styled from "styled-components";
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

export default AdminOrderList;
