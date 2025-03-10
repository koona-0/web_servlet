package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class payok0219 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = response.getWriter();
//			String final_amount = "500000";
//			String payment = "무통장 입금";
			String final_amount = request.getParameter("final_amount");
			String payment = request.getParameter("payment");
			System.out.println(payment);
			
			if(final_amount == null ||final_amount == "") {
				this.pw.write("<script>" 
						+ "alert('합계값 오류로 인하여 데이터 처리가 되지 않았습니다.');" 
						+ "history.go(-1);" 
						+ "</script>");
			}else if(payment == null ||payment == "") {
				//값이 하나라도 없다면
				this.pw.write("<script>" 
						+ "alert('결제수단 오류로 인하여 데이터 처리가 되지 않았습니다.');" 
						+ "history.go(-1);" 
						+ "</script>");
			}else if(payment.equals("신용카드")||payment.equals("가상계좌")||payment.equals("휴대폰 결제")||payment.equals("무통장 입금")){
				request.setAttribute("final_amount", final_amount);
				request.setAttribute("payment", payment);
				RequestDispatcher rd = request.getRequestDispatcher("./payok0219.jsp");
				rd.forward(request, response);
			}
			else {
				this.pw.write("<script>" 
						+ "alert('시스템 오류로 인하여 데이터 처리가 되지 않았습니다.');" 
						+ "history.go(-1);" 
						+ "</script>");
			}
		}catch (Exception e) {
			this.pw.write("<script>" 
					+ "alert('올바른 접근방식이 아닙니다.');" 
					+ "history.go(-1);" 
					+ "</script>");
		}finally {
			this.pw.close();
		}	
	}
}
