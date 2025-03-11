function ajaxs(){	//전송 버튼 클릭시 이벤트 함수 
	var mid = document.getElementById("mid");
	var memail = document.getElementById("memail");
	if(mid.value == ""){
		alert("아이디를 입력하세요!");
	}else if(memail.value == ""){
		alert("이메일을 입력하세요!");
	}else {
		this.ajax_post(mid.value, memail.value);	//ajax post 통신을 위한 함수 
	}
}

//Ajax POST 전송하는 함수 (실무용! 짧은코드! 조음!)
function ajax_post(mid, memail){
	var http, result;	//http : Back-end 통신용 , result : Back-end 제공한 데이터 
	http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		if(http.readyState == 4 && http.status == 200){	//4 == DONE (url유효) || 200 == 정상통신
			console.log(this.response);
		}else if(http.status == 404){
			console.log("경로 오류 발생");
		}else if(http.status == 405){
			console.log("통신 규격 오류 발생");
		}
	} 
	//post 통신 
	http.open("post","./ajax_postok.do",true);
	//⭐️ajax post 전송시 content-type 적용하여 urlencoded 적용해야만 정상적으로 Back-end에게 값을 전송함 
	http.setRequestHeader("content-type", "application/x-www-form-urlencoded");	//form enctype 쓰고 컨스페하면 나오는거
	//post는 값 여러개 보낼때 &를 이용해서 함께 보냄 
	http.send("userid="+mid+"&usermail="+memail);
	//값 하나 보낼때
	//http.send("userid="+mid);
	
	/* 
	//get 방식에서는 주소 뒤에 붙였었음
	http.open("get","./ajax_postok.do?userid="+mid,true);
	http.send();
	*/
	 
}