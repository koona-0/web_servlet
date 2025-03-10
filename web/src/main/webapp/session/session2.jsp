<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//Controller에서 session을 받은 후 해당 session값을 문자열로 변환 후 HTML에 출력
//여기 .toString쓰면 로그아웃시 (예외처리안하면) 500번 에러 발생!!!!
HttpSession se = (HttpSession)request.getAttribute("se");
String userid = (String)se.getAttribute("id");
String usernm = (String)se.getAttribute("name");
String usertel = (String)se.getAttribute("tel");

//아래 조건문 equals(null), equals("") => 500에러 
//연산기호 쓰는 이유 : 프론트에서 받은게 아니기 때문에 연산기호 써야함! 무조건 equals가 아니다!! 
if(userid == null){
	out.print("<script>alert('로그인이 필요합니다.'); location.href='./login.html';</script>");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 정보 출력</title>
</head>
<body>
<%=usernm %>님 환영합니다. <input type="button" value="로그아웃" onclick="logout()">
<br>
아이디 : <%=userid %><br>
연락처 : <%=usertel %>
</body>
<script>
function logout(){
	if(confirm("로그아웃 하시겠습니꽈?")){
	location.href="./session3.do";		
	}
	
}
</script>
</html>