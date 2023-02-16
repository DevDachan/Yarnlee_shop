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

function Login(props) {
    const navigate = useNavigate();
    const [id, setId] = useState();
    const [pwd, setPwd] = useState();


    const onClick = () =>{
      navigate(`/`);
    }
    const goRegister = () =>{
      navigate(`/register`);
    }


    return (
        <Wrapper>
          <div class="grid_t" style={{margin: "30px"}}>
            <div class="gr-3">
              <h3> ID </h3>
            </div>
            <div class="gr-9">
              <input type="text" id="ip_id" value={id}/>
            </div>
            <div class="gr-3">
              <h3> Password </h3>
            </div>
            <div class="gr-9">
              <input type="password" id="ip_pwd" value={pwd}/>
            </div>
            <div class="gr-6 ralign mt1 mr1">
              <button onClick={onClick}> 로그인 </button>
            </div>
            <div class="gr-6 lalign mt1 mr1">
              <button onClick={goRegister}> 회원 가입</button>
            </div>
          </div>
        </Wrapper>
    );
}

export default Login;
