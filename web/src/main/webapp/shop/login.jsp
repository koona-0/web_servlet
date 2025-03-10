<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//out.print 적용했을 때 null 출력이 될 경우(session) => 연산기호
HttpSession hs = request.getSession();
String mid = (String)hs.getAttribute("mid");
String mnm = (String)hs.getAttribute("mnm");
if(mid !=null||mnm != null){	//로그인이 되어있을 경우 다시 로그인 하는 것을 방지 
	out.print("<script>"
		+"alert('이미 로그인하셨습니다.');"
		+"location.href='./main.jsp';"
		+"</script>");
	
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 로그인</title>
</head>
<body>

<form id="frm" method="post" action="./shop_loginok.do" onsubmit="return loginck()">
<p>회원 로그인</p>
<div>
<label>	<!-- 라벨에 onclick 쓰면 더블이벤트 발생 절대 노노 -->
<input type="radio" name="spart" value="P" onclick="partcheck(this.value)" checked>일반회원
</label>
<label>
<input type="radio" name="spart" value="C" onclick="partcheck(this.value)">사업자회원
</label>
<br>
<input type="text" name="sid" placeholder="아이디를 입력하세요"><br>
<input type="password" name="spw" placeholder="패스워드를 입력하세요"><br>

<span id="snoview" style="display:none;">
<input type="text" name="sno" placeholder="사업자등록번호를 입력하세요"><br>
</span>
<input type="submit" value="로그인">
<br><br>
<input type="button" value="아이디 찾기" onclick="find_id()">
<input type="button" value="비밀번호 찾기" onclick="find_pw()">

</div>
</form>

</body>
<script src="./login.js?v=2"></script>
</html>