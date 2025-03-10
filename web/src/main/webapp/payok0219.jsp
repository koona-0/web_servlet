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
<title>결제 진행</title>
</head>
<body>

<form id="frm" method="post" action="./mallok.do">
최종 결제 금액 : <input type="text" name="final_amount" value="<%=final_amount%>" readonly><br>
결제 수단 : <input type="text" name="payment" value="<%=payment%>" readonly><br>
<%
//무통장 입금이라면
if(payment.equals("무통장 입금")){
%>
계좌번호 : 02-1234-56789<br>
입금자명 : <input type="text" name="mname">
<%	
}
%>
<br>
<input type="button" value="결제완료" onclick="gopage()">
</form>

</body>
<script>
var fa = "<%=final_amount%>";
var pm = "<%=payment%>";


	var gopage = function() {
		if (pm == "무통장 입금") {

			if (frm.mname.value == "") {
				alert("입금자명을 입력하세요!");
				frm.mname.focus();
			} else {
				frm.submit();
			}

		} else {
			frm.submit();
		}
	}
</script>
</html>