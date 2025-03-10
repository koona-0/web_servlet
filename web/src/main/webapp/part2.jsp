<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 입력</title>
</head>
<body>
<form id="frm" method="post" enctype="multipart/form-data" action="./part3.do">
<p>회원가입 정보 입력 [간편가입]</p>
아이디 <input type="text" name="mid"><br>
고객명 <input type="text" name="mname"><br>
비밀번호 <input type="password" name="mpass" maxlength="12">* 비밀번호는 6 ~ 12 자리입니다.<br>
이메일 <input type="text" name="memail"><br>
휴대폰번호 <input type="text" name="mtel" maxlength="11">* "-"는 입력하지 않습니다.<br>
이미지 <input type="file" name="mfile">* 이미지 첨부는 최대 2MB까지 가능합니다.<br>
<button type="button" onclick="gopage()">회원가입</button> 
</form>
</body>
<script>
var gopage = function(){
	if(frm.mid.value==""){
		alert("아이디를 입력하세요!");
		frm.mid.focus();	
	}else if(frm.mname.value==""){
		alert("고객명을 입력하세요!");
		frm.mname.focus();	
	}else if(frm.mpass.value==""){
		alert("비밀번호를 입력하세요!");
		frm.mpass.focus();	
	}else if(frm.mpass.value.length < 6){	//비밀번호 길이 확인 
		alert("패스워드는 최소 6자 입력해주세요!");
		frm.mpass.focus();	
	}else if(frm.memail.value==""){
		alert("이메일을 입력하세요!");
		frm.memail.focus();	
	}else if(frm.mtel.value==""){
		alert("휴대폰번호를 입력하세요!");
		frm.mtel.focus();	
	}else{	//alert에 확인 취소 => confirm
		if(confirm("회원가입을 진행하시겠습니까?")){	//확인 
			frm.submit();			
		}else{	//취소 
			history.go(-1);
		}
	}
}

</script>
</html>