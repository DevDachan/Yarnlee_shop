import React from "react";


function Footer(props){

  return (
  <footer id="footer">
    <div class="inner">
      <section>
        <h2>Get in touch</h2>
        <form method="post" action="#">
          <div class="fields">
            <div class="field half">
              <input type="text" name="name" id="name" placeholder="Name" />
            </div>
            <div class="field half">
              <input type="email" name="email" id="email" placeholder="Email" />
            </div>
            <div class="field">
              <textarea name="message" id="message" placeholder="Message"></textarea>
            </div>
          </div>
          <ul class="actions">
            <li><input type="submit" value="Send" class="primary" /></li>
          </ul>
        </form>
      </section>
      <section>
        <h2>Follow</h2>
        <ul class="icons">
          <li><a href="https://instagram.com/yarnlee__?igshid=YmMyMTA2M2Y=" class="icon brands style2 fa-instagram"><span class="label">Instagram</span></a></li>
          <li><a href="tel:010-6818-9524" class="icon solid style2 fa-phone"><span class="label">Phone</span></a></li>
          <li><a href="mailto:chn7894@handong.ac.kr" class="icon solid style2 fa-envelope"><span class="label">Email</span></a></li>
        </ul>
      </section>
      <ul class="copyright">
        <li>&copy; Untitled. All rights reserved</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
      </ul>
    </div>
  </footer>
  );

}
export default Footer;
