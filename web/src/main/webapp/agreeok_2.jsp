<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String mid = (String)request.getAttribute("mid");
    String mname = (String)request.getAttribute("mname");
    String memail = (String)request.getAttribute("memail");
    String mtel = (String)request.getAttribute("mtel");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
</head>
<body>

<br>
회원가입이 완료 되었습니다.<br>
<br>
<br>
아이디 : <%=mid%> <br>
고객명 : <%=mname%><br>
이메일 : <%=memail%> <br>
휴대폰 번호 : <%=mtel%>

</body>
</html>