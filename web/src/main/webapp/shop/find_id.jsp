<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<body>

<form id="frm" method="post" action="./findok_id.do" onsubmit="return find_id()">
<p>아이디 찾기</p>
<input type="radio" name="spart" value="P" checked="checked" onclick="frm_view(this.value)">일반회원
<input type="radio" name="spart" value="C" onclick="frm_view(this.value)">사업자회원
<br>
회원 이름 및 회사명<br>
<input type="text" name="snm"><br>
휴대폰번호 및 전화번호<br>
<input type="text" name="stel" maxlength="11"><br>
이메일 주소<br>
<input type="text" name="semail"><br>
<span id="corp" style="display:none;">
사업자 번호<br>
<input type="text" name="sno" maxlength="13"> * "-" 없이 입력<br>
</span>
</div>
<input type="submit" value="아이디 찾기">
</form>

</body>
<script>
function find_id() {
	if (frm.snm.value == "") {
		alert("이름 및 회사명을 입력하세요");
		return false;
	} else if (frm.stel.value == "") {
		alert("휴대폰번호 및 연락처를 입력하세요");
		return false;
	} else if (frm.semail.value == "") {
		alert("이메일을 입력하세요");
		return false;
	} else {
		if (frm.spart[0].checked == true) {	//일반회원일때 
			return true;
		} else {	//사업자회원일때 
			if (frm.sno.value == "") {
				alert("사업자 번호를 입력하셔야 합니다.");
			} else if (frm.sno.value.length < 10) {
				alert("올바른 사업자 번호를 입력하세요!");
			} else {
				return true;
			}
		}
	}
}

</script>
</html>