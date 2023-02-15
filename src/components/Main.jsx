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
            <div class="inner mg0">
              <header>
                <h1>안녕하십니까!!<br />
                예제 쇼핑몰 페이지입니다!</h1>
              </header>
              <section class="tiles">
                <article class="style1">
                  <span class="image">
                    <img src="images/pic01.jpg" alt="" />
                  </span>
                  <a href="Item">
                    <h2>Magna</h2>
                    <div class="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article class="style2">
                  <span class="image">
                    <img src="images/pic02.jpg" alt="" />
                  </span>
                  <a href="Item">
                    <h2>Lorem</h2>
                    <div class="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article class="style3">
                  <span class="image">
                    <img src="images/pic03.jpg" alt="" />
                  </span>
                  <a href="Item">
                    <h2>Feugiat</h2>
                    <div class="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article class="style4">
                  <span class="image">
                    <img src="images/pic04.jpg" alt="" />
                  </span>
                  <a href="Item">
                    <h2>Tempus</h2>
                    <div class="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article class="style5">
                  <span class="image">
                    <img src="images/pic05.jpg" alt="" />
                  </span>
                  <a href="Item">
                    <h2>Aliquam</h2>
                    <div class="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article class="style6">
                  <span class="image">
                    <img src="images/pic06.jpg" alt="" />
                  </span>
                  <a href="Item">
                    <h2>Veroeros</h2>
                    <div class="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article class="style2">
                  <span class="image">
                    <img src="images/pic07.jpg" alt="" />
                  </span>
                  <a href="Item">
                    <h2>Ipsum</h2>
                    <div class="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article class="style3">
                  <span class="image">
                    <img src="images/pic08.jpg" alt="" />
                  </span>
                  <a href="Item">
                    <h2>Dolor</h2>
                    <div class="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article class="style1">
                  <span class="image">
                    <img src="images/pic09.jpg" alt="" />
                  </span>
                  <a href="Item">
                    <h2>Nullam</h2>
                    <div class="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article class="style5">
                  <span class="image">
                    <img src="images/pic10.jpg" alt="" />
                  </span>
                  <a href="Item">
                    <h2>Ultricies</h2>
                    <div class="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article class="style6">
                  <span class="image">
                    <img src="images/pic11.jpg" alt="" />
                  </span>
                  <a href="Item">
                    <h2>Dictum</h2>
                    <div class="content">
                      <p>Sed nisl arcu euismod sit amet nisi lorem etiam dolor veroeros et feugiat.</p>
                    </div>
                  </a>
                </article>
                <article class="style4">
                  <span class="image">
                    <img src="images/pic12.jpg" alt="" />
                  </span>
                  <a href="Item">
                    <h2>Pretium</h2>
                    <div class="content">
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
