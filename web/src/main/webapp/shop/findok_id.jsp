<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String mid = (String)request.getAttribute("mid");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾음</title>
</head>
<body>
<p>아이디 찾기</p>
아이디 : <%=mid %><br>
<input type="button" value="비밀번호 찾기" onclick="find_pw()">
</body>

<script>
function find_pw(){
	location.href="./find_pw.jsp";
}
</script>

</html>