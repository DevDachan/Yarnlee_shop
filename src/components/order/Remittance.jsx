import React, { useState, useRef } from 'react';

function Remittance(props) {

  const remittanceImage = props.remittanceImage;
  const setImageId = props.setImageId;

  const selectImage = (e) => {
    const file = e.target.files[0];
    const newImage = document.getElementById("img");
    const render = new FileReader();

    render.readAsDataURL(file);
    render.onload = () => {
      newImage.src = render.result;
      newImage.style.width = "150px";
      newImage.style.height = "150px";
      newImage.style.visibility  = "visible";
      newImage.style.objectFit = "contain";
      remittanceImage.value = render.result;
    }
    setImageId(1);
  }

  return (
    <div className="grid_t calign" id="image_container">
      <div className="gr-4 pt1">
        <h2>송금 내역</h2>
      </div>

      <div className="gr-3">
        <label id="lb_image" htmlFor="image_remittance">Upload</label>
      </div>

      <div className="gr-5">
        <img id="img" src='' />
      </div>

      <input
        type="file"
        id="image_remittance"
        name="userfile"
        accept="image/*"
        required
        style={{visibility: "hidden"}}
        ref={props.remittanceImage}
        onChange={selectImage}
      />
    </div>
  );
}

export default Remittance;
