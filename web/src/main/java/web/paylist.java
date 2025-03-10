package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class paylist extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // 글자가 깨지지 않게 함
        response.setContentType("text/html;charset=utf-8"); // 응답을 웹 페이지 형태로 만듦

        PrintWriter pw = response.getWriter(); // 화면에 글을 쓸 준비함

        try {
            String[] selectedPrices = request.getParameterValues("product"); // 사용자가 고른 상품 가격을 가져옴
            String totalprice = request.getParameter("totalprice"); // 사용자가 입력한 총 금액을 가져옴
            System.out.println("선택된 가격: " + Arrays.toString(selectedPrices)); // 선택한 가격을 콘솔에 보여줌
            System.out.println("총 결제 금액: " + totalprice); // 총 결제 금액을 콘솔에 보여줌

            String[] allProductNames = {
                "프리즘코리아 CP55G5", 
                "삼성전자 QLED KQ85", 
                "LG전자 올레드 evo", 
                "이스트라 스마트 TV", 
                "LG전자 울트라 HD"
            }; // 모든 상품 이름을 적어둠

            String[] allProductPrices = {
                "399000", 
                "2192810", 
                "2732230", 
                "359000", 
                "790000"
            }; // 모든 상품 가격을 적어둠

            List<String> selectedProductNames = new ArrayList<>(); // 고른 상품 이름을 저장할 곳 만듦
            List<String> selectedProductPrices = new ArrayList<>(); // 고른 상품 가격을 저장할 곳 만듦

            for (int i = 0; i < allProductPrices.length; i++) {
                if (selectedPrices != null && Arrays.asList(selectedPrices).contains(allProductPrices[i])) {
                    selectedProductNames.add(allProductNames[i]); // 고른 상품 이름을 저장함
                    selectedProductPrices.add(allProductPrices[i]); // 고른 상품 가격을 저장함
                    pw.println("상품명: " + allProductNames[i] + " / 가격: " + allProductPrices[i] + "원<br>"); // 웹 페이지에 보여줌
                    System.out.println("상품명: " + allProductNames[i] + " / 가격: " + allProductPrices[i] + "원"); // 콘솔에 보여줌
                }
            }

            request.setAttribute("product_names", selectedProductNames); // 고른 상품 이름을 다음 페이지에 넘김
            request.setAttribute("product_prices", selectedProductPrices); // 고른 상품 가격을 다음 페이지에 넘김
            request.setAttribute("totalprice", totalprice); // 총 결제 금액을 다음 페이지에 넘김

            RequestDispatcher rd = request.getRequestDispatcher("/paylist.jsp"); // 다음 페이지로 이동 준비함
            rd.forward(request, response); // 실제로 다음 페이지로 이동함

        } catch (Exception e) {
            e.printStackTrace(); // 오류 내용을 콘솔에 보여줌
            pw.println("오류 발생: " + e.getMessage()); // 오류 메시지를 웹 페이지에 보여줌
        } finally {
            pw.close(); // 화면에 글 쓰는 도구를 닫음
        }
    }
}
