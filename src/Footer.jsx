import React from "react";


function Footer(props){

  return (
  <footer id="footer">
    <div className="inner">
      <section>
        <h2>Follow</h2>
        <ul className="icons">
          <li><a href="https://instagram.com/yarnlee__?igshid=YmMyMTA2M2Y=" target="_blank" className="icon brands style2 icon-instagram"><span className="label">Instagram</span></a></li>
          <li><a href="http://pf.kakao.com/_ZxdEvxj" target="_blank" className="icon solid style2 icon-kakaotalk"><span className="label">KakapTalk</span></a></li>
        </ul>
      </section>
      <ul className="copyright">
        <li>&copy; Untitled. All rights reserved</li><li>Design: <a href="http://html5up.net" target="_blank">HTML5 UP</a></li>
      </ul>
    </div>
  </footer>
  );

}

/*
<section>
  <h2>Get in touch</h2>
  <form method="post" action="#">
    <div className="fields">
      <div className="field half">
        <input type="text" name="name" id="name" placeholder="Name" />
      </div>
      <div className="field half">
        <input type="email" name="email" id="email" placeholder="Email" />
      </div>
      <div className="field">
        <textarea name="message" id="message" placeholder="Message"></textarea>
      </div>
    </div>
    <ul className="actions send">
      <li className="send"><input type="submit" className="send" value="Send" className="primary" /></li>
    </ul>
  </form>
</section>

*/
export default Footer;
