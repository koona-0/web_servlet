<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ page import="java.util.List" %> 

<% 
    // 이전 페이지에서 받은 상품 이름과 가격 목록을 가져옴
    List<String> product_names = (List<String>) request.getAttribute("product_names"); 
    List<String> product_prices = (List<String>) request.getAttribute("product_prices"); 
    String totalprice = (String) request.getAttribute("totalprice"); // 총 결제 금액을 가져옴
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>구매확정 물품</title>
</head>
<body>
    <h2>구매확정 물품</h2>
    
    <ul>
        <% 
            // 상품이 있을 때만 목록을 보여줌
            if (product_names != null && product_prices != null && !product_names.isEmpty()) { 
                for (int i = 0; i < product_names.size(); i++) { 
                    String name = product_names.get(i); // 상품 이름 꺼냄
                    String price = product_prices.get(i); // 상품 가격 꺼냄
        %>
                    <!-- 상품 이름과 가격을 화면에 보여줌 -->
                    <li>상품명: <%= name %> / 가격: <%= price %>원</li> 
        <%      
                } 
            } else { 
        %>
                <!-- 선택된 상품이 없을 때 메시지를 보여줌 -->
                <li>선택된 상품이 없습니다.</li> 
        <% } %>
    </ul>

    <!-- 총 결제 금액을 굵게 보여줌 -->
    <p><strong>총 결제 금액: <%= totalprice %>원</strong></p> 
    
    <form id="frm" action="http://172.30.1.89:8080/web/payok0219.do" method="get">
        <!-- 최종 결제 금액을 숨겨서 전송함 -->
        <input name="final_amount" value="<%= totalprice %>">

        <h3>결제 수단을 선택하세요</h3> <!-- 결제 방법을 고르라고 알려줌 -->
        <!-- 결제 방법을 선택할 수 있게 함 -->
        <label><input type="radio" name="payment" value="신용카드"> 신용카드</label><br>
        <label><input type="radio" name="payment" value="가상계좌"> 가상계좌</label><br>
        <label><input type="radio" name="payment" value="휴대폰 결제"> 휴대폰 결제</label><br>
        <label><input type="radio" name="payment" value="무통장 입금"> 무통장 입금</label><br>

        <!-- 결제를 진행하는 버튼을 만듦 -->
        <input type="button" value="결제 진행" onclick="final()">
    </form>
</body>

<script>
function final(){
    frm.submit(); // 폼을 서버로 보냄
}
</script>

</html>
