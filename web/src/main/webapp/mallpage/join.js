function joinok(){
	if(frm.mid.value == ""){
		alert("아이디를 입력하세요");
	}else if(frm.mpass.value == ""){
		alert("비밀번호를 입력하세요");
	}else if(frm.mname.value == ""){
		alert("이름을 입력하세요");
	}else if(frm.memail.value == ""){
		alert("이메일을 입력하세요");
	}else if(frm.mtel.value == ""){
		alert("전화번호를 입력하세요");
	}else {
		var pw2 = document.getElementById("mpass2").value;
		if(pw2 != frm.mpass.value){
			alert("동일한 비밀번호를 입력하세요");
		}else{
			frm.submit();
		}
	}
}