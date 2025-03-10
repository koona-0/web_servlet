package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class mallok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = response.getWriter();
			String final_amount = request.getParameter("final_amount");
			String payment = request.getParameter("payment");
			request.setAttribute("final_amount", final_amount);
			request.setAttribute("payment", payment);
			RequestDispatcher rd = request.getRequestDispatcher("./mallok.jsp");
			rd.forward(request, response);

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
