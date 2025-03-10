<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인 페이지</title>
</head>
<body>

<!-- 
외부 페이지를 가져오는 두가지 방법
<jsp:include page=""></jsp:include>
<%-- 
<%@ include file=""%>
--%>
-->

<header>
<jsp:include page="./top.jsp" flush="true"></jsp:include>
</header>

<section>
<%@ include file="./product.jsp"%>
</section>
<footer>
<jsp:include page="./copyright.jsp"></jsp:include>
</footer>

</body>
</html>