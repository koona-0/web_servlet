package shop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class findok_pw extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	m_selectid si = new m_selectid();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = response.getWriter();
			String mid = request.getParameter("mid");
			String snm = request.getParameter("snm");
			String stel = request.getParameter("stel");
			String semail = request.getParameter("semail");
			
			String sid = this.si.idok(snm,stel,semail);
			
			if(mid.equals(sid)){
				request.setAttribute("mid", mid);
				RequestDispatcher rd = request.getRequestDispatcher("./update_pw.jsp");
				rd.forward(request, response);
			}else {
				this.pw.write("<script>" 
			            + "alert('정보를 다시 확인해주세요');" 
			            + "history.go(-1);" 
			            + "</script>");
			}
			
		}catch (Exception e) {
			this.pw.write("<script>" 
					+ "alert('findok_pw error');" 
					+ "history.go(-1);" 
					+ "</script>");
		}finally {
			this.pw.close();
		}
	}

}
