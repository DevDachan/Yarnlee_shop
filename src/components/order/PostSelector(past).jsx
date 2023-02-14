import React, { useState, useEffect, useRef } from "react";
import DaumPostcode from 'react-daum-postcode';



function PostSelector(props) {
  const [openPostcode, setOpenPostcode] = useState(false);
  
  const handle = {
      // 버튼 클릭 이벤트
      clickButton: () => {
          setOpenPostcode(current => !current);
      },

      // 주소 선택 이벤트
      selectAddress: (data: any) => {
          props.setAddress(data.address);
          props.setZonecode(data.zonecode);

          console.log(`
              주소: ${data.address},
              우편번호: ${data.zonecode}`)
          setOpenPostcode(false);
      },
  }

  return (
      <div>
       <button onClick={handle.clickButton}>Address</button>

       {openPostcode &&
           <DaumPostcode
               onComplete={handle.selectAddress}  // 값을 선택할 경우 실행되는 이벤트
               autoClose={false} // 값을 선택할 경우 사용되는 DOM을 제거하여 자동 닫힘 설정
            />}
      </div>
  );
}

export default PostSelector;
