<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //getAttribute : Controller에서 setAttribute로 생성된 값을 이관받음
    int product_m = (int)request.getAttribute("product_m");
    int product_s = (int)request.getAttribute("product_s");
    int product_p = (int)request.getAttribute("product_p");
    int total = (int)request.getAttribute("total");	//getAttribute는 문자형이기 때문에 int로 받을거면 변환해줘야함 
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제 진행 사항</title>
</head>
<body>
<%-- : jsp 주석 
out.print : <%=%> 
--%>
상품금액 : <% out.print(product_m); %><br>
할인율 : <%=product_s%>%<br>
적립금 : <%=product_p%><br>
최종 결제 금액 : <%=total%><br>
<input type="button" value="결제하기"> 
</body>
</html>

<%-- 
[프로젝트 만드는 순서]
1. HTML
2. CSS
3. Javascript
4. .java 파일 (servlet => post, get)
5. web.xml 등록
6. Controller에서 Front 값을 이관
7. View가 필요할 경우 .jsp파일도 생성 
--%>