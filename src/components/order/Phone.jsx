import React, { useState, useRef } from 'react';

function Phone() {
  // State와 Ref를 2개 사용하는 이유는 먼저 해당 값이 수정이 될 수 있게 할 것인가 때문이다.
  // Ref는 수정이 될 수 있는 값
  // State는 직접 수정이 될 수 없고 setNum으로 수정이 가능하다.

  const [phoneNum, setPhoneNum] = useState('');
  const phoneRef = useRef();

  // 휴대폰 번호 입력 함수
  const handlePhone = (e) => {
    // replace D: not Digit, g: 전 영역
    const value = phoneRef.current.value.replace(/\D+/g, "");
    const numberLength = 11;

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
        />

  );
};

export default Phone;
