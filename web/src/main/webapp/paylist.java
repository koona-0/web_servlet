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
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter pw = response.getWriter();

        try {
            String[] product = request.getParameterValues("product");
            String totalprice = request.getParameter("totalprice");
            System.out.println(Arrays.toString(product));
            System.out.println(totalprice);
            
            List<String> product_prices = new ArrayList<>();
            
            if (product != null) {
                for (String p : product) {
                    if (p != null && !p.isEmpty()) {
                        product_prices.add(p);
                        pw.println("가격: " + p + "원<br>");
                    } else {
                        pw.println("잘못된 데이터 형식: " + p + "<br>");
                    }
                }

                request.setAttribute("product_prices", product_prices);
                request.setAttribute("totalprice", totalprice);

                RequestDispatcher rd = request.getRequestDispatcher("/paylist.jsp");
                rd.forward(request, response);
            } else {
                pw.println("선택된 상품이 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("오류 발생: " + e.getMessage());
        } finally {
            pw.close();
        }
    }
}
