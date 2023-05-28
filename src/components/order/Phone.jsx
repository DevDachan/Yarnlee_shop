import React, { useState, useRef } from 'react';

function Phone(props) {

  const phoneRef = useRef();
  const phoneNum = props.phoneNum;
  const setPhoneNum = props.setPhoneNum;

  const onBlur = props.onBlur;

  // 휴대폰 번호 입력 함수
  const handlePhone = (e) => {
    // replace D: not Digit, g: 전 영역
    const value = phoneRef.current.value.replace(/\D+/g, "");
    const numberLength = 11;

    const onBlur = () => {}

    let result;
    result = "";

    //for문은 각각의 string index를 확인하면서 3번째, 7번째 글자가 나올 때 -을 추가해준다.
    for (let i = 0; i < value.length && i < numberLength; i++) {
      switch (i) {
        case 3:
          result += "-";
          break;
        case 7:
          result += "-";
          break;
        default:
          break;
      }
      result += value[i];
    }

    phoneRef.current.value = result;

    setPhoneNum(e.target.value);
  };

  return (
    <input
      id="phoneNum"
      name="user-num"
      className="prl1"
      value={phoneNum}
      ref={phoneRef}
      onChange={handlePhone}
      type="tel"
      onBlur={props.onBlur ? props.onBlur:onBlur}
      required
    />
  );
};

export default Phone;
