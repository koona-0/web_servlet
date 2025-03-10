<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<String> data = (ArrayList)request.getAttribute("data");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰 등록 완료 페이지</title>
</head>
<body>

<p>쿠폰 등록 페이지</p>
아이디 : <%=data.get(0)%><br>
쿠폰 번호 : <%=data.get(1)%><br>
<%
if(data.get(2).equals("on")){
	%>
	광고 수신 동의<br>
	<%
}else{
	%>
	<script>
	alert('올바르지않은 접근입니다.');
	location.href='./coupon.html';
	</script>
	<%
}
%>

</body>
</html>