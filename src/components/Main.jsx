import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

import ItemList from "./list/ItemList";
// 나중에 현재 전체 목록 itemList로 변경하기.

function Main(props) {
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
                <h1>안녕하십니까!!</h1>
                <h1>예제 쇼핑몰 페이지입니다!</h1>
              </header>
              <section className="tiles">
                <article className="style1">
                  <span className="image">
                    <img src="images/pic01.jpg" alt="" />
                  </span>
                  <a href="content">
                    <h2>Magna</h2>
                    <div className="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article className="style2">
                  <span className="image">
                    <img src="images/pic02.jpg" alt="" />
                  </span>
                  <a href="content">
                    <h2>Lorem</h2>
                    <div className="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article className="style3">
                  <span className="image">
                    <img src="images/pic03.jpg" alt="" />
                  </span>
                  <a href="content">
                    <h2>Feugiat</h2>
                    <div className="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article className="style4">
                  <span className="image">
                    <img src="images/pic04.jpg" alt="" />
                  </span>
                  <a href="content">
                    <h2>Tempus</h2>
                    <div className="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article className="style5">
                  <span className="image">
                    <img src="images/pic05.jpg" alt="" />
                  </span>
                  <a href="content">
                    <h2>Aliquam</h2>
                    <div className="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article className="style6">
                  <span className="image">
                    <img src="images/pic06.jpg" alt="" />
                  </span>
                  <a href="content">
                    <h2>Veroeros</h2>
                    <div className="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article className="style2">
                  <span className="image">
                    <img src="images/pic07.jpg" alt="" />
                  </span>
                  <a href="content">
                    <h2>Ipsum</h2>
                    <div className="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article className="style3">
                  <span className="image">
                    <img src="images/pic08.jpg" alt="" />
                  </span>
                  <a href="content">
                    <h2>Dolor</h2>
                    <div className="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article className="style1">
                  <span className="image">
                    <img src="images/pic09.jpg" alt="" />
                  </span>
                  <a href="content">
                    <h2>Nullam</h2>
                    <div className="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article className="style5">
                  <span className="image">
                    <img src="images/pic10.jpg" alt="" />
                  </span>
                  <a href="content">
                    <h2>Ultricies</h2>
                    <div className="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article className="style6">
                  <span className="image">
                    <img src="images/pic11.jpg" alt="" />
                  </span>
                  <a href="content">
                    <h2>Dictum</h2>
                    <div className="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article className="style4">
                  <span className="image">
                    <img src="images/pic12.jpg" alt="" />
                  </span>
                  <a href="content">
                    <h2>Pretium</h2>
                    <div className="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
              </section>
            </div>
          </div>
      </Wrapper>
  );
}

export default Main;
