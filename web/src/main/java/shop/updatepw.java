package shop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class updatepw extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	m_updatepw up = new m_updatepw();
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			this.pw = response.getWriter();
			String mid = request.getParameter("mid");
			String newpw = request.getParameter("newpw");
			int result = this.up.pwok(mid, newpw);
			
			if(result==0) {
				this.pw.write("<script>" 
						+ "alert('변경 실패');" 
						+ "history.go(-1);" 
						+ "</script>");
			}else {
				this.pw.write("<script>" 
						+ "alert('비밀번호 변경 완료');" 
						+ "location.href='./login.jsp';" 
						+ "</script>");
			}
		}catch (Exception e) {
			this.pw.write("<script>" 
					+ "alert('updatepw error');" 
					+ "history.go(-1);" 
					+ "</script>");
		}finally{
			this.pw.close();
		}
	}
}