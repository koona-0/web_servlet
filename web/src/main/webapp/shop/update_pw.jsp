<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String mid = (String)request.getAttribute("mid");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 재설정</title>
</head>
<body>

<form id="frm" method="post" action="./updatepw.do" onsubmit="return ck()">
<input type="hidden" name="mid" value="<%=mid%>">
<input type="password" name="newpw" placeholder="변경할 비밀번호 입력"><br>
<input type="password" name="newpw2" placeholder="변경할 비밀번호 재입력"><br>
<input type="submit" value="비밀번호 변경">
</form>

</body>
<script>
function ck(){
	if(frm.newpw.value != frm.newpw2.value){
		alert('비밀번호가 서로 다릅니다.');
		return false;
	}else{
		return true;
	}
	
}
</script>
</html>