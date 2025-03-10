package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class idsearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		try {
			String mname = request.getParameter("mname");
			String mcall = request.getParameter("mcall");
			String memail = request.getParameter("memail");
			String mid = "";
			
			if(mname.equals("홍길동")&&mcall.equals("01010041919")&&memail.equals("hong@naver.com")) {
				mid = "hong2025";
			}else {
				mid = "가입정보가 확인 되지 않습니다.";
			}
			
			request.setAttribute("mid", mid);
			
			RequestDispatcher rd = request.getRequestDispatcher("/idsearch.jsp");
			rd.forward(request, response);
			
		}catch (Exception e) {
			pw.write("<script>"
					+ "alert('올바른 값이 전달되지 않았습니다.');"
					+ "history.go(-1);"	 
					+ "</script>");
		}   	
	}
}