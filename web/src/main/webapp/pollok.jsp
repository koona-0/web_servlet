<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
  //request.getAttribute : 무조건 Controller에서 값이 날아와야함
    /*
    String word = request.getAttribute("abc").toString();	//"" => null처리를 못해서 다른처리가 부가적으로 필요해서 다른놈들을 쓰는것이 좋음 
    String word = (String)request.getAttribute("abc");	 //null, ""
    String word = String.valueOf(request.getAttribute("abc"));	//null, ""
    */
    String subject = (String)request.getAttribute("subject");
    String etc[] = (String[])request.getAttribute("etc");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사 결과값</title>
</head>
<body>
선택하신 과목 : <%=subject%><br>
배우고 싶은 과목 : <%
int w = 0;
while(w < etc.length){	//Controller에서 원시배열로 값을 이관받은 데이터를 반복문으로 view에서 처리 
	out.print(etc[w]+", ");
	w++;
}
%>
</body>
</html>