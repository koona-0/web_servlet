<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<ArrayList<String>> notice = (ArrayList<ArrayList<String>>)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>
</head>
<body>
<p>현재 등록된 게시물 : <%=notice.size() %></p>
<table border="1" cellpadding="0" cellspacing="0">
<thead>
<tr>
<th width="50">번호</th>
<th width="500">제목</th>
<th width="100">글쓴이</th>
<th width="100">조회</th>
<th width="150">등록일</th>
</thead>
<tbody>
<%
int f;
int total = notice.size();	//리스트 출력번호를 총 데이터 갯수로 처리 (원래 이렇게 안함)
for(f=0; f<notice.size(); f++){
%>
<tr height="30" align="center">
<td><%=total %></td>
<td align="left" onclick="notice_view('<%= notice.get(f).get(0)%>')"><%=notice.get(f).get(1) %></td>
<td><%=notice.get(f).get(2) %></td>
<td><%=notice.get(f).get(3) %></td>
<td><%=notice.get(f).get(4).substring(0,10)%></td>
</tr>
<%
total--;
//원래 번호 이렇게 안하는데 임시방편 
}
%>
</tbody>
</table>
</body>
<script>
function notice_view(no){
	//해당 게시물의 내용 및 첨부파일을 확인할 수 있는 view페이지 
	location.href='./notice_view.do?nidx='+no;
}
</script>
</html>