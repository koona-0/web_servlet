var login = function(){
	if(frm.adm_id.value==""){
		alert("아이디를 입력하세요");
	}else if(frm.adm_pw.value==""){
		alert("패스워드를 입력하세요");
	}else if(frm.adm_no.value==""){
		alert("사원번호를 입력하세요");
	}else {
		//get통신 할때는 name말고 id로도 보낼 수 있음
		 
		//get 방식 1 : form으로 전송 
		//frm.method = "get";	//get으로 통신시 안써도됨
		//frm.action = "./adminok.do";
		//frm.enctype="";	//파일 전송시
		//frm.submit();
		
		
		//get 방식 2 : location 사용
		
		//btoa : 자바스크립트 유일 암호화 함수 base64 인코딩 
		//qwer => btoa() => cXdlcg== : 이런식으로 마지막에 =붙어있으면 base64
		var id = btoa(frm.adm_id.value);
		var pw = btoa(frm.adm_pw.value);
		var no = btoa(frm.adm_no.value);
		
		location.href="./adminok.do?admin_id="+id+"&adm_pw="+pw+"&adm_no="+no;
	}
}