function wordck(){
	//var w = "010123-45678";
	
	//범위 
	//let ck = /[0-9]/;		//0~9 하나라도 있으면 true
	//let ck = /[a-zA-Z]/;	//a~z, A~Z 하나라도 있으면 true
	//console.log(ck.test(w));	
	//test() : true, false
	
	//^는 not의 역할
	//let ck = /^[0-9]/;		//0~9 말고 하나라도 있으면 true  
	
	//let ck = /[0-9]/;
	//console.log(w.match(ck));	
	//match() : 배열형태의 값 출력
	
	//첫글자만 검토함
	//let ck = /[0-9]/;
	//let ck = /^[0-9]/;
	
	//g : 그룹 => 전체를 확인 
	//let ck = /^[0-9]/g;
	
	//010-1234-5678 => true, 01012345678 => false 
	//var w = "010-1234-5678";
	//let ck = /^\d{2,3}-\d{3,4}-\d{4}$/;
	
	//01012345678 => true, 010-1234-5678 => false 
	var w = "01012345678";
	let ck = /^\d{2,3}\d{3,4}\d{4}$/;
	//$ : 해당 패턴에 문자열을 대입하여 체크하는 방식
	//d : 숫자  
	
	console.log(ck.test(w));
	//match, test알아두기 근데 test만 알아도 웬만한건 다 하긴하는데 match도 알아두기 
}


function eventok(){
	if(f.ename.value==""){
		alert("고객명을 입력하세요!");
	}else if(f.etel.value==""){
		alert("연락처를 입력하세요");
	}else if(f.email.value==""){
		alert("이메일을 입력하세요");
	}else if(f.ememo.value==""){
		alert("이벤트 참여 이유를 입력하세요");
	}else if(f.info1.checked == false){
		alert("개인정보활용에 동의하셔야 이벤트참여가 가능합니다");
	}else if(f.info2.checked == false){
		alert("제 3자의 정보제공에 동의하셔야 이벤트참여가 가능합니다");
	}else{
		let ck = /^\d{2,3}\d{3,4}\d{4}$/;	//슷자 외에 다른 문자,길이 9~11이 아닐 경우 false
		if(ck.test(f.etel.value)==false){
			alert("연락처를 정상적으로 입력하세요");
		}else{
			f.submit();		
		}		
	}
}