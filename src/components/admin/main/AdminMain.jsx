import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

import AdminItemList from "./list/AdminItemList";
// 나중에 현재 전체 목록 itemList로 변경하기.

function AdminMain(props) {
  const navigate = useNavigate();
  const Wrapper = styled.div`
      padding: 0 2.5em;
      margin: 0 auto;
      width: calc(100% - 32px);
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
  `;
  return (
      <Wrapper>
          <div id="main">
            <div className="inner mg0">
              <header>
                <textarea placeholder="메인 화면에서 나타날 내용 입력" defaultValue="<h1>안녕하십니까!!<br />
                예제 쇼핑몰 페이지입니다!</h1>">
                </textarea>
              </header>
              <section className="tiles mt2">
                <article className="style1">
                  <span className="image">
                    <img src="images/pic01.jpg" alt="" />
                  </span>
                  <a>
                    <input type="text" defaultValue="Magna" />
                    <div className="content">
                      <input type="text" defaultValue="Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat" />
                    </div>
                  </a>
                </article>

                <article className="style2">
                  <span className="image">
                    <img src="images/pic02.jpg" alt="" />
                  </span>
                  <a>
                    <input type="text" defaultValue="Magna" />
                    <div className="content">
                      <input type="text" defaultValue="Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat" />
                    </div>
                  </a>
                </article>


                <article className="style3">
                  <span className="image">
                    <img src="images/pic03.jpg" alt="" />
                  </span>
                  <a>
                    <input type="text" defaultValue="Magna" />
                    <div className="content">
                      <input type="text" defaultValue="Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat" />
                    </div>
                  </a>
                </article>

                <article className="style4">
                  <span className="image">
                    <img src="images/pic04.jpg" alt="" />
                  </span>
                  <a>
                    <input type="text" defaultValue="Magna" />
                    <div className="content">
                      <input type="text" defaultValue="Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat" />
                    </div>
                  </a>
                </article>

                <article className="style5">
                  <span className="image">
                    <img src="images/pic05.jpg" alt="" />
                  </span>
                  <a>
                    <input type="text" defaultValue="Magna" />
                    <div className="content">
                      <input type="text" defaultValue="Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat" />
                    </div>
                  </a>
                </article>

                <article className="style6">
                  <span className="image">
                    <img src="images/pic06.jpg" alt="" />
                  </span>
                  <a>
                    <input type="text" defaultValue="Magna" />
                    <div className="content">
                      <input type="text" defaultValue="Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat" />
                    </div>
                  </a>
                </article>



              </section>
            </div>
          </div>


          <h2>순서 바꾸기</h2>

          <div className="grid_t mb2">
            <div className="gr-9">
              <p>Magna </p>
            </div>
            <div className="gr-3">
              <select>
                <option value="sd">1</option>
                <option value="ad">2</option>
                <option value="qd">3</option>
              </select>
            </div>

            <div className="gr-9">
              <p>Lorem </p>
            </div>
            <div className="gr-3">
              <select>
                <option value="sd">1</option>
                <option value="ad">2</option>
                <option value="qd">3</option>
              </select>
            </div>

            <div className="gr-9">
              <p>Feugiat </p>
            </div>
            <div className="gr-3">
              <select>
                <option value="sd">1</option>
                <option value="ad">2</option>
                <option value="qd">3</option>
              </select>
            </div>

            <div className="gr-9">
              <p>Magna </p>
            </div>
            <div className="gr-3">
              <select>
                <option value="sd">1</option>
                <option value="ad">2</option>
                <option value="qd">3</option>
              </select>
            </div>
          </div>

      </Wrapper>
  );
}

export default AdminMain;
