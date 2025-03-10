<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
   <%
    String count = request.getAttribute("count").toString();
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 정보 입력 [간편가입]</title>
</head>
<body>
<form id="join_form" method="post" enctype="multipart/form-data" action="http://172.30.1.44:8080/web/agreeok_2.do">
<!-- 
<form id="join_form" method="post" action="./agreeok_2.do">
 -->
아이디 <input type="text" name="mid" required><br>
고객명 <input type="text" name="mname" required><br>
비밀번호 <input type="password" name="mpass" required><br>
이메일 <input type="text" name="memail" required><br>
휴대폰번호 <input type="tel" name="mtel" pattern="(010|011)\d{3,4}\d{3,4}" maxlength="11" required><br>
이미지 <input type="file" name="mfile"><br>
<input type="submit">회원가입
<!-- 
<button type="button" onclick="join_btn()">회원가입</button> 
-->
</form>

</body>
<script>
/*
function join_btn(){
	if(join_form.mid.value==""){
		alert("아이디를 입력하세요!");
		join_form.mid.focus();	
	}
	else if(join_form.mname.value==""){
		alert("고객명을 입력하세요!");
		join_form.mname.focus();	
	}  
	else if(join_form.mpass.value==""){
		alert("비밀번호를 입력하세요!");
		join_form.mpass.focus();	
	} 
	else if(join_form.memail.value==""){
		alert("이메일을 입력하세요!");
		join_form.memail.focus();	
	} 
	else if(join_form.mtel.value==""){
		alert("휴대폰번호를 입력하세요!");
		join_form.mtel.focus();	
	} 
	else{
		join_form.submit();	
	}
}
*/
</script>

</html>
<!--
주은
http://172.30.1.34:8080/web/agreeok.html
나영
http://172.30.1.92:8080/web/agreeok.jsp
하빈  
http://172.30.1.44:8080/web/agreeok_2.jsp
 -->