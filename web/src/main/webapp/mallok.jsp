<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String final_amount = (String) request.getAttribute("final_amount");
String payment = (String) request.getAttribute("payment");
%>
<html>
<head>
<meta charset="UTF-8">
<title>결제 진짜 완료</title>
</head>
<body>
[<%=payment%>] <%=final_amount%>원 결제완료되었습니다.
</body>
</html>