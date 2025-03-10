<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<ArrayList<String>> notice = (ArrayList<ArrayList<String>>)request.getAttribute("result");

//페이지 번호 생성
/*
페이징 생성방법
1. 한 페이지 당 몇개씩 데이터를 출력할 것인지를 설정 
2. 데이터베이스에 있는 데이터의 총 갯수 / 한 페이지당 갯수 => 소수점
3. Math.ceil을 사용한 이유는 소수점으로 떨어졌을 때 올림처리 
*/
String total_page = notice.get(0).get(5);
int pg = 1;

if(total_page != null || !total_page.equals(null)){
	pg = (int)Math.ceil(Integer.parseInt(total_page) / 3);
	float pg2 = Integer.parseInt(total_page) / 3f;
	pg = (int)Math.ceil(pg2);
	//out.print(pg);
}

//get으로 page번호를 가져오는 방식
//최초 공지사항 리스트 페이지에 접근시 페이지 번호가 없을 수 있음 or 1클릭시 페이지 번호가 1이될 수 있음 
String pno = (String)request.getAttribute("pageno");
out.print(pno);
if(pno ==null || pno.equals("1")){
	pno = "1";
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>
</head>
<body>
<p>현재 등록된 게시물 : <%=notice.get(0).get(5) %></p>
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
//총 데이터 갯수 - ((페이지번호 -1) * 한 페이지당 출력갯수 )
int total =Integer.parseInt(total_page) - (Integer.parseInt(pno)-1)*3;	//리스트 출력번호를 총 데이터 갯수로 처리 (원래 이렇게 안함)
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

<table border="1">
<tr>
<%
int w = 1;
while(w <= pg){
	
%>
<td width=20 height=20 align="center"><a href="./notice_list.do?pageno=<%=w %>"><%=w %></a></td>
<%
	w++;
}
%>
</tr>
</table>
</body>
<script>
function notice_view(no){
	//해당 게시물의 내용 및 첨부파일을 확인할 수 있는 view페이지 
	location.href='./notice_view.do?nidx='+no;
}
</script>
</html>